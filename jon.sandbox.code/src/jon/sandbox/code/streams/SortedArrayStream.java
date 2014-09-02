package jon.sandbox.code.streams;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SortedArrayStream<E extends Comparable<E>> implements ISortedStream<E>
{
  public SortedArrayStream(E[] elements)
  {
    this(elements, true);
  }

  public SortedArrayStream(E[] elements, boolean doSort)
  {
    super();
    if (elements == null) {
      throw new IllegalArgumentException("Cannot pass null array to SortedArrayStream constructor!");
    }

    // TODO: Filter out null elements or throw an IllegalArgumentException
    
    m_elements = Arrays.copyOf(elements, elements.length);
    if (doSort) {
      Arrays.sort(m_elements);
    }
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

