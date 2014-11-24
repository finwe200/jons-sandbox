package jon.sandbox.code.questions.ai;

public class ScreeningQuestions
{
  public int closestTo1000(int arg1, int arg2)
  {
    return (Math.abs(arg1 - 1000) <= Math.abs(arg2 - 1000)) ? arg1 : arg2;
  }

  public String doubleEachCharacterInString(String str)
  {
    if (str == null) {
      return "";
    }

    StringBuffer buf = new StringBuffer(str.length() * 2);
    for (char c : str.toCharArray())
    {
      buf.append(c);
      buf.append(c);
    }
    return buf.toString();
  }

  /** This method will return true if two sub-arrays can be found which yield
   *  equal sums. This method assumes a sub-array is restricted to a contiguous 
   *  set of elements (which simplifies the algorithm and reduces the number 
   *  of combinations to evaluate). The directions were a little ambiguous so I
   *  hope this assumption is OK.
   * 
   * @param arry
   * 
   * @return true if two contiguous sub-arrays can be found which yield
   *  equal sums, otherwise return false.
   */
  public boolean containsTwoContiguousSubArraysWithEqualSums(int[] arry)
  {
    if (arry == null) {
      return false;
    }

    for (int i = 0, n = arry.length; i < n; i++)
    {
      int sum1 = sum(arry, 0, i);
      int sum2 = sum(arry, i + 1, n -1);
      if (sum1 == sum2)
      {
        //System.out.println(
        //  " Sum1=" + sum1 + ", Sum2=" + sum2 +
        //  " (i=" + i + ")"
        //);
        return true;
      }
    }
    return false;
  }

  /** This method will return true if two sub-arrays can be found which yield
   *  equal sums. A sub-array does not have to be contiguous (this makes the
   *  algorithm more complicated).
   * 
   * @param arry
   * 
   * @return true if two contiguous sub-arrays can be found which yield
   *  equal sums, otherwise return false.
   */
  public boolean containsTwoSubArraysWithEqualSums(int[] arry)
  {
    if (arry == null) {
      return false;
    }

    // Outer loop to evaluate all possible segment sizes. Only need to evaluate 
    // 1/2 of the possible segments since two segments sizes are evaluated
    // per pass.
    // Example: If an array has 6 elements and a segment size of 1 is evaluated
    //          then a segment size of 5 is also evaluated.  
    int sumForAll = sum(arry, 0, arry.length - 1);
    for (int segmentSize = 1, maxSegSize = (arry.length + 1) / 2;
         segmentSize <= maxSegSize;
         segmentSize++)
    {
      // Initialize the list of indicies used to evaluate all possible combinations
      // for a given segment size
      int[] segmentIndicies = createAndInitializeSegmentIndicies(segmentSize);

      // Inner loop to evaluate all combinations for a segment size
      do 
      {
        // See if sum of current segment matches sum of everything else
        int segmentSum = sum(arry, segmentIndicies);
        if (segmentSum == sumForAll - segmentSum)
        {
          //System.out.println(
          //  " Sum1=" + segmentSum + ", Sum2=" + (sumForAll - segmentSum) +
          //  " (segmentSize=" + segmentSize + ", indicies=" + toString(segmentIndicies) + ")"
          //);
          return true;
        }
      }
      while (advanceSegmentIndicies(arry, segmentIndicies));
    }

    return false;
  }

  private int sum(int[] arry, int start, int end)
  {
    int rtn = 0;
    for (int i = start; i <= end; i++)
    {
      rtn += arry[i];
    }
    return rtn;
  }

  private int sum(int[] arry, int[] indicies)
  {
    int rtn = 0;
    for (int i : indicies)
    {
      rtn += arry[i];
    }
    return rtn;
  }

  @SuppressWarnings("unused")
  private String toString(int[] elements)
  {
    StringBuffer buf = new StringBuffer(200);
    buf.append('[');
    for (int i = 0, n = elements.length; i < n; i++)
    {
      int element = elements[i];
      if ( i > 0) {
        buf.append(", ");
      }
      buf.append(element);
    }
    buf.append(']');
    return buf.toString();
  }

