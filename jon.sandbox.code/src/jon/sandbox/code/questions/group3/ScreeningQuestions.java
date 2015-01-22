package jon.sandbox.code.questions.group3;

import java.util.Arrays;
import java.util.HashMap;

public class ScreeningQuestions extends Object
{
  public ScreeningQuestions()
  {
    super();
  }

  public static void main(String[] args)
  {
    {
      ScreeningQuestions sq = new ScreeningQuestions();
      int[] ints = {10, 9, 7, 16, 12, 11, 2};

      sq.printCallToContainsSumOfTarget(ints, 13);
      sq.printCallToContainsSumOfTarget(ints, 12);
      sq.printCallToContainsSumOfTarget(ints, 10);

      sq.printCallToContainsSumOfTarget2(ints, 13);
      sq.printCallToContainsSumOfTarget2(ints, 12);
      sq.printCallToContainsSumOfTarget2(ints, 10);
    }
  }

  /**
   * 
   * @param ints An array of positive numbers or zero.
   * @param target
   * @return
   */
  public boolean containsSumOfTarget(int[] ints, int target)
  {
    // Place numbers in sorted order
    Arrays.sort(ints);

    for (int i = 0, n = ints.length; i < n; i++)
    {
      int cur = ints[i];
      if (cur < 0 || cur > target)
      {
        // Bad input detected or it's no longer possible to find a sum
        return false;
      }

      int index = Arrays.binarySearch(ints, target - cur);
      if (index > 0)
        return true;
    }
 
    return false;
  }

  /**
   * 
   * @param ints An array of positive numbers or zero.
   * @param target
   * @return
   */
  public boolean containsSumOfTarget2(int[] ints, int target)
  {
    // Place all numbers in a "hash map"
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(ints.length); 
    for (int element : ints)
    {
      if (element < 0)
      {
        // Abort on bad input
        return false;
      }

      // Filter out values that can never yield the target 
      if (element <= target) {
        map.put(element, element);
      }
    }

    for (Integer key : map.keySet())
    {
      if (map.containsKey(target - key)) {
        return true;
      }
    }
 
    return false;
  }

  public String toString(int[] elements)
  {
    StringBuilder buf = new StringBuilder("int[");
    for (int i = 0, n = elements.length; i < n; i++)
    {
      if (i > 0) {
        buf.append(',');
      }
      buf.append(elements[i]);
    }
    buf.append(']');
    return buf.toString();
  }

  public void printCallToContainsSumOfTarget(int[] ints, int target)
  {
    System.out.println(
      "containsSumOfTarget(" + toString(ints) + ", " + target + ")= " +
      containsSumOfTarget(ints, target)
    );
  }

  public void printCallToContainsSumOfTarget2(int[] ints, int target)
  {
    System.out.println(
      "containsSumOfTarget2(" + toString(ints) + ", " + target + ")= " +
      containsSumOfTarget2(ints, target)
    );
  }
};
