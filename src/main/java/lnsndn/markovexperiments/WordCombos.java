package lnsndn.markovexperiments;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

class WordCombos {

    // Represents a map of word tuples and all the words that have been encountered
    // following each particular tuple
    private HashMap<Pair<String, String>, List<String>> words = new HashMap<>();

    Boolean exists(String word1, String word2) {
        return words.containsKey(Pair.of(word1, word2));
    }

    String generateFollowingWord(String word1, String word2) {
        // Randomizes a word known to follow the string tuple combo
        Random random = new Random();
        final List<String> followingWords = words.get(Pair.of(word1, word2));
        return followingWords.get(random.nextInt(followingWords.size()));
    }

    void addCombosFromString(final String str) {
        final List<String> splitWords = Arrays.asList(str.split(" "));
        for(int i = 0; i < splitWords.size() - 3; i++) {
            // TODO: cleaning words, remove special characters, convert to lower case etc
            final Pair<String, String> wordCombo = Pair.of(splitWords.get(i), splitWords.get(i + 1));
            final String nextWord = splitWords.get(i + 2);
            words.computeIfAbsent(wordCombo, w -> new ArrayList<>()).add(nextWord);
        }
    }
}
