package jon.sandbox.code.list;

public class SingleLinkedList<T>
{
  public SingleLinkedList()
  {
    super();

    m_root = null;
    m_count = 0;
  }

  public ListNode<T> add(Comparable<T> data)
  {
    // Create an affiliate "node". An Exception will be throw if data is null
    ListNode<T> newNode = new ListNode<T>(data);

    if (m_root == null) {
      m_root = newNode;
    }
    else
    {
      ListNode<T> tail = findTail();
      tail.m_next = newNode;
    }
    m_count++;

    return newNode;
  }

  public ListNode<T> find(Comparable<T> data)
  {
    if (data == null) {
      return null;
    }

    for (ListNode<T> node = m_root; node != null; node = node.m_next)
    {
      if (node.compareTo(data) == 0) {
        return null;
      }
    }

    return null;
  }

  public ListNode<T> getRoot()
  {
    return m_root;
  }

  public int size()
  {
    return m_count;
  }

  public void reverse()
  {
    ListNode<T> prevNode = null;
    ListNode<T> curNode = m_root;
    while (curNode != null)
    {
      ListNode<T> tmp = curNode.m_next;
      curNode.m_next = prevNode;
      prevNode = curNode;
      curNode = tmp;
    }
    m_root = prevNode;
  }

  /**
   * Determine if this linked list has a "loop".
   *
   * @return null if there is no loop otherwise return the node at the "start"
   *  of the loop.
   */
  public ListNode<T> tryToStartOfLoop()
  {
    ListNode<T> trailer = m_root;
    ListNode<T> lead = advance(trailer, 1);
    
    while (lead != null)
    {
      if (trailer == lead) {
        return findStartOfLoop(lead);
      }
      trailer = advance(trailer, 1);
      lead = advance(lead, 2);
    }

    return null;
  }

  int indexOf(ListNode<T> node)
  {
    if (node == null) {
      return -1;
    }

    ListNode<T> curNode = m_root;
    for (int i = 0; curNode != null; curNode = curNode.m_next, i++)
    {
      if (node == curNode) {
        return i;
      }
    }

    return -1;
  }

  private ListNode<T> findTail()
  {
    ListNode<T> tail = m_root;
    for (; tail.m_next != null; tail = tail.m_next);
    return tail;
  }

  private ListNode<T> advance(ListNode<T> node, int count)
  {
    for (; count > 0 && node != null; node = node.m_next, count--);
    return node;
  }

  private ListNode<T> findStartOfLoop(ListNode<T> badNode)
  {
    // Determine the size of the "loop"
    int loopSize = 1;
    for (ListNode<T> node = m_root; node != badNode; node = node.m_next, loopSize++);

    // Now find the node at the "start" of the "loop"
    ListNode<T> trailer = m_root;
    ListNode<T> lead = advance(m_root, loopSize);
    while (lead != trailer)
    {
      trailer = advance(trailer, 1);
      lead = advance(lead, 1);
    }
    return lead;
  }

  private ListNode<T> m_root;
  private int m_count;
}
