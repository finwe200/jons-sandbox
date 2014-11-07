package jon.sandbox.code.generics.extend;

public class Bounded extends Story implements Action, Adventure
{
  @Override
  public String action()
  {
    return "Action";
  }

  @Override
  public String adventure()
  {
    return "Adventure";
  }
}
