package jon.sandbox.code.basic;

import java.lang.reflect.Array;
import java.util.HashMap;

public class Intersection<T>
{
  @SuppressWarnings("unchecked")
  public Intersection(Class<T> clazz, T[] array1, T[] array2)
  {
    // Put all elements in the 1st array into a Hash-map (duplicates will be lost)
    HashMap<T, T> map1 = new HashMap<T, T>(array1.length);
    for (T i : array1)
    {
      map1.put(i, i);
    }
    
    // Only store elements contained in both arrays (and filter out duplicates)
    HashMap<T, T> outputMap = new HashMap<T, T>(map1.size());
    for (T i : array2)
    {
      if (map1.containsKey(i)) {
        outputMap.put(i, i); // This will filter out duplicates
      }
    }

    // Convert output-map to an array
    m_class = clazz;
    m_intersection = (T[])Array.newInstance(m_class, outputMap.size());
    int i = 0;
    for (T val : outputMap.values()) {
      m_intersection[i++] = val;
    }
  }

  public T[] getIntersection()
  {
    return m_intersection;
  }

  private final Class<T> m_class;
  private final T[] m_intersection;
}
