package jon.sandbox.code.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SortedStreamManager<E extends Comparable<E>>
{
  public SortedStreamManager(List<ISortedStream<E>> streams)
  {
     super();

     if (streams == null)
     {
       throw new IllegalArgumentException(
         "Cannot pass null array to SortedStreamManager constructor!");
     }

     // Initialize the list of "head inputs" (initial input from each stream)
     // Only manage streams that have input
     m_headInputs = new ArrayList<HeadInput<E>>(streams.size());
     for (ISortedStream<E> stream : streams)
     {
        if (stream != null && stream.hasNext()) {
          m_headInputs.add(new HeadInput<E>(stream));
        }
     }
  }
 
  public boolean hasNext()
  {
    return (!m_headInputs.isEmpty());
  }

  /**
   * This implementation assumes no Stream will contain null elements!
   * (otherwise more error handling is required)
   * 
   * @return
   */
  public Comparable<E> next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException(
        "SortedStreamManager.getNex() cannot return an element!");
    }

    // Get minimum value from the array of "head inputs"
    E minElement = m_headInputs.get(0).peek();
    int minHeadInputIndex = 0;
    for (int i = 1, n = m_headInputs.size(); i < n; i++)
    {
      E curElement = m_headInputs.get(i).peek();
      if (minElement != null && (curElement == null || curElement.compareTo(minElement) < 0))
      {
        minElement = curElement;
        minHeadInputIndex = i;
      }
    }
    
    // Get next input from the stream with the minimum input. If that stream
    // is exhausted then remove the stream from the list of "head input streams"
    HeadInput<E> minHeadInput = m_headInputs.get(minHeadInputIndex);
    if (minHeadInput.hasNext()) {
      minHeadInput.next();
    }
    else {
      m_headInputs.remove(minHeadInputIndex);
    }

   return minElement;
  }

  private class HeadInput<E1 extends Comparable<E>> implements ISortedStream<E>
  {
    public HeadInput(ISortedStream<E> stream)
    {
      super();
      if (stream == null || !stream.hasNext())
      {
        throw new IllegalArgumentException(
          "Cannot pass null array to HeadInput constructor!");
      }
      m_stream = stream;
      m_headElement = stream.next();
    }

    @Override
    public boolean hasNext()
    {
      return m_stream.hasNext();
    }

    @Override
    public E next()
    {
      m_headElement = m_stream.next();
      return m_headElement;
    }

    public E peek()
    {
      return m_headElement;
    }

    private final ISortedStream<E> m_stream;
    private E m_headElement;
  };

  final ArrayList<HeadInput<E>> m_headInputs;
}

