package jon.sandbox.eclipse.ui.model.library;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import jon.sandbox.eclipse.ui.model.person.Gender;
import jon.sandbox.eclipse.ui.model.person.Person;

import org.eclipse.core.runtime.Assert;

public class Author extends Object
{
  public static final String PROPERTY_BOOKS = "author.books";

  public Author(
    String firstName, String lastName, Gender gender, boolean married, int age)
  {
    super();

    m_propertyChangeSupport = new PropertyChangeSupport(this);
    m_person = new Person(firstName, lastName, gender, married, age);
    m_books = new ArrayList<Book>(10);
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

  public String getFirstName()
  {
    return m_person.getFirstName();
  }

  public String getLastName()
  {
    return m_person.getLastName();
  }

  public List<Book> getBooks()
  {
    return m_books;
  }

  public void setFirstName(String firstName)
  {
    m_person.setFirstName(firstName);
  }

  public void setLastName(String lastName)
  {
    m_person.setFirstName(lastName);
  }

  public void addBook(Book book)
  {
    Assert.isNotNull(book);
    if (!m_books.contains(book))
    {
      m_books.add(book);
    }
  }

  @Override
  public String toString()
  {
    return m_person.getFirstName() + " " + m_person.getLastName();
  }
 
  private final PropertyChangeSupport m_propertyChangeSupport;
  private final Person m_person;
  private final List<Book> m_books;
}
