package jon.sandbox.code.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import jon.sandbox.code.basic.ComparableComparator;
import jon.sandbox.code.basic.Fruit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSortedStreamManager
{
  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }
  
  @Before
  public void setUp() throws Exception
  {
  }
  
  @After
  public void tearDown() throws Exception
  {
  }
  
  @Test
  public void testConstruction()
  {
    {
      SortedStreamManager<Integer> mgr =
        new SortedStreamManager<Integer>(new ArrayList<ISortedStream<Integer>>(0));
      assertEquals(0, mgr.m_headInputs.size());
      assertEquals(false, mgr.hasNext());
    }

    {
      SortedArrayStream<Integer> s1 =
        new SortedArrayStream<Integer>(new Integer[]{4,1,25,3,5});
      SortedArrayStream<Integer> s2 =
        new SortedArrayStream<Integer>(new Integer[]{17,21,-6});
      SortedArrayStream<Integer> s3 = new SortedArrayStream<Integer>(new Integer[]{});
      SortedArrayStream<Integer> s4 = null;
      List<ISortedStream<Integer>> streams = new ArrayList<ISortedStream<Integer>>(10);
      streams.add(s1);
      streams.add(s2);
      streams.add(s3);
      streams.add(s4);
      SortedStreamManager<Integer> mgr = new SortedStreamManager<Integer>(streams);
      assertEquals(2, mgr.m_headInputs.size());
      assertEquals(true, mgr.hasNext());
    }

    try
    {
      new SortedStreamManager<Integer>(null);
      fail("Expected Exception from Iterator<LinkedObject<T>>.next() not thrown!");
    }
    catch (IllegalArgumentException e)
    {
    }
  }

  @Test
  public void testNext()
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

      Integer[] expected = {-6,1,3,4,5,17,21,25,1000};
      for (Integer i : expected)
      {
        assertEquals(true, mgr.hasNext());
        //assertEquals(0, i.compareTo((Integer)mgr.next()));
        assertEquals(true, isEqual(mgr.next(), i));

      }
      assertEquals(false, mgr.hasNext());
    }

    {
      SortedArrayStream<Fruit> s1 = null;
      SortedArrayStream<Fruit> s2 = new SortedArrayStream<Fruit>(
        new Fruit[]{new Fruit("Watermelon"), new Fruit("Apple")});
      SortedArrayStream<Fruit> s3 = new SortedArrayStream<Fruit>(new Fruit[]{});
      SortedArrayStream<Fruit> s4 = new SortedArrayStream<Fruit>(
        new Fruit[]{new Fruit("Pineapple"), new Fruit("Apple"), new Fruit("Orange")});
      SortedArrayStream<Fruit> s5 = new SortedArrayStream<Fruit>(
        new Fruit[]{null, new Fruit("Banana"), null}, new ComparableComparator<Fruit>());
      List<ISortedStream<Fruit>> streams = new ArrayList<ISortedStream<Fruit>>(10);
      streams.add(s1);
      streams.add(s2);
      streams.add(s3);
      streams.add(s4);
      streams.add(s5);
      SortedStreamManager<Fruit> mgr = new SortedStreamManager<Fruit>(streams);

      Fruit[] expected =
        {null, null, new Fruit("Apple"),new Fruit("Apple"),
         new Fruit("Banana"),new Fruit("Orange"),new Fruit("Pineapple"),
         new Fruit("Watermelon")};
      for (Fruit i : expected)
      {
        assertEquals(true, mgr.hasNext());
        //assertEquals(0, i.compareTo((Fruit)mgr.next()));
        assertEquals(true, isEqual(mgr.next(), i));
      }
      assertEquals(false, mgr.hasNext());
    }
  }
  
  private <T> boolean isEqual(Comparable<T> c1, T c2)
  {
    if (c1 == null || c2 == null) {
      return (c1 == c2);
    }
    return (c1.compareTo(c2) == 0);
  }
}
