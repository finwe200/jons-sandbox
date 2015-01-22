package jon.sandbox.code.questions.group3;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestScreeningQuestions
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
  public void testContainsSumOfTarget()
  {
    ScreeningQuestions sq = new ScreeningQuestions();

    {
      int[] ints = {10, 9, 7, 16, 12, 11, 2, 0, 11};

      assertEquals(false, sq.containsSumOfTarget(ints, 8));
      assertEquals(true, sq.containsSumOfTarget(ints, 10));
      assertEquals(true, sq.containsSumOfTarget(ints, 12));
      assertEquals(true, sq.containsSumOfTarget(ints, 13));
      assertEquals(true, sq.containsSumOfTarget(ints, 14));
      assertEquals(false, sq.containsSumOfTarget(ints, 15));
      assertEquals(true, sq.containsSumOfTarget(ints, 22));
      assertEquals(false, sq.containsSumOfTarget(ints, 24));
      assertEquals(true, sq.containsSumOfTarget(ints, 25));
      assertEquals(true, sq.containsSumOfTarget(ints, 27));
      assertEquals(false, sq.containsSumOfTarget(ints, 32));
    }

    {
      int[] ints = {10, 0, 1, 0};

      assertEquals(true, sq.containsSumOfTarget(ints, 0));
      assertEquals(true, sq.containsSumOfTarget(ints, 1));
      assertEquals(false, sq.containsSumOfTarget(ints, 2));
      assertEquals(false, sq.containsSumOfTarget(ints, 9));
      assertEquals(true, sq.containsSumOfTarget(ints, 10));
      assertEquals(true, sq.containsSumOfTarget(ints, 11));
      assertEquals(false, sq.containsSumOfTarget(ints, 12));
      assertEquals(false, sq.containsSumOfTarget(ints, 20));
    }

    {
      int[] ints = {10, 0, -1};

      try
      {
        sq.containsSumOfTarget(ints, 1);
      }
      catch (IllegalArgumentException e)
      {
      }
    }
  }

  @Test
  public void testContainsSumOfTarget2()
  {
    ScreeningQuestions sq = new ScreeningQuestions();

    {
      int[] ints = {10, 9, 7, 16, 12, 11, 2, 0, 11};
  
      assertEquals(false, sq.containsSumOfTarget2(ints, 8));
      assertEquals(true, sq.containsSumOfTarget2(ints, 10));
      assertEquals(true, sq.containsSumOfTarget2(ints, 12));
      assertEquals(true, sq.containsSumOfTarget2(ints, 13));
      assertEquals(true, sq.containsSumOfTarget2(ints, 14));
      assertEquals(false, sq.containsSumOfTarget2(ints, 15));
      assertEquals(true, sq.containsSumOfTarget2(ints, 22));
      assertEquals(false, sq.containsSumOfTarget2(ints, 24));
      assertEquals(true, sq.containsSumOfTarget2(ints, 25));
      assertEquals(true, sq.containsSumOfTarget2(ints, 27));
      assertEquals(false, sq.containsSumOfTarget2(ints, 32));
    }

    {
      int[] ints = {10, 0, 1, 0};

      assertEquals(true, sq.containsSumOfTarget2(ints, 0));
      assertEquals(true, sq.containsSumOfTarget2(ints, 1));
      assertEquals(false, sq.containsSumOfTarget2(ints, 2));
      assertEquals(false, sq.containsSumOfTarget2(ints, 9));
      assertEquals(true, sq.containsSumOfTarget2(ints, 10));
      assertEquals(true, sq.containsSumOfTarget2(ints, 11));
      assertEquals(false, sq.containsSumOfTarget2(ints, 12));
      assertEquals(false, sq.containsSumOfTarget2(ints, 20));
    }

    {
      int[] ints = {10, 0, -1};

      try
      {
        sq.containsSumOfTarget(ints, 1);
      }
      catch (IllegalArgumentException e)
      {
      }
    }
  }
}
