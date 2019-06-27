package lnsndn.markovexperiments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args){
    final SentenceGenerator generator = new SentenceGenerator(readFile("src/main/resources/brides.txt"));
    System.out.println(generator.generateSentence());
  }

  private static List<String> readFile(String filename) {
    List<String> records = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      while ((line = reader.readLine()) != null) {
        records.add(line);
      }
      reader.close();
      return records;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
