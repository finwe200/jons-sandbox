package jon.sandbox.code.questions;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import jon.sandbox.code.basic.Fruit;

public class ScreeningQuestions<E extends Comparable<E>> extends Object
{
  /**
   * 
   * @param clazz this parameter provides the type information needed to
   *  create an array of E at runtime! 
   */
  public ScreeningQuestions(Class<E> clazz)
  {
    super();
    m_class = clazz;
  }

  public static void main(String[] args)
  {
    {
      ScreeningQuestions<Integer> sq = new ScreeningQuestions<Integer>(Integer.class);
      Integer[] input = {1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17};
      printArray(input);
      Integer[] output = sq.removeDuplicates(input);
      printArray(output);
  
      System.out.println();
      printArray(input);
      output = sq.removeDuplicates2(input);
      printArray(output);
    }

    {
      ScreeningQuestions<Fruit> sq = new ScreeningQuestions<Fruit>(Fruit.class);
      System.out.println();
      Fruit[] input = {new Fruit("Apple"), new Fruit("Banana"), new Fruit("Banana"), new Fruit("Lemon"), new Fruit("Lemon")};
      printArray(input);
      Fruit[] output = sq.removeDuplicates(input);
      printArray(output);

      System.out.println();
      printArray(input);
      output = sq.removeDuplicates2(input);
      printArray(output);
    }
    
    System.out.println();
    printSquareRoot(2);
    printSquareRoot(4);
    printSquareRoot(16);
    printSquareRoot(30);
    printSquareRoot(1056);
    printSquareRoot(1000000);
  }

  /*
  QUESTION 1-A

  Given a sorted array of integers, write a method to remove the duplicates.  Do
  not use any classes from the java.util package, or the equivalent library for
  your language.
  Example: [ 1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17 ] ->
           [ 1, 2, 3, 4, 10, 13, 15, 17 ]
  */
  
  /**
   * Remove all duplicates from an array of sorted Objects.
   *
   * @param values The sorted array of Objects to remove the duplicates from.
   *
   * @return A copy of the input array with all duplicates removed.
   *
   * @throws IllegalArgumentException will be thrown if values is null, any
   *  elements contained in values are null, or values is not in sorted order.
   */
  @SuppressWarnings("unchecked")
  public E[] removeDuplicates(Comparable<E>[] values)
  {
    if (values == null)
    {
      throw new IllegalArgumentException("Cannot pass null array to removeDuplicates().");
    }

    // Determine the number of non-duplicates
    E prev = null;
    int numNonDups = 0;
    for (int i = 0, n = values.length; i < n; i++)
    {
      Comparable<E> e = values[i];
      if (e == null)
      {
        String msg = MessageFormat.format("Element {0} passed to removeDuplicates() is null!", i);
        throw new IllegalArgumentException(msg);
      }

      int comp = (prev == null) ? 1 : e.compareTo(prev);
      if (comp < 0)
      {
        throw new IllegalArgumentException("The elements passed to removeDuplicates() must be in sorted order.");
      }
      if (comp != 0)
      {
        numNonDups++;
      }
      prev = (E)e;
    }

    /*
    Type type = this.getClass().getGenericSuperclass();
    System.out.println("GenericSuperclass : " + type);
    System.out.println("GenericSuperclass class : " + type.getClass());
    if(type instanceof ParameterizedType)
    {
      ParameterizedType pt = (ParameterizedType)type;
      System.out.println("ParameterizedType : " + pt);
      System.out.println("ParameterizedType OwnerType : " + pt.getOwnerType());
      System.out.println("ParameterizedType RawType : " + pt.getRawType());
    }
    */

    // To create an array of T in JAVA you must know the class type for
    // the array (Collections and generics work differently)
    E[] nonDups = (E[])Array.newInstance(m_class, numNonDups);

    // Only store non-duplicates in the new array
    prev = null;
    for (int i = 0, i1 = 0, n = values.length - 1; i <= n; i++)
    {
      Comparable<E> e = values[i];
      int comp = (prev == null) ? 1 : e.compareTo(prev);
      if (comp != 0)
      {
        nonDups[i1++] = (E)values[i];
      }
      prev = (E)e;
    }

    return nonDups;
  }

  /*
  QUESTION 1-B
  
  Now implement your solution to A as a Java iterator that does not modify the source
  array, and returns only unique elements.  Write a concrete subclass of the
  interface java.util.Iterator; the interface is included below for convenience
  (see http://download-llnw.oracle.com/javase/6/docs/api/java/util/Iterator.html).
  You do not need to implement the optional remove() method.
  (For other languages, implement a generator or other equivalent construct.)
  Do not "cheat" by calling the removeDuplicates() you defined above. One benefit
  of iterators is that they can operate incrementally -- make your iterator
  only do the minimum work it needs to in each call to hasNext() or next().
  public interface Iterator<E>
  */
  
