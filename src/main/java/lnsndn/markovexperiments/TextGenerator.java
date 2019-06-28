package lnsndn.markovexperiments;

import lnsndn.markovexperiments.data.DataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TextGenerator {

  private final String START_WORD_1 = "BEGIN";
  private final String START_WORD_2 = "HERE";
  private final String STOP_WORD = "END";
  private final int MAX_POOL_SIZE = 10; // should really be number of CPUs-1
  private Dictionary dictionary = new Dictionary();
  private DataReader reader;

  public TextGenerator(DataReader reader) {
    this.reader = reader;
    buildDictionary();
  }

  private void buildDictionary() {
    final List<String> lines = reader.getLines();
    final int maxChunkSize = lines.size() / MAX_POOL_SIZE;
    int poolSize = 1;

    if (lines.size() > maxChunkSize) {
      while (poolSize < MAX_POOL_SIZE && poolSize * maxChunkSize < lines.size()) {
        poolSize++;
      }
    }
    final int actualThreadChunkSize = lines.size() / poolSize;
    List<Callable<Boolean>> callables = new ArrayList<>();

    for (int i = 0; i < lines.size(); i += actualThreadChunkSize) {
      final int finalI = i;
      callables.add(() -> {
        lines
          .subList(finalI, Math.min(finalI + actualThreadChunkSize, lines.size()))
          .forEach(dictionary::addCombosFromString);
        return true;
      });
    }
    ExecutorService executor = Executors.newFixedThreadPool(poolSize);
    try {
      executor.invokeAll(callables);
      executor.shutdown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public String generate() {
    return generate(START_WORD_1, START_WORD_2, "");
  }

  private String generate(String word1, String word2, String text) {
    if(!dictionary.exists(word1, word2)) {
      return text;
    }
    final String nextWord = dictionary.generateFollowingWord(word1, word2);
    if(nextWord.equals(STOP_WORD)) {
      return text.trim();
    }
    return generate(word2, nextWord, text + nextWord + " ");
  }
}
