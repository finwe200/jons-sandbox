package jon.sandbox.code.questions.ai;

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
  public void testClosestTo1000()
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    {
      int arg1 = 1000, arg2 = 999;
      assertEquals(arg1, sc.closestTo1000(arg1, arg2));
    }

    {
      int arg1 = 1002, arg2 = 999;
      assertEquals(arg2, sc.closestTo1000(arg1, arg2));
    }

    {
      int arg1 = -867, arg2 = -866;
      assertEquals(arg2, sc.closestTo1000(arg1, arg2));
    }
    
    {
      int arg1 = 200, arg2 = 200;
      assertEquals(arg2, sc.closestTo1000(arg1, arg2));
    }

    {
      int arg1 = 990, arg2 = 1010;
      assertEquals(arg1, sc.closestTo1000(arg1, arg2));
    }

  }

  @Test
  public void testDoubleEachCharacterInString()
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    assertEquals("aabbcc", sc.doubleEachCharacterInString("abc"));
    assertEquals("", sc.doubleEachCharacterInString(""));
    assertEquals("", sc.doubleEachCharacterInString(null));
    assertEquals("11??TTtt", sc.doubleEachCharacterInString("1?Tt"));
  }

  @Test
  public void testContainsTwoContiguousSubArraysWithEqualSums()
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    assertEquals(false, sc.containsTwoContiguousSubArraysWithEqualSums(null));
    assertEquals(false, sc.containsTwoContiguousSubArraysWithEqualSums(new int[0]));
    assertEquals(false, sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{10}));

    assertEquals(
      true,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{10,15,20,2,2,1})
    );

    assertEquals(
      true,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{-10,-15,-20,-2,-2,-1})
    );

    assertEquals(
      false,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{10,15,20,2,2,2})
    );

    assertEquals(
      true,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{0,0,0,0})
    );

    assertEquals(
      false,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{4,5,1})
    );

    assertEquals(
      true,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{4,1,5})
    );

    assertEquals(
      false,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{12,16,6,2,4,4})
    );

    assertEquals(
      false,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{12,1,11,3,32,13})
    );

    assertEquals(
      false,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{12,7,38,12,20,13})
    );

    assertEquals(
      false,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{12,7,38,12,101,32})
    );

    assertEquals(
      true,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{12,7,38,12,32,101})
    );

    assertEquals(
      false,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{12,7,38,12,32,101,400,52})
    );

    assertEquals(
      false,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{122,70,58,-31,32,400,201,52})
    );
  }

  @Test
  public void testContainsTwoSubArraysWithEqualSums()
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    assertEquals(false, sc.containsTwoSubArraysWithEqualSums(null));
    assertEquals(false, sc.containsTwoSubArraysWithEqualSums(new int[0]));
    assertEquals(false, sc.containsTwoSubArraysWithEqualSums(new int[]{10}));

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{10,15,20,2,2,1})
    );

    assertEquals(
      true,
      sc.containsTwoContiguousSubArraysWithEqualSums(new int[]{-10,-15,-20,-2,-2,-1})
    );

    assertEquals(
      false,
      sc.containsTwoSubArraysWithEqualSums(new int[]{10,15,20,2,2,2})
    );

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{0,0,0,0})
    );

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{4,5,1})
    );

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{4,1,5})
    );

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{12,16,6,2,4,4})
    );
    
    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{12,1,11,3,32,13})
    );

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{12,7,38,12,20,13})
    );

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{12,7,38,12,101,32})
    );

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{12,7,38,12,32,101})
    );

    assertEquals(
      false,
      sc.containsTwoSubArraysWithEqualSums(new int[]{12,7,38,12,32,101,400,52})
    );

    assertEquals(
      true,
      sc.containsTwoSubArraysWithEqualSums(new int[]{122,70,58,-31,32,400,201,52})
    );
  }

  @Test
  public void testInsertAsteriskBetweenEachCharacter()
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    assertEquals("", sc.insertAsteriskBetweenEachCharacter(null));
    assertEquals("", sc.insertAsteriskBetweenEachCharacter(""));
    assertEquals("a*b*c", sc.insertAsteriskBetweenEachCharacter("abc"));
    assertEquals("a", sc.insertAsteriskBetweenEachCharacter("a"));
    assertEquals("1*2*3*a*b*c*4*5*6", sc.insertAsteriskBetweenEachCharacter("123abc456"));
  }

  @Test
  public void testInsertAsteriskBetweenEachCharacter2()
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    assertEquals("", sc.insertAsteriskBetweenEachCharacter2(null));
    assertEquals("", sc.insertAsteriskBetweenEachCharacter2(""));
    assertEquals("a*b*c", sc.insertAsteriskBetweenEachCharacter2("abc"));
    assertEquals("a", sc.insertAsteriskBetweenEachCharacter2("a"));
    assertEquals("1*2*3*a*b*c*4*5*6", sc.insertAsteriskBetweenEachCharacter2("123abc456"));
  }

  @Test
  public void testToHexadecimal()
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    assertEquals("0", sc.toHexadecimal(0));
    assertEquals("2A8", sc.toHexadecimal(680));
    assertEquals("ABCDEF", sc.toHexadecimal(11259375));
    assertEquals("-ABCDEF", sc.toHexadecimal(-11259375));
  }

  @Test
  public void testToBinary()
  {
    ScreeningQuestions sc = new ScreeningQuestions();

    assertEquals("0", sc.toBinary(0));
    assertEquals("10000", sc.toBinary(16));
    assertEquals("1010101000", sc.toBinary(680));
    assertEquals("-101010111100110111101111", sc.toBinary(-11259375));
  }
}
