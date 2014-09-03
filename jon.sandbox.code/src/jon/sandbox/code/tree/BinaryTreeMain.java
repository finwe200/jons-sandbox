package jon.sandbox.code.tree;

import jon.sandbox.code.basic.Fruit;

public class BinaryTreeMain extends Object
{
  public BinaryTreeMain()
  {
    super();
  }

  static public void main(String[] args)
  {
    {
      PrintVisitor<Integer> visitor = new PrintVisitor<Integer>();
      BinaryTree<Integer> tree = new BinaryTree<Integer>();
      tree.add(10);
      tree.add(6);
      tree.add(4);
      tree.add(8);
      tree.add(14);
      tree.add(12);
      tree.add(16);

      System.out.println("In-order traversal:");
      tree.print();

      System.out.println("\nPre-order traversal:");
      tree.preOrderTraversal(visitor);

      System.out.println("\nPost-order traversal:");
      tree.postOrderTraversal(visitor);

      System.out.println("\nBreath-first traversal:");
      tree.breathFirstTraversal(visitor);
    }

    {
      PrintVisitor<Fruit> visitor = new PrintVisitor<Fruit>();
      BinaryTree<Fruit> tree = new BinaryTree<Fruit>();
      tree.add(new Fruit("Kiwi"));
      tree.add(new Fruit("Cantaloupe"));
      tree.add(new Fruit("Banana"));
      tree.add(new Fruit("Grapefruit"));
      tree.add(new Fruit("Lemon"));
      tree.add(new Fruit("Pineapple"));
      tree.add(new Fruit("Watermelon"));

      System.out.println("\nIn-order traversal:");
      tree.print();

      System.out.println("\nBreath-first traversal:");
      tree.breathFirstTraversal(visitor);
    }
  }
}
