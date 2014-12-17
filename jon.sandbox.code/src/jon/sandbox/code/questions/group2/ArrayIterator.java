package jon.sandbox.code.questions.group2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<E> implements Iterator<E>
{
  public ArrayIterator(E[] values)
  {
    super();
    m_values = values;
    m_index = 0;
  }

  @Override
  public boolean hasNext()
  {
    return (m_index < m_values.length);
  }

  @Override
  public E next()
  {
    if (!hasNext())
    {
      throw new NoSuchElementException("ArrayIterator.next() has nothing to return.");
    }
    return m_values[m_index++];
  }

  @Override
  public void remove()
  {
    throw new UnsupportedOperationException("ArrayIterator.remove() is not supported.");
  }

  private final E[] m_values;
  private int m_index;
};

