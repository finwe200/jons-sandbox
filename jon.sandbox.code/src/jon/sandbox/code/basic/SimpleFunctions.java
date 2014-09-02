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

  public static void main(String[] args)
  {
    outputFib1(12);

    System.out.println();
    outputFib2(12);
  }
}
