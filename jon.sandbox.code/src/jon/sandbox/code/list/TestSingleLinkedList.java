package jon.sandbox.code.list;

import static org.junit.Assert.assertEquals;
import jon.sandbox.code.basic.Fruit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSingleLinkedList
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
      SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
      assertEquals(null, list.getRoot());
      assertEquals(0, list.size());
    }

    {
      SingleLinkedList<Fruit> list = new SingleLinkedList<Fruit>();
      assertEquals(null, list.getRoot());
      assertEquals(0, list.size());
    }
  }

  @Test
  public void testAdd()
  {
    {
      SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();

      ListNode<Integer> n10 = list.add(10);
      assertEquals(1, list.size());
      assertEquals(n10, list.getRoot());
      assertEquals(0, list.indexOf(n10));

      ListNode<Integer> node = list.add(21);
      assertEquals(2, list.size());
      assertEquals(n10, list.getRoot());
      assertEquals(1, list.indexOf(node));

      node = list.add(6);
      assertEquals(3, list.size());
      assertEquals(n10, list.getRoot());
      assertEquals(2, list.indexOf(node));

      node = list.add(-672365);
      assertEquals(4, list.size());
      assertEquals(n10, list.getRoot());
      assertEquals(3, list.indexOf(node));
    }

    {
      SingleLinkedList<Fruit> list = new SingleLinkedList<Fruit>();
      
      ListNode<Fruit> kiwi = list.add(new Fruit("Kiwi"));
      assertEquals(1, list.size());
      assertEquals(kiwi, list.getRoot());
      assertEquals(0, list.indexOf(kiwi));

      ListNode<Fruit> node = list.add(new Fruit("Cantaloupe"));
      assertEquals(2, list.size());
      assertEquals(kiwi, list.getRoot());
      assertEquals(1, list.indexOf(node));

      node = list.add(new Fruit("Banana"));
      assertEquals(3, list.size());
      assertEquals(kiwi, list.getRoot());
      assertEquals(2, list.indexOf(node));

      node = list.add(new Fruit("Grapefruit"));
      assertEquals(4, list.size());
      assertEquals(kiwi, list.getRoot());
      assertEquals(3, list.indexOf(node));

      node = list.add(new Fruit("Lemon"));
      assertEquals(5, list.size());
      assertEquals(kiwi, list.getRoot());
      assertEquals(4, list.indexOf(node));

      node = list.add(new Fruit("Pineapple"));
      assertEquals(6, list.size());
      assertEquals(kiwi, list.getRoot());
      assertEquals(5, list.indexOf(node));

      node = list.add(new Fruit("Watermelon"));
      assertEquals(7, list.size());
      assertEquals(kiwi, list.getRoot());
      assertEquals(6, list.indexOf(node));
    }
  }

  @Test
  public void testTryToStartOfLoop()
  {
    {
      SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();

      ListNode<Integer> node1 = list.add(10);
      ListNode<Integer> node2 = list.add(-21);
      ListNode<Integer> node3 = list.add(68237);
      ListNode<Integer> node4 = list.add(-672365);
      ListNode<Integer> node5 = list.add(42);
      ListNode<Integer> node6 = list.add(73);
      assertEquals(null, list.tryToStartOfLoop());

      node6.m_next = node5;
      assertEquals(node5, list.tryToStartOfLoop());
      
      node4.m_next = node1;
      assertEquals(node1, list.tryToStartOfLoop());

      node3.m_next = node2;
      assertEquals(node2, list.tryToStartOfLoop());

      node1.m_next = node1;
      assertEquals(node1, list.tryToStartOfLoop());    
    }

    {
      SingleLinkedList<Fruit> list = new SingleLinkedList<Fruit>();

      ListNode<Fruit> node1 = list.add(new Fruit("Apple"));
      ListNode<Fruit> node2 = list.add(new Fruit("Strawberry"));
      ListNode<Fruit> node3 = list.add(new Fruit("Honey Dew"));
      ListNode<Fruit> node4 = list.add(new Fruit("Watermelon"));
      ListNode<Fruit> node5 = list.add(new Fruit("Grape"));
      ListNode<Fruit> node6 = list.add(new Fruit("Rasberry"));
      assertEquals(null, list.tryToStartOfLoop());

      node6.m_next = node5;
      assertEquals(node5, list.tryToStartOfLoop());
      
      node4.m_next = node1;
      assertEquals(node1, list.tryToStartOfLoop());

      node3.m_next = node2;
      assertEquals(node2, list.tryToStartOfLoop());

      node1.m_next = node1;
      assertEquals(node1, list.tryToStartOfLoop());    
    }
  }
}