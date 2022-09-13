/*

Given two inputs,

  

First input is the location map, a 2D array

  

| O | E | E | E | X | 

| E | O | X | X | X |

| E | E | E | E | E |

| X | E | O | E | E |

| X | E | X | E | X | 

O = Robot, E = Empty, X = blocker

  

(n * n)

  

Second input is the query. It’s a 1D array consisting of distance to the closest blocker in the order from left, top, bottom and right [2, 2, 4, 1] ->

This means distance of 2 to the left blocker, 2 to the top blocker, 4 to the bottom blocker and 1 to the right blocker

  

Note: The location map boundary is also considered blocker, meaning if the robot hits the boundary it also means it’s hitting the blocker.

  

Write a function that takes these two inputs and returns the index of the robots (if any) that matches the query that we’re looking for.

  

Solution for the example above would be the robot located at index [1, 1]

  

*/

import java.util.*;

class Main {

  public static void main(String[] args) {
    String[] row1 = new String[] { "0", "E", "E", "E", "X" };
    String[] row2 = new String[] { "E", "0", "X", "X", "X" };
    String[] row3 = new String[] { "E", "E", "E", "E", "E" };
    String[] row4 = new String[] { "X", "E", "0", "E", "E" };
    String[] row5 = new String[] { "X", "E", "X", "E", "X" };
    String[][] input = new String[][] { row1, row2, row3, row4, row5 };
    Integer[] matchValues = new Integer[] { 1, 1, 3, 4 };

    List<List<Integer[]>> table = new ArrayList<List<Integer[]>>();

    if (input.length == 0)
      return;

    if (input[0].length == 0)
      return;

    for (int i = 0; i < input.length; i++) {
      List<Integer[]> currData = new ArrayList<Integer[]>();
      table.add(currData);
      for (int j = 0; j < input[0].length; j++) {
        String item = input[i][j];
        Integer[] topLeftData;
        if (item.equals("X")) {
          topLeftData = new Integer[] { 0, 0, 0, 0 };
        } else {
          topLeftData = new Integer[] { -1, -1, -1, -1 };
          setValue(table, topLeftData, i, j, i-1, j, 1);
          setValue(table, topLeftData, i, j, i, j-1, 0);
        }
        currData.add(topLeftData);
      }
    }

    for (int i = input.length - 1; i >= 0; i--) {
      List<Integer[]> currData = table.get(i);
      for (int j = input[0].length - 1; j >= 0; j--) {
        String item = input[i][j];
        Integer[] closestBlockerData = currData.get(j);
        if (!item.equals("X")) {
          setValue(table, closestBlockerData, i, j, i+1, j, 2);
          setValue(table, closestBlockerData, i, j, i, j+1, 3);
        }
        if (item.equals("0") && matchAns(closestBlockerData, matchValues)) {
          System.out.print(Integer.valueOf(i).toString() + "," + Integer.valueOf(j).toString());
          return;
        }
      }
    }
    System.out.println("end");
  }

  public static void setValue(List<List<Integer[]>> data, Integer[] distData, int i, int j, int iRef, int jRef, int updateIndex) {
    if (iRef < 0 || jRef < 0 || iRef == data.size() || jRef == data.size()){
      distData[updateIndex] = 1;
    } else {
      distData[updateIndex] = data.get(iRef).get(jRef)[updateIndex] + 1;
    }
  }

  public static boolean matchAns(Integer[] dist1, Integer[] dist2) {
    for (int i = 0; i < 4; i++) {
      if (!dist1[i].equals(dist2[i])) {
        return false;
      }
    }
    return true;
  }
}

