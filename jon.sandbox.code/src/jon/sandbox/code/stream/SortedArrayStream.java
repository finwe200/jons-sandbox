package jon.sandbox.code.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SortedArrayStream<E extends Comparable<E>> implements ISortedStream<E>
{
  public SortedArrayStream(E[] elements)
  {
    this(elements, true);
  }

  /**
   *  This constructor assumes there are no null elements.
   *
   * @param elements
   * @param doSort
   */
  public SortedArrayStream(E[] elements, boolean doSort)
  {
    super();
    if (elements == null) {
      throw new IllegalArgumentException("Cannot pass null array to SortedArrayStream constructor!");
    }

    m_elements = Arrays.copyOf(elements, elements.length);
    if (doSort) {
      Arrays.sort(m_elements);
    }
    m_index = 0;
  }

  /**
   * Supply a comparator that can handle null elements!
   *
   * @param elements
   * @param comparator
   */
  public SortedArrayStream(E[] elements, Comparator<E> comparator)
  {
    super();
    if (elements == null) {
      throw new IllegalArgumentException("Cannot pass null array to SortedArrayStream constructor!");
    }
    if (comparator == null) {
      throw new IllegalArgumentException("Cannot pass null comparator to SortedArrayStream constructor!");
    }

    // TODO: Filter out null elements or throw an IllegalArgumentException
    
    m_elements = Arrays.copyOf(elements, elements.length);
    Arrays.sort(m_elements, comparator);
    m_index = 0;
  }

  @Override
  public boolean hasNext()
  {
    return (m_index < m_elements.length);
  }

  @Override
  public E next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException(
        "SortedArrayStream.getNex() cannot return an element!");
    }
    return m_elements[m_index++];
  }

  private final E[] m_elements;
  private int m_index;
}

