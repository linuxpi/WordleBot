import java.util.*;
import java.util.stream.Collectors;

public class WordleBot {
  private final String ans;
  private final Set<Character> ansSet;
  private final RandomNonRepeatableWordGenerator wordGen;
  private final ContextManager context;

  public WordleBot(RandomNonRepeatableWordGenerator wordGen,
      String ans) {
    this.wordGen = wordGen;
    this.ans = ans;
    this.ansSet = ans.chars().mapToObj(c -> new Character((char)c)).collect(Collectors.toSet());      
    this.context = new ContextManager();
  }

  public int solve() {
    int count = 0;
    while (wordGen.hasWord() && count < 6) {
      String guess = wordGen.getNewWord(context);
      if (!context.match(guess)) {
        continue;
      }
      count += 1;
      Result result = checkWord(guess);
      if (result.allMatch())
        return count;

      context.update(guess, result);
      System.out.println("udpated conteext = " + context);
    }
    return -1;
  }

  public Result checkWord(String guess) {
    System.out.println("checking guess = " + guess);
    Result result = new Result();

    for (int i = 0; i < guess.length(); i++) {
      Boolean indexMatch = guess.charAt(i) == ans.charAt(i);
      Boolean match = indexMatch || ansSet.contains(guess.charAt(i));
      result.add(guess.charAt(i), i, match, indexMatch);
    }
    System.out.println("result = " + result);
    return result;
  }
}