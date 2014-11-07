package jon.sandbox.code.generics.extend;

import java.util.List;

public class Generics
{
  public static void main(String[] args)
  {
    BoundedGenerics<Bounded> container = new BoundedGenerics<Bounded>(new Bounded());
    System.out.println(container.action());
    System.out.println(container.adventure());
    System.out.println(container.plot());
  }
  
  public List<? extends Action> getElements()
  {
    return null;
  }
}
