import java.util.*;

public class ContextManager {
    private final Set<Character> gray = new HashSet<Character>();
    private final Map<Character, List<Integer>> yellow = new HashMap<Character, List<Integer>>();
    private final Map<Character, List<Integer>> green = new HashMap<Character, List<Integer>>();

    public ContextManager() {
    }

    public String toString() {
      return gray.toString() + yellow.toString() + green.toString();
    }

    public void update(String word, Result result) {
      char[] wordChars = word.toCharArray();
      for (int i = 0; i < wordChars.length; i++) {
        String charResult = result.get(i);
        if (charResult.equals(Result.NO_MATCH)) {
          gray.add(wordChars[i]);
        } else if (charResult.equals(Result.FULL_MATCH)) {
          updateMap(green, wordChars[i], i);
        } else {
          updateMap(yellow, wordChars[i], i);
        }
      }
    }

    public void updateMap(Map<Character, List<Integer>> data, Character c, int i) {
      if (!data.containsKey(c)) {
        data.put(c, new ArrayList<Integer>());
      }
      if (!data.get(c).contains(i)) data.get(c).add(i);
    }

    public boolean match(String word) {
      return matchGray(word) && matchYellow(word) && matchGreen(word);
    }

    private boolean matchGray(String word) {
      for (char c : word.toCharArray()) {
        Character cChar = c;
        if (gray.contains(cChar)) {
          return false;
        }
      }
      return true;
    }

    private boolean matchYellow(String word) {
      int count = 0;
      char[] charArray = word.toCharArray();

      for (int i = 0; i < word.length(); i++) {
        Character cChar = charArray[i];
        if (yellow.containsKey(cChar) && !yellow.get(cChar).contains(i)) {
          count += 1;
        }
      }
      return count == yellow.size();
    }

    private boolean matchGreen(String word) {
      char[] charArray = word.toCharArray();
      for (Map.Entry<Character, List<Integer>> entry : green.entrySet()) {
        Character expChar = entry.getKey();
        List<Integer> charIndex = entry.getValue();
        for (Integer index: charIndex) {
        if (charArray[index] != expChar.charValue()) return false;          
        }
      }
      return true;
    }
  }