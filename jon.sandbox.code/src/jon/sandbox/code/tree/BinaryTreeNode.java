package jon.sandbox.code.tree;

public class BinaryTreeNode<T> implements Comparable<T>
{
  public BinaryTreeNode(Comparable<T> data)
  {
    super();

    if (data == null)
    {
      throw new IllegalArgumentException(
        "Cannot pass null data to BinaryTreeNode constructor!");
    }

    m_data = data;
    m_leftNode = null;
    m_rightNode = null;
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

  @Override
  public int compareTo(T data)
  {
    return m_data.compareTo(data);
  }

  private final Comparable<T> m_data;
  BinaryTreeNode<T> m_leftNode;
  BinaryTreeNode<T> m_rightNode;
}
