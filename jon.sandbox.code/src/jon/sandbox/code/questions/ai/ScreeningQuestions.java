package jon.sandbox.code.questions.ai;

import java.util.ArrayList;
import java.util.List;

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
   * @param arry The array of integers to evaluate.
   * 
   * @return true if two contiguous sub-arrays can be found which yield
   *  equal sums, otherwise return false. A value of null, an empty array, or
   *  an array of one element will return false.
   */
  public boolean containsTwoContiguousSubArraysWithEqualSums(int[] arry)
  {
    if (arry == null || arry.length < 2) {
      return false;
    }

    // Iterate on each loop to compute the sums!
//  for (int i = 0, n = arry.length; i < n; i++)
//  {
//    int sum1 = sum(arry, 0, i);
//    int sum2 = sum(arry, i + 1, n -1);
//    if (sum1 == sum2)
//    {
//      return true;
//    }
//  }

    // Do minimal effort to compute the 2 sums
    int sum1 = 0;
    int sum2 = sum(arry, 0, arry.length - 1);
    for (int i = 0, n = arry.length - 1; i < n; i++)
    {
      int ithElement = arry[i];
      sum1 += ithElement;
      sum2 -= ithElement;
      if (sum1 == sum2)
      {
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
   * @return true if any two sub-arrays can be found which yield
   *  equal sums, otherwise return false. A value of null, an empty array, or
   *  an array of one element will return false.
   */
  public boolean containsTwoSubArraysWithEqualSums(int[] arry)
  {
    if (arry == null) {
      return false;
    }

    // Outer loop to evaluate all possible segment sizes. Only need to evaluate 
    // 1/2 of the possible segment sizess since two segments sizes are evaluated
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

      // Inner loop to evaluate all combinations for a given segment size
      do 
      {
        // See if sum of current segment matches sum of everything else
        int segmentSum = sum(arry, segmentIndicies);
        int sum2 = sumForAll - segmentSum;
        //System.out.println(
        //  " Sum1=" + segmentSum + ", Sum2=" + sum2 +
        //  " (segmentSize=" + segmentSize + ", indicies=" + toString(segmentIndicies) + ")"
        //);
        if (segmentSum == sum2)
        {
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
   *  Note: This function will not work correctly if the input contains an '*'!
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

  public String toHexadecimal(int num)
  {
    return toOtherBase(16, num);
  }

  public String toBinary(int num)
  {
    return toOtherBase(2,  num);
  }
  
  private String toOtherBase(int base, int num)
  {
    StringBuffer buf = new StringBuffer(m_digits.size());
    if (num < 0)
    {
      buf.append('-');
      num = Math.abs(num);
    }

    m_digits.clear(); 
    int result = num;
    do
    {
      int remainder = result % base;
      m_digits.add(remainder);
      result = result / base;
    }
    while (result != 0);

    for (int i = m_digits.size() - 1; i >= 0; i--) {
      buf.append(ms_hexChars[m_digits.get(i)]);
    }
    return buf.toString();
  }

  public String addPositiveIntegers(String num1, String num2)
  {
    int n = Math.max(num1.length(), num2.length());
    StringBuffer buf = new StringBuffer(n * 2);
    int carry = 0;

    for (int count = n, i1 = num1.length() - 1, i2 = num2.length() - 1; count > 0; count--, i1--, i2--)
    {
      int d1 = (i1 >= 0) ? Integer.parseInt("" + num1.charAt(i1)) : 0;
      int d2 = (i2 >= 0) ? Integer.parseInt("" + num2.charAt(i2)) : 0;
      int sum = d1 + d2 + carry;
      carry = sum / 10;
      sum = (sum < 10) ? sum : sum - 10;
      buf.insert(0, sum);
    }
    if (carry > 0) {
      buf.insert(0, '1');
    }
    return buf.toString();
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

    System.out.println("toHexadecimal(680)=" + sc.toHexadecimal(680));
    System.out.println("toHexadecimal(0)=" + sc.toHexadecimal(0));
    System.out.println("toHexadecimal(-11259375)=" + sc.toHexadecimal(-11259375));

    System.out.println("toBinary(16)=" + sc.toBinary(16));
    System.out.println("toBinary(0)=" + sc.toBinary(0));
    System.out.println("toBinary(-511)=" + sc.toBinary(-511));

    System.out.println(
      "addPositiveIntegers(\"983\", \"92\")=" + sc.addPositiveIntegers("983", "92"));
    System.out.println(
      "addPositiveIntegers(\"66\", \"85\")=" + sc.addPositiveIntegers("66", "85"));
  }

  private final static char[] ms_hexChars =
    {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

  private final List<Integer> m_digits = new ArrayList<Integer>(10);
}
