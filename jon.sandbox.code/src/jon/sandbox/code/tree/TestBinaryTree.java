package jon.sandbox.code.tree;

import static org.junit.Assert.assertEquals;
import jon.sandbox.code.basic.Fruit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBinaryTree
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
      BinaryTree<Integer> tree = new BinaryTree<Integer>();
      assertEquals(null, tree.getRoot());
    }
  }

  @Test
  public void testfind()
  {
    {
      BinaryTree<Integer> tree = new BinaryTree<Integer>();
      BinaryTreeNode<Integer> n10 = tree.add(10);
      BinaryTreeNode<Integer> n6 = tree.add(6);
      BinaryTreeNode<Integer> n4 = tree.add(4);
      BinaryTreeNode<Integer> n8 = tree.add(8);
      BinaryTreeNode<Integer> n14 = tree.add(14);
      BinaryTreeNode<Integer> n12 = tree.add(12);
      BinaryTreeNode<Integer> n17 = tree.add(17);

      assertEquals(null, tree.find(0));
      assertEquals(n10, tree.find(10));
      assertEquals(n6, tree.find(6));
      assertEquals(n4, tree.find(4));
      assertEquals(n8, tree.find(8));
      assertEquals(n14, tree.find(14));
      assertEquals(n12, tree.find(12));
      assertEquals(n17, tree.find(17));
      assertEquals(null, tree.find(16));
    }

    {
      BinaryTree<Fruit> tree = new BinaryTree<Fruit>();
      BinaryTreeNode<Fruit> kiwi = tree.add(new Fruit("Kiwi"));
      BinaryTreeNode<Fruit> cantaloupe = tree.add(new Fruit("Cantaloupe"));
      BinaryTreeNode<Fruit> banana = tree.add(new Fruit("Banana"));
      BinaryTreeNode<Fruit> grapefruit = tree.add(new Fruit("Grapefruit"));
      BinaryTreeNode<Fruit> lemon = tree.add(new Fruit("Lemon"));
      BinaryTreeNode<Fruit> pineapple = tree.add(new Fruit("Pineapple"));
      BinaryTreeNode<Fruit> watermelon = tree.add(new Fruit("Watermelon"));
      
      assertEquals(null, tree.find(new Fruit("Kumquat")));
      assertEquals(kiwi, tree.find(new Fruit("Kiwi")));
      assertEquals(cantaloupe, tree.find(new Fruit("Cantaloupe")));
      assertEquals(banana, tree.find(new Fruit("Banana")));
      assertEquals(grapefruit, tree.find(new Fruit("Grapefruit")));
      assertEquals(lemon, tree.find(new Fruit("Lemon")));
      assertEquals(pineapple, tree.find(new Fruit("Pineapple")));
      assertEquals(watermelon, tree.find(new Fruit("Watermelon")));
    }
  }
}
