package jon.sandbox.code.streams;

import java.util.ArrayList;
import java.util.List;

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
      System.out.print(mgr.next().toString());
    }
    System.out.println("]");
  }

  static public void main(String[] args)
  {
    {
      SortedArrayStream<Integer> s1 =
        new SortedArrayStream<Integer>(new Integer[]{4,1,25,3,5});
      SortedArrayStream<Integer> s2 =
        new SortedArrayStream<Integer>(new Integer[]{17,21,-6});
      SortedArrayStream<Integer> s3 = new SortedArrayStream<Integer>(new Integer[]{});
      SortedArrayStream<Integer> s4 = new SortedArrayStream<Integer>(new Integer[]{1000});
      List<ISortedStream<Integer>> streams = new ArrayList<ISortedStream<Integer>>(10);
      streams.add(s1);
      streams.add(s2);
      streams.add(s3);
      streams.add(s4);
      SortedStreamManager<Integer> mgr = new SortedStreamManager<Integer>(streams);
      System.out.println("SortedStreamManager([4,1,25,3,5], [17,21,-6], [], [1000])");
      output(mgr);
    }

    {
      SortedArrayStream<Fruit> s1 = null;
      SortedArrayStream<Fruit> s2 =
        new SortedArrayStream<Fruit>(new Fruit[]{new Fruit("Watermelon"), new Fruit("Apple")});
      SortedArrayStream<Fruit> s3 = new SortedArrayStream<Fruit>(new Fruit[]{});
      SortedArrayStream<Fruit> s4 =
        new SortedArrayStream<Fruit>(new Fruit[]{new Fruit("Pineapple"), new Fruit("Apple"), new Fruit("Orange")});
      SortedArrayStream<Fruit> s5 =
        new SortedArrayStream<Fruit>(new Fruit[]{new Fruit("Banana")});
      List<ISortedStream<Fruit>> streams = new ArrayList<ISortedStream<Fruit>>(10);
      streams.add(s1);
      streams.add(s2);
      streams.add(s3);
      streams.add(s4);
      streams.add(s5);
      SortedStreamManager<Fruit> mgr = new SortedStreamManager<Fruit>(streams);
      System.out.println("\nSortedStreamManager(null, ['Watermelon','Apple'], [], ['Pineapple','Apple','Orange'], ['Banana'])");
      output(mgr);
    }
  }
}
