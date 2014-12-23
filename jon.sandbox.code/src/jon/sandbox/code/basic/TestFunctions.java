package jon.sandbox.code.basic;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFunctions
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
  public void testFindLargestInts()
  {
    // Confirm that an input of "null" returns an empty array
    {
      int[] rtn = Functions.findLargestInts(3, null);
      assertEquals(0, rtn.length);
    }

    // Confirm that an invalid input for the "number of integers to find"
    // returns an empty array
    {
      int[] rtn = Functions.findLargestInts(0, new int[]{12, 2});
      assertEquals(0, rtn.length);
      rtn = Functions.findLargestInts(-4, new int[]{12, 2});
      assertEquals(0, rtn.length);
    }

    {
      int[] rtn = Functions.findLargestInts(3, new int[]{12, 2});
      assertEquals(2, rtn.length);
      assertEquals(true, contains(rtn, 2));
      assertEquals(true, contains(rtn, 12));
    }

    {
      int[] rtn = Functions.findLargestInts(4, new int[]{20, 18, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 4});
      assertEquals(4, rtn.length);
      assertEquals(true, contains(rtn, 9));
      assertEquals(true, contains(rtn, 10));
      assertEquals(true, contains(rtn, 18));
      assertEquals(true, contains(rtn, 20));
    }

    // Confirm that duplicates will be filtered!
    {
      int[] rtn = Functions.findLargestInts(5, new int[]{20, 18, 1, 2, 3, 20, 5, 6, 7, 20, 9, 10, 20, 4, -67, -18});
      assertEquals(5, rtn.length);
      assertEquals(true, contains(rtn, 7));
      assertEquals(true, contains(rtn, 9));
      assertEquals(true, contains(rtn, 10));
      assertEquals(true, contains(rtn, 18));
      assertEquals(true, contains(rtn, 20));
    }

    {
      int[] rtn = Functions.findLargestInts(4, new int[]{-10, -40, -999, -500, -67, 2, 0});
      assertEquals(4, rtn.length);
      assertEquals(true, contains(rtn, -40));
      assertEquals(true, contains(rtn, -10));
      assertEquals(true, contains(rtn, 0));
      assertEquals(true, contains(rtn, 2));
    }
  }

  private boolean contains(int[] elements, int element)
  {
    for (int cur : elements)
    {
      if (cur == element) {
        return true;
      }
    }
    return false;
  }
}
