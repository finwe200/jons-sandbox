package jon.sandbox.code.generics.extend;

public class Genre<T>
{
  protected final T m_item;

  public Genre(T item)
  {
    m_item = item;
  }

  T getItem()
  {
    return m_item;
  }
}
