import java.util.*;

public class RandomNonRepeatableWordGenerator {
  private final WordDict wordDict;
  private final List<String> usedWords = new ArrayList<String>();
  private final Random rand;
  
  public RandomNonRepeatableWordGenerator (WordDict wordDict) {
    this.wordDict = wordDict;
    this.rand = new Random();
  }

  private int getRandomIndex() {
    return rand.nextInt(wordDict.getMaxIndex());
  }

  public String getNewWord(ContextManager context) {
    String word = wordDict.get(getRandomIndex());
    useWord(word);
    return word;
  }


  public boolean hasWord() {
    return wordDict.getMaxIndex() > 0;
  }

  public void useWord(String word) {
    usedWords.add(word);
    wordDict.remove(word);
  }
}