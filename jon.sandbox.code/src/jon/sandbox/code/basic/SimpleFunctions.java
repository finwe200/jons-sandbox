package jon.sandbox.code.basic;

public class SimpleFunctions
{
  static public int fib1(int n)
  {
    if (n < 2) {
      return 1;
    }
    return fib1(n-1) + fib1(n-2);
  }

  static public int fib2(int n)
  {
    int prev = 1;
//  int next = 1;
    int result = 1;
    for (int i = 2; i <= n; i++)
    {
      int tmp = prev;
      prev = result;
      result += tmp;
    }
    return result;
  }

  static public void outputFib1(int n)
  {
    System.out.print("Fib1(" + n + "): ");
    for (int i = 0; i <= n; i++) 
    {
      if (i > 0) {
        System.out.print(", ");
      }
      System.out.print(fib1(i));
    }
    System.out.println();
  }

  static public void outputFib2(int n)
  {
    System.out.print("Fib2(" + n + "): ");
    for (int i = 0; i <= n; i++) 
    {
      if (i > 0) {
        System.out.print(", ");
      }
      System.out.print(fib2(i));
    }
    System.out.println();
  }
  
  static public String invertWords(String input)
  {
    String[] words = input.split(" ");
    StringBuffer buf = new StringBuffer(input.length());

    // Evaluate words in original order
    boolean firstWord = true;
    for (String word : words)
    {
       if (!firstWord) {
         buf.append(' ');
       } else {
         firstWord = false;
       }
 
       // Invert word
       for (int i = word.length() - 1; i >= 0; i--)
       {
          buf.append(word.charAt(i));
       }
    } 
    return buf.toString();
  }

  static public void outputInvertedWords(String words)
  {
    System.out.println("Words=" + words);
    System.out.println("Inverted Words=" + invertWords(words) + "\n");
  }

  static public char[] invertWordsInPlace(char[] input)
  {
    int i = skipWhiteSpace(input, 0);
    int n = input.length - 1;

    // Outer loop for all words
    while (i <= n)
    {
      // Determine length of current word
      int j = i;
      for (; j <= n && !Character.isWhitespace(input[j + 1]); j++);

      // Advance "i" after end of word
      int i1 = i;
      i = j + 1;

      // Invert current word 
      for (; i1 < j; i1++, j--)
      {
        char tmp = input[i1];
        input[i1] = input[j];
        input[j] = tmp;
      }

      // Find start of next word
      i = skipWhiteSpace(input, i);
    }

    return input;
  }

  static private int skipWhiteSpace(char[] chars, int index)
  {
    for (int n = chars.length; index < n && Character.isWhitespace(chars[index]); index++);
    return index;
  }

  static public void outputInvertedWordsInPlace(String words)
  {
    System.out.println("Words=" + words);
    System.out.println("Inverted Words=" + new String(invertWordsInPlace(words.toCharArray())) + "\n");
  }

  public static void main(String[] args)
  {
    outputFib1(12);

    System.out.println();
    outputFib2(12);

    outputInvertedWords("Hello World");
    outputInvertedWords("The time has come the walrus said to talk of many things ...");

    outputInvertedWordsInPlace(" Hello  World ");
    outputInvertedWordsInPlace(" The time has come the walrus said \tto talk of many things ...  ");
  }
}
