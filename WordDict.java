import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class WordDict {
  private List<String> words;

  public WordDict() {
    try{
      String[] a = Files.lines(Paths.get("allWords.json"))
        .collect(Collectors.toList()).get(0).split("\", \"");  
      this.words = new ArrayList(Arrays.asList(a));
      words.set(0, words.get(0).substring(1));
      words.set(words.size()-1, words.get(words.size()-1).substring(0,5));
    } catch (Exception e) {
      System.out.println(e);
      this.words = new ArrayList<String>();
    }
  }

  public int getMaxIndex() {
    return words.size();
  }
  
  public String get(int index) {
    return words.get(index));
  }

  public void remove(String word) {
    words.remove(word);
  }
}
