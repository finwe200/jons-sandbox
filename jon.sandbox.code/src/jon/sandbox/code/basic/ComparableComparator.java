package jon.sandbox.code.basic;

import java.util.Comparator;

public class ComparableComparator<T>
  implements
    Comparator<T>
{
  public ComparableComparator()
  {
  }

  @SuppressWarnings("unchecked")
  @Override
  public int compare(T o1, T o2)
  {
    if (o1 == null) {
      return (o2 == null) ? 0 : -1;
    }

    if (o2 == null) {
      return 1;
    }

    if (o1 instanceof Comparable<?> && o2 instanceof Comparable<?>) {
      return ((Comparable<T>)o1).compareTo(o2);
    }

    return 0;
  }
}
