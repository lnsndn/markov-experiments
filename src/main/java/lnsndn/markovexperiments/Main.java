package lnsndn.markovexperiments;

import lnsndn.markovexperiments.data.BrideFileReaderImpl;

public class Main {

  public static void main(String[] args) {
    final SentenceGenerator generator = new SentenceGenerator(new BrideFileReaderImpl());
    System.out.println(generator.generateSentence());
  }
}
