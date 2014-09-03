package jon.sandbox.code.list;

public class ListNode<T> //implements Comparable<T>
{
  public ListNode(Comparable<T> data)
  {
    super();

    if (data == null)
    {
      throw new IllegalArgumentException(
        "Cannot pass null data to ListNode constructor!");
    }

    m_data = data;
    m_next = null;
  }

  public Comparable<T> getData()
  {
    return m_data;
  }

  @SuppressWarnings("unchecked")
  public int compareTo(Comparable<T> data)
  {
    return m_data.compareTo((T)data);
  }

  private final Comparable<T> m_data;
  ListNode<T> m_next;
}
