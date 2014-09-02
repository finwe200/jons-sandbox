package jon.sandbox.code.stream;

import java.util.ArrayList;
import java.util.List;

import jon.sandbox.code.basic.ComparableComparator;
import jon.sandbox.code.basic.Fruit;

public class SortedStreamMain extends Object
{
  public SortedStreamMain()
  {
    super();
  }

  static public void output(SortedStreamManager<?> mgr)
  {
    System.out.print("[");
    for (boolean firstElement = true; mgr.hasNext(); firstElement = false)
    {
      if (!firstElement) {
        System.out.print(','); 
      }
      Object obj = mgr.next();
      String name = (obj == null) ? "null" : obj.toString();
      System.out.print(name);
    }
    System.out.println("]");
  }

  static public void main(String[] args)
  {
    {
      ComparableComparator<Integer> comparator = new ComparableComparator<Integer>();
      SortedArrayStream<Integer> s1 =
        new SortedArrayStream<Integer>(new Integer[]{4,1,25,3,5});
      SortedArrayStream<Integer> s2 =
        new SortedArrayStream<Integer>(new Integer[]{null,17,null,21,-6,null}, comparator);
      SortedArrayStream<Integer> s3 = new SortedArrayStream<Integer>(new Integer[]{});
      SortedArrayStream<Integer> s4 = new SortedArrayStream<Integer>(new Integer[]{1000});
      List<ISortedStream<Integer>> streams = new ArrayList<ISortedStream<Integer>>(10);
      streams.add(s1);
      streams.add(s2);
      streams.add(s3);
      streams.add(s4);
      SortedStreamManager<Integer> mgr = new SortedStreamManager<Integer>(streams);
      System.out.println("SortedStreamManager([4,1,25,3,5], [null,17,null,21,-6,null], [], [1000])");
      output(mgr);
    }

    {
      ComparableComparator<Fruit> comparator = new ComparableComparator<Fruit>();
      SortedArrayStream<Fruit> s1 = null;
      SortedArrayStream<Fruit> s2 = new SortedArrayStream<Fruit>(
        new Fruit[]{new Fruit("Watermelon"), null, new Fruit("Apple")}, comparator);
      SortedArrayStream<Fruit> s3 = new SortedArrayStream<Fruit>(new Fruit[]{});
      SortedArrayStream<Fruit> s4 = new SortedArrayStream<Fruit>(
        new Fruit[]{new Fruit("Pineapple"), new Fruit("Apple"), new Fruit("Orange"), null}, comparator);
      SortedArrayStream<Fruit> s5 = new SortedArrayStream<Fruit>(
        new Fruit[]{null, new Fruit("Banana"), null}, comparator);
      List<ISortedStream<Fruit>> streams = new ArrayList<ISortedStream<Fruit>>(10);
      streams.add(s1);
      streams.add(s2);
      streams.add(s3);
      streams.add(s4);
      streams.add(s5);
      SortedStreamManager<Fruit> mgr = new SortedStreamManager<Fruit>(streams);
      System.out.println("\nSortedStreamManager(null, ['Watermelon',null,'Apple'], [], ['Pineapple','Apple','Orange',null], [null,'Banana'])");
      output(mgr);
    }
  }
}
