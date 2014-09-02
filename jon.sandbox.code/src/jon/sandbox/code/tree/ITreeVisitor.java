package jon.sandbox.code.tree;

public interface ITreeVisitor<T>
{
  public enum VisitStatus
  {
    eContinue
    {
      @Override
      public String getName()
      {
        return "Continue";
      }
      
    },
    eAbort
    {
      @Override
      public String getName()
      {
        return "Abort";
      }
    };

    public abstract String getName();
  };

  public VisitStatus visit(BinaryTreeNode<T> node);
}
