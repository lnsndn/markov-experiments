package lnsndn.markovexperiments;

import lnsndn.markovexperiments.data.DataReader;


public class SentenceGenerator {

  private final String START_WORD_1 = "BEGIN";
  private final String START_WORD_2 = "HERE";
  private final String STOP_WORD = "END";
  private WordCombos wordCombos = new WordCombos();

  public SentenceGenerator(DataReader reader) {
    // Not thread safe!
    reader.getLines().forEach(wordCombos::addCombosFromString);
  }

  public String generateSentence() {
    return generate(START_WORD_1, START_WORD_2, "");
  }

  private String generate(String word1, String word2, String sentence) {
    if(!wordCombos.exists(word1, word2)) {
      return sentence;
    }
    final String nextWord = wordCombos.generateFollowingWord(word1, word2);
    if(nextWord.equals(STOP_WORD)) {
      return sentence.trim();
    }
    return generate(word2, nextWord, sentence + nextWord + " ");
  }
}