  private boolean advanceSegmentIndicies(int[] arry, int[] indicies)
  {
    for (int i = indicies.length - 1, offset = 0, lastIndex = arry.length - 1;
         i >= 0;
         i--, offset++)
    {
      if (indicies[i] < lastIndex - offset)
      {
        indicies[i]++;
        for (int j = i + 1; j < indicies.length; j++) {
          indicies[j] = indicies[j - 1] + 1;
        }
        return true;
      }
    }
    return false;
  }

  private int[] createAndInitializeSegmentIndicies(int segmentSize)
  {
    int[] indicies = new int[segmentSize];
    for (int i = 0; i < segmentSize; i++) {
      indicies[i] = i;
    }
    return indicies;
  }

  /** Normally I would not solve this type of problem with recursion! 
   * 
   * @param str
   * @return
   */
  public String insertAsteriskBetweenEachCharacter(String str)
  {
    if (str == null) {
      return "";
    }
    StringBuffer buf = new StringBuffer(str.length() * 2);
    return insertAsteriskBetweenEachCharacter(str, buf, 0).toString();
  }

  private StringBuffer insertAsteriskBetweenEachCharacter(
    String str, StringBuffer buf, int index)
  {
    int len = str.length();
    if (index >= len) {
      return buf;
    }

    buf.append(str.charAt(index));
    if (index + 1 < len) {
      buf.append('*');
    }
    return insertAsteriskBetweenEachCharacter(str, buf, index + 1);
  }

  /** Normally I would not solve this type of problem with recursion!
   *  Note: This function will fail if the input contains an asterisk!
   * 
   * @param str
   * @return
   */
  public String insertAsteriskBetweenEachCharacter2(String str)
  {
    if (str == null) {
      return "";
    }
    int i = str.lastIndexOf('*') + 2;
    if (i >= str.length()) {
      return str;
    }
    str = str.substring(0, i) + "*" + str.substring(i);
    return insertAsteriskBetweenEachCharacter2(str);
  }

  public static void main(String[] args)
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    System.out.println("closestTo1000(1002,999)=" + sc.closestTo1000(1002,  999));
    System.out.println("doubleEachCharacterInString(\"abc\")=" + sc.doubleEachCharacterInString("abc"));

    System.out.println(
      "containsTwoContiguousSubArraysWithEqualSums([10,15,20,2,2,1])=" +
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{10,15,20,2,2,1})
    );
    System.out.println(
      "containsTwoContiguousSubArraysWithEqualSums([10,15,20,2,2,2])=" +
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{10,15,20,2,2,2})
    );

    System.out.println(
      "containsTwoSubArraysWithEqualSums([4,5,1])=" +
      sc.containsTwoSubArraysWithEqualSums(new int[]{4,5,1})
    );

    System.out.println(
      "containsTwoSubArraysWithEqualSums([4,1,5])=" +
      sc.containsTwoSubArraysWithEqualSums(new int[]{4,1,5})
    );

    System.out.println(
      "containsTwoSubArraysWithEqualSums([12,16,6,2,4,4])=" +
      sc.containsTwoSubArraysWithEqualSums(new int[]{12,16,6,2,4,4})
    );

    System.out.println(
      "containsTwoSubArraysWithEqualSums([12,1,11,3,32,13])=" +
      sc.containsTwoSubArraysWithEqualSums(new int[]{12,1,11,3,32,13})
    );

    System.out.println(
      "prefixEachCharacterInStringWithAsterisk(\"abc\")=" + 
      sc.insertAsteriskBetweenEachCharacter("abc")
    );
    System.out.println(
      "prefixEachCharacterInStringWithAsterisk(\"a\")=" + 
      sc.insertAsteriskBetweenEachCharacter("a")
    );
    System.out.println(
      "prefixEachCharacterInStringWithAsterisk(\"\")=" + 
      sc.insertAsteriskBetweenEachCharacter("")
    );

    System.out.println(
      "prefixEachCharacterInStringWithAsterisk2(\"abc\")=" + 
      sc.insertAsteriskBetweenEachCharacter2("abc")
    );
    System.out.println(
      "prefixEachCharacterInStringWithAsterisk2(\"a\")=" + 
      sc.insertAsteriskBetweenEachCharacter2("a")
    );
    System.out.println(
      "prefixEachCharacterInStringWithAsterisk2(\"\")=" + 
      sc.insertAsteriskBetweenEachCharacter2("")
    );
  }

}
