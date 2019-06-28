package lnsndn.markovexperiments;

import lnsndn.markovexperiments.data.BrideReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    final SentenceGenerator generator = new SentenceGenerator(new BrideReader());
    System.out.println(generator.generateSentence());
  }
}
