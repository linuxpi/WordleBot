import java.util.*;
import java.util.stream.*;
import java.util.stream.IntStream;

class Main {
  public static void main(String[] args) {
    RandomNonRepeatableWordGenerator wordGen = new RandomNonRepeatableWordGenerator(new WordDict());
    WordleBot bot = new WordleBot(wordGen, "jolly");
    System.out.println(bot.solve());
  }
}
