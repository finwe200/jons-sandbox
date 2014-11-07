package jon.sandbox.code.generics.extend;

import java.util.ArrayList;
import java.util.List;

public class MythicalFiction <T extends Action & Adventure> extends Fantasy<T>
{
  MythicalFiction(T item)
  {
    super(item);
  }
  
  String action()
  {
    return m_item.action();
  }
  
  public List<T> getElements()
  {
    List<T> list = new ArrayList<T>(1);
    list.add(m_item);
    return list;
  }
}
