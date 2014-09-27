package jon.sandbox.code.questions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jon.sandbox.code.basic.Fruit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestRemoveDuplicates
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
  public void testRemoveDuplicates()
  {
    // Normal case for Integers
    {
      Integer[] input = {1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17};
      Integer[] output = ScreeningQuestions.removeDuplicates(input);
      assertEquals(8, output.length);
      assertEquals(1, output[0].intValue());
      assertEquals(2, output[1].intValue());
      assertEquals(3, output[2].intValue());
      assertEquals(4, output[3].intValue());
      assertEquals(10, output[4].intValue());
      assertEquals(13, output[5].intValue());
      assertEquals(15, output[6].intValue());
      assertEquals(17, output[7].intValue());
    }

    // Normal case for Fruits
    {
      Fruit apple = new Fruit("Apple");
      Fruit banana = new Fruit("Banana");
      Fruit lemon = new Fruit("Lemon");
      Fruit[] input = {apple, apple, banana, banana, banana, lemon, lemon};
      Fruit[] output = ScreeningQuestions.removeDuplicates(input);
      assertEquals(3, output.length);
      assertEquals(apple, output[0]);
      assertEquals(banana, output[1]);
      assertEquals(lemon, output[2]);
    }

    // Empty Array
    {
      Integer[] input = {};
      Integer[] output = ScreeningQuestions.removeDuplicates(input);
      assertTrue(null == output);
    }

    // Negative test: Pass in null!
    {
      try 
      {
        ScreeningQuestions.removeDuplicates(null);
        fail("removeDuplicates(null) must thrown an IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
      }
      catch (Throwable e)
      {
        fail("removeDuplicates(null) must throw an IllegalArgumentException!");
      }
    }

    // Negative test: Pass in null elements!
    {
      try 
      {
        Integer[] input = {1, null, 2,};
        ScreeningQuestions.removeDuplicates(input);
        fail("removeDuplicates() with null elements must throw an IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
      }
      catch (Throwable e)
      {
        fail("removeDuplicates() with null elements must throw an IllegalArgumentException!");
      }
    }

    // Negative test: Pass in non-sorted elements!
    {
      try 
      {
        Integer[] input = {1, 3, 2};
        ScreeningQuestions.removeDuplicates(input);
        fail("removeDuplicates() with non-sorted elements must throw an IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
      }
      catch (Throwable e)
      {
        fail("removeDuplicates() with non-sorted elements must throw an IllegalArgumentException!");
      }
    }
  }

  @Test
  public void testRemoveDuplicates2()
  {
    // Normal case for Integers
    {
      Integer[] input = {1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17};
      Integer[] output = ScreeningQuestions.removeDuplicates2(input);
      assertEquals(8, output.length);
      assertEquals(1, output[0].intValue());
      assertEquals(2, output[1].intValue());
      assertEquals(3, output[2].intValue());
      assertEquals(4, output[3].intValue());
      assertEquals(10, output[4].intValue());
      assertEquals(13, output[5].intValue());
      assertEquals(15, output[6].intValue());
      assertEquals(17, output[7].intValue());
    }

    // Normal case for Fruits
    {
      Fruit apple = new Fruit("Apple");
      Fruit banana = new Fruit("Banana");
      Fruit lemon = new Fruit("Lemon");
      Fruit[] input = {apple, apple, banana, banana, banana, lemon, lemon};
      Fruit[] output = ScreeningQuestions.removeDuplicates2(input);
      assertEquals(3, output.length);
      assertEquals(apple, output[0]);
      assertEquals(banana, output[1]);
      assertEquals(lemon, output[2]);
    }

    // Empty Array
    {
      Integer[] input = {};
      Integer[] output = ScreeningQuestions.removeDuplicates2(input);
      assertTrue(null == output);
    }

    // Negative test: Pass in null!
    {
      try 
      {
        ScreeningQuestions.removeDuplicates2(null);
        fail("removeDuplicates2(null) must thrown an IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
      }
      catch (Throwable e)
      {
        fail("removeDuplicates2(null) must throw an IllegalArgumentException!");
      }
    }

    // Negative test: Pass in null elements!
    {
      try 
      {
        Integer[] input = {1, null, 2,};
        ScreeningQuestions.removeDuplicates2(input);
        fail("removeDuplicates2() with null elements must throw an IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
      }
      catch (Throwable e)
      {
        fail("removeDuplicates2() with null elements must throw an IllegalArgumentException!");
      }
    }

    // Negative test: Pass in non-sorted elements!
    {
      try 
      {
        Integer[] input = {1, 3, 2};
        ScreeningQuestions.removeDuplicates2(input);
        fail("removeDuplicates2() with non-sorted elements must throw an IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
      }
      catch (Throwable e)
      {
        fail("removeDuplicates2() with non-sorted elements must throw an IllegalArgumentException!");
      }
    }
  }
}
