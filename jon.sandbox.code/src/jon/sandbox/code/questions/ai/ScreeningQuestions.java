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
    for (int i = 0, n = arry.length; i < n; i++)
    {
      int sum1 = sum(arry, 0, i);
      int sum2 = sum(arry, i + 1, n -1);
      if (sum1 == sum2) {
        return true;
      }
    }
    return false;
  }

  private int sum(int[] arry, int start, int end)
  {
    int rtn = 0;
    for (int i = start; i <= end; i++) {
      rtn += arry[i];
    }
    return rtn;
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
