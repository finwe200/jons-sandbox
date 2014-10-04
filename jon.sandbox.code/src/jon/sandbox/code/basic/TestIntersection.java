package jon.sandbox.code.basic;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestIntersection
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
  public void testGetIntersection()
  {
    {
      Integer[] a1 = new Integer[]{-8, 0, 3, 99, 734267634, 99, 3, 0, -8};
      Integer[] a2 = new Integer[]{-664, -8, 0, 734267634, 0, -8, 2, 2};
      Intersection<Integer> intersection = new Intersection<Integer>(Integer.class, a1, a2);
      Integer[] commonUniqueElements = intersection.getIntersection();

      assertEquals(3, commonUniqueElements.length);

      assertEquals(true, contains(commonUniqueElements, -8));
      assertEquals(true, contains(commonUniqueElements, 0));
      assertEquals(true, contains(commonUniqueElements, 734267634));
      
      assertEquals(false, contains(commonUniqueElements, 3));
      assertEquals(false, contains(commonUniqueElements, 99));
      assertEquals(false, contains(commonUniqueElements, 2));
    }
  }

  private <T> boolean contains(T[] elements, T element)
  {
    for (T cur : elements)
    {
      if (cur.equals(element)) {
        return true;
      }
    }
    return false;
  }
}
