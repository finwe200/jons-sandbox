package jon.sandbox.code.list;

import jon.sandbox.code.basic.Fruit;

public class SingleLinkedListMain extends Object
{
  public SingleLinkedListMain()
  {
    super();
  }

  static public <T> void output(SingleLinkedList<T> list)
  {
    System.out.print("[");
    boolean firstElement = true;
    for (ListNode<T> node = list.getRoot(); node != null;
         node = node.m_next, firstElement = false)
    {
      if (!firstElement) {
        System.out.print(','); 
      }
      System.out.print(node.getData().toString());
    }
    System.out.print("]");
  }

  static public void main(String[] args)
  {
    {
      SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
      list.add(10);
      list.add(21);
      list.add(-6);
      list.add(100332);
      list.add(0);

      System.out.print("Original List: ");
      output(list);
      
      list.reverse();
      System.out.print("\nReversed List: ");
      output(list);

      list.reverse();
      System.out.print("\nTwice Reversed List: ");
      output(list);
    }

    {
      SingleLinkedList<Fruit> list = new SingleLinkedList<Fruit>();
      list.add(new Fruit("Kiwi"));
      list.add(new Fruit("Cantaloupe"));
      list.add(new Fruit("Banana"));
      list.add(new Fruit("Grapefruit"));
      list.add(new Fruit("Lemon"));
      list.add(new Fruit("Pineapple"));
      list.add(new Fruit("Watermelon"));

      System.out.print("\n\nOriginal List: ");
      output(list);
      
      list.reverse();
      System.out.print("\nReversed List: ");
      output(list);

      list.reverse();
      System.out.print("\nTwice Reversed List: ");
      output(list);
    }
  }
}
