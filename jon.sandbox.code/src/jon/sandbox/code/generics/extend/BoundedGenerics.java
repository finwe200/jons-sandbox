package jon.sandbox.code.generics.extend;

public class BoundedGenerics <T extends Story & Action & Adventure> extends MythicalFiction<T>
{
  public BoundedGenerics(T item)
  {
    super(item);
  }

  public String plot()
  {
    return m_item.plot();
  }
}