  /**
   * Remove all duplicates from an array of sorted Objects. This implementation
   *  uses an Iterator to access the array elements.
   *
   * @param values The sorted array of Objects to remove the duplicates from.
   *
   * @return A copy of the input array with all duplicates removed.
   *
   * @throws IllegalArgumentException will be thrown if values is null, any
   *  elements contained in values are null, or values is not in sorted order.
   */
  @SuppressWarnings("unchecked")
  public E[] removeDuplicates2(Comparable<E>[] values)
  {
    if (values == null)
    {
      throw new IllegalArgumentException("Cannot pass null array to removeDuplicates2().");
    }

    // Create list of non-duplicates
    ArrayIterator<Comparable<E>> itr = new ArrayIterator<Comparable<E>>(values);
    List<E> nonDups = new ArrayList<E>(values.length);
    E prev = null;
    while (itr.hasNext())
    {
      Comparable<E> e = itr.next();
      if (e == null)
      {
        throw new IllegalArgumentException("Cannot pass null array element to removeDuplicates().");
      }

      int comp = (prev == null) ? 1 : e.compareTo(prev);
      if (comp < 0)
      {
        throw new IllegalArgumentException("The elements passed to removeDuplicates() must be sorted.");

      }
      if (comp != 0)
      {
        nonDups.add((E)e);
      }
      prev = (E)e;
    }

    // To create an array of T in JAVA you must know the class type for
    // the array (Collections and generics work differently)
    E[] rtn = nonDups.toArray((E[]) Array.newInstance(m_class, nonDups.size()));

    return rtn;
  }

  public E dummyMethod()
  {
    return null;
  }
  
  static private <T> void printArray(T[] elements)
  {
    System.out.print(elements.getClass().getSimpleName() + "(" + elements.length + "): [");
    for (int i = 0, n = elements.length; i < n; i++)
    {
      if (i > 0) {
        System.out.print(',');
      }
      T e = elements[i];
      System.out.print((e == null) ? "null" : elements[i].toString());
    }
    System.out.println(']');
  }

  /*
  QUESTION 2

  Given a non-negative real number a, its square root is a number x, such that x * x = a.
  One way to compute a square root is via successive approximation, where one estimate
  yields a better estimate.
  For example, let's say you are trying to find the square root of 2, and you have
  an estimate of 1.5. We'll say a = 2, and x = 1.5. To compute a better estimate,
  we'll divide a by x. This gives a new value y = 1.333333... However, we can't just
  take this as our next estimate (why not?). We need to average it with the previous
  estimate. So our next estimate, xx will be (x + y) / 2, or 1.416666...
  A) Write a function that takes a non-negative real number a, and an epsilon (a
     small real number), and returns an approximation of the square root of a.
      double square_root(double a, double epsilon) ...
     Epsilon determines how accurate the approximation needs to be. The function
     should return the first approximation x it obtains that satisfies abs(x*x - a) < epsilon,
     where abs(x) is the absolute value of x.
     Questions (answer these):
     1. Why can't the next estimate, xx, be computed as a / x, where x is the
        previous estimate?
     2. What happens, in your implementation, if a = 0?
     3. What value does your program give for square_root(2, 1e-6)?
  */

  static public double square_root(double a, double epsilon)
  {
    int count = 1;
    double estimate = getInitialEstimate(a);
    double sumOfEstimates = estimate;

    while (true)
    {
      double remainder = Math.abs((estimate * estimate) - a);
      if (remainder < epsilon) {
        return estimate;
      }
      
      double newEstimate = a / estimate;
      sumOfEstimates += newEstimate;
      count++;
      estimate = sumOfEstimates / count;
    }
  }
  
  static private double getInitialEstimate(double num)
  {
    int numAsInt = (int)Math.round(num);
    int powOfTen = 1;
    for (int i = 0, n = ms_initialEstimates.length; i < n; i++)
    {
      int lowerBound = (i == 0) ? 0 : powOfTen;
      powOfTen *= 10;
      if (numAsInt >= lowerBound && numAsInt <= powOfTen) {
        return ms_initialEstimates[i];
      }
    }
    return ms_initialEstimates[ms_initialEstimates.length - 1];
  }

  static private void printSquareRoot(double a, double epislon)
  {
    double x = square_root(a, epislon);
    System.out.println("Square Root(" + a + ", " + epislon + ") = " + x);
  }

  static private void printSquareRoot(double a)
  {
    System.out.println();
    printSquareRoot(a, 1.5);
    printSquareRoot(a, 1);
    printSquareRoot(a, 0.75);
    printSquareRoot(a, 0.5);
    printSquareRoot(a, 0.25);
    printSquareRoot(a, 0.1);
    printSquareRoot(a, 0.01);
    printSquareRoot(a, 0.005);
    printSquareRoot(a, 1e-6);
  }

  // Initial "square-root estimate" for each power of 10: 0-10, 11-100, 101-1000, etc. 
  static private final double[] ms_initialEstimates = {3, 5, 16, 50, 158, 500, 1581};

  private final Class<E> m_class;
};
