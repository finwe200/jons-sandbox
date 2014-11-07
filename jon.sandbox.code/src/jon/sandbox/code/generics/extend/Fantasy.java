package jon.sandbox.code.generics.extend;

public class Fantasy<T extends Adventure> extends Genre<T>
{
  public Fantasy(T item)
  {
    super(item);
  }

  public String adventure()
  {
    return m_item.adventure();
  }
}
