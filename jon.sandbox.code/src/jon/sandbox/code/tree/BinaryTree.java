package jon.sandbox.code.tree;

import java.util.ArrayDeque;


public class BinaryTree<T>
{
  public BinaryTree()
  {
    super();

    m_root = null;
  }

  public BinaryTreeNode<T> add(Comparable<T> data)
  {
    // Create an affiliate "node". An Exception will be throw in data is null
    BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(data);

    if (m_root == null) {
      m_root = newNode;
    }
    else
    {
      BinaryTreeNode<T> parent = findParent(m_root, newNode);
      int comp = parent.compareTo(newNode.getData());
      if (comp > 0) {
        parent.m_leftNode = newNode;
      } else {
        parent.m_rightNode = newNode;
      }
    }

    return newNode;
  }

  public BinaryTreeNode<T> find(Comparable<T> data)
  {
    return (data == null) ? null : find(m_root, data);
  }

  public void inOrderTraversal(ITreeVisitor<T> visitor)
  {
    inOrderTraversal(m_root,visitor);
  }

  public void preOrderTraversal(ITreeVisitor<T> visitor)
  {
    preOrderTraversal(m_root, visitor);
  }

  public void postOrderTraversal(ITreeVisitor<T> visitor)
  {
    postOrderTraversal(m_root, visitor);
  }

  public void breathFirstTraversal(ITreeVisitor<T> visitor)
  {
    if (m_root == null) {
      return;
    }

    ArrayDeque<BinaryTreeNode<T>> queue = new ArrayDeque<BinaryTreeNode<T>>(10);
    queue.addFirst(m_root);

    while (!queue.isEmpty())
    {
      BinaryTreeNode<T> node = queue.removeFirst();
      visitor.visit(node);

      if (node.m_leftNode != null) {
        queue.addLast(node.m_leftNode);
      }
      if (node.m_rightNode != null) {
        queue.addLast(node.m_rightNode);
      }
    }
  }

  public void print()
  {
    inOrderTraversal(m_root, new PrintVisitor<T>());
  }

  public BinaryTreeNode<T> getRoot()
  {
    return m_root;
  }

  private BinaryTreeNode<T> find(BinaryTreeNode<T> node, Comparable<T> data)
  {
    if (node == null) {
      return null;
    }

    int comp = node.compareTo(data);
    return (comp == 0) ? node :
      (comp > 0) ? find(node.m_leftNode, data) : find(node.m_rightNode, data);
  }

  private void inOrderTraversal(BinaryTreeNode<T> node, ITreeVisitor<T> visitor)
  {
    if (node == null) {
      return;
    }

    inOrderTraversal(node.m_leftNode, visitor);
    visitor.visit(node);
    inOrderTraversal(node.m_rightNode, visitor);
  }

  private void preOrderTraversal(BinaryTreeNode<T> node, ITreeVisitor<T> visitor)
  {
    if (node == null) {
      return;
    }

    visitor.visit(node);
    preOrderTraversal(node.m_leftNode, visitor);
    preOrderTraversal(node.m_rightNode, visitor);
  }

  private void postOrderTraversal(BinaryTreeNode<T> node, ITreeVisitor<T> visitor)
  {
    if (node == null) {
      return;
    }

    postOrderTraversal(node.m_leftNode, visitor);
    postOrderTraversal(node.m_rightNode, visitor);
    visitor.visit(node);
  }

  private BinaryTreeNode<T> findParent(BinaryTreeNode<T> currentNode, BinaryTreeNode<T> newNode)
  {
    int comp = currentNode.compareTo(newNode.getData());
    if (comp > 0)
    {
      return (currentNode.m_leftNode == null) ?
        currentNode : findParent(currentNode.m_leftNode, newNode);
    }
    if (comp < 0)
    {
      return (currentNode.m_rightNode == null) ?
        currentNode : findParent(currentNode.m_rightNode, newNode);
    }

    throw new IllegalArgumentException(
      "Cannot duplicate node-data to BinaryTreeNode.add()! Data=" + newNode.toString());
  }

  private BinaryTreeNode<T> m_root;
}
