package jon.sandbox.code.tree;

public class PrintVisitor<T> implements ITreeVisitor<T>
{
  @Override
  public ITreeVisitor.VisitStatus visit(BinaryTreeNode<T> node)
  {
    System.out.println(node.getData().toString());
    return VisitStatus.eContinue;
  }
}
