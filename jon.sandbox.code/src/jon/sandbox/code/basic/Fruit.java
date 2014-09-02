package jon.sandbox.code.basic;

public class Fruit implements Comparable<Fruit>
{
  public Fruit(String name)
  {
    super();
    if (name == null)
    {
      throw new IllegalArgumentException(
        "Cannot pass null name to Fruit constructor!");
    }
    m_name = name;
  }

  @Override
  public int compareTo(Fruit o)
  {
    int rtn = m_name.compareToIgnoreCase(o.m_name);
    return rtn;
  }

  @Override
  public String toString()
  {
    return m_name;
  }
  
  public String getName()
  {
    return m_name;
  }

  private final String m_name;
};

