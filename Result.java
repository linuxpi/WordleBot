import java.util.*;

public class Result {
  public static final String FULL_MATCH = "green";
  public static final String PARTIAL_MATCH = "yellow";
  public static final String NO_MATCH = "gray";

  private final List<String> data = new ArrayList<String>();

    public void add(Character c, Integer i, Boolean match, Boolean indexMatch) {
      if (match) {
        if (indexMatch)
          data.add(FULL_MATCH);
        else
          data.add(PARTIAL_MATCH);
      } else {
        data.add(NO_MATCH);
      }
    }

    public String get(int i) {
      return data.get(i);
    }

    public Boolean allMatch() {
      for (String charResult : data) {
        if (!charResult.equals(FULL_MATCH))
          return false;
      }
      return true;
    }
    public String toString() {
      return data.toString();
    }
  }