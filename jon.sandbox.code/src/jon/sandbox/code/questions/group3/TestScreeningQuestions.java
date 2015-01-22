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
    int[] ints = {10, 9, 7, 16, 12, 11, 2};

    assertEquals(true, sq.containsSumOfTarget(ints, 13));
    assertEquals(true, sq.containsSumOfTarget(ints, 12));
    assertEquals(false, sq.containsSumOfTarget(ints, 10));
    assertEquals(true, sq.containsSumOfTarget(ints, 14));
    assertEquals(false, sq.containsSumOfTarget(ints, 15));
  }

  @Test
  public void testContainsSumOfTarget2()
  {
    ScreeningQuestions sq = new ScreeningQuestions();
    int[] ints = {10, 9, 7, 16, 12, 11, 2};

    assertEquals(true, sq.containsSumOfTarget2(ints, 13));
    assertEquals(true, sq.containsSumOfTarget2(ints, 12));
    assertEquals(false, sq.containsSumOfTarget2(ints, 10));
    assertEquals(true, sq.containsSumOfTarget2(ints, 14));
    assertEquals(false, sq.containsSumOfTarget2(ints, 15));
  }
}
