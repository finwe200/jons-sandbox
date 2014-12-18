package jon.sandbox.eclipse.ui.model.library;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.Assert;

public class Book extends Object
{
  public static final String PROPERTY_TITLE = "book.title";

  public Book(Author author, String title)
  {
    super();

    Assert.isNotNull(author);
    Assert.isNotNull(title);

    m_propertyChangeSupport = new PropertyChangeSupport(this);
    m_author = author;
    m_title = title;
  }

  public void addPropertyChangeListener(
    String propertyName, PropertyChangeListener listener)
  {
    m_propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    m_propertyChangeSupport.removePropertyChangeListener(listener);
  }

  public Author getAuthor()
  {
    return m_author;
  }

  public String getTitle()
  {
    return m_title;
  }

  public void setTitle(String title)
  {
    Assert.isNotNull(title);
    m_propertyChangeSupport.firePropertyChange(PROPERTY_TITLE, m_title, m_title = title);
  }

  @Override
  public String toString()
  {
    return m_title;
  }
 
  private final PropertyChangeSupport m_propertyChangeSupport;
  private final Author m_author;
  private String m_title;
}
