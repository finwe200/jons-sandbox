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

    int halfTarget = (target + 1) / 2;
    for (int i = 0, n = ints.length - 1; i < n; i++)
    {
      int cur = ints[i];
      if (cur < 0)
      {
        // Abort on bad input
        throw new IllegalArgumentException(
          "The array passed to containsSumOfTarget2() cannot contain a negative integer!"
        );
      }

      if (cur > halfTarget)
      {
        // It's no longer possible to find a sum
        return false;
      }

      int index = Arrays.binarySearch(ints, target - cur);
      if (index >= 0)
      {
        // Handle the case where the two numbers used to compute the "target"
        // are the same (i.e. 0 + 0 = 0, 5 + 5 = 10, etc.)
        if (index == i) {
          if (!containsDuplicate(ints, index)) {
            continue;
          }
        }
        return true;
      }
    }
 
    return false;
  }

  /*
   * See if the sorted array contains a duplicate of the value that
   * corresponds to the specified index.
   */
  private boolean containsDuplicate(int[] ints, int index)
  {
    if (index > 0) {
      return (ints[index] == ints[index - 1]);
    }
    if (index + 1 < ints.length) {
      return (ints[index] == ints[index + 1]);
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
    // Place all numbers in a "hash map. Use a "count" to handle the case
    // where the two numbers used to compute the "target" are the same
    // (i.e. 0 + 0 = 0, 5 + 5 = 10, etc.)
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(ints.length); 
    for (int element : ints)
    {
      if (element < 0)
      {
        // Abort on bad input
        throw new IllegalArgumentException(
          "The array passed to containsSumOfTarget2() cannot contain a negative integer!"
        );
      }

      // Filter out values that can never yield the target 
      if (element <= target)
      {
        Integer count = map.get(element);
        if (count == null) {
          map.put(element, 1);
        }
        else {
          map.put(element, count.intValue() + 1);
        }
      }
    }

    for (Integer lhs : map.keySet())
    {
      int rhs = target - lhs;
      Integer count = map.get(rhs);
      if (count != null && (lhs.intValue() != rhs || count.intValue() >= 2)) {
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
