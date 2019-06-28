package lnsndn.markovexperiments;

import lnsndn.markovexperiments.data.BrideFileReaderImpl;

public class Main {

  public static void main(String[] args) {
    final TextGenerator generator = new TextGenerator(new BrideFileReaderImpl());
    System.out.println(generator.generate());
  }
}
