package jon.sandbox.eclipse.ui.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.Assert;

public class Person
{
  public static final String PROPERTY_FIRST_NAME = "firstname";
  public static final String PROPERTY_LAST_NAME = "firstname";
  public static final String PROPERTY_GENDER = "gender";
  public static final String PROPERTY_IS_MARRIED = "married";
  public static final int MIN_AGE = 18;
  public static final int MAX_AGE = 100;

  public Person(
    String firstName, String lastName, Gender gender, boolean married, int age)
  {
    super();

    Assert.isNotNull(firstName);
    Assert.isNotNull(lastName);
    Assert.isNotNull(gender);
    Assert.isTrue(isValidAge(age));

    m_firstName = firstName;
    m_lastName = lastName;
    m_gender = gender;
    m_isMarried = married;
    m_age = age;
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
    return m_firstName;
  }

  public String getLastName()
  {
    return m_lastName;
  }

  public Gender getGender()
  {
    return m_gender;
  }

  public boolean isMarried()
  {
    return m_isMarried;
  }

  public int getAge()
  {
    return m_age;
  }

  public void setFirstName(String firstName)
  {
    m_propertyChangeSupport.firePropertyChange(
      PROPERTY_FIRST_NAME, m_firstName, m_firstName = firstName);
  }

  public void setLastName(String lastName)
  {
    m_propertyChangeSupport.firePropertyChange(
      PROPERTY_LAST_NAME, m_lastName, m_lastName = lastName);
  }

  public void setGender(Gender gender)
  {
    m_propertyChangeSupport.firePropertyChange(
      PROPERTY_GENDER, m_gender, m_gender = gender);
  }

  public void setMarried(boolean isMarried)
  {
    m_propertyChangeSupport.firePropertyChange(
      PROPERTY_IS_MARRIED, m_isMarried, m_isMarried = isMarried);
  }

  public void setAge(Integer age)
  {
    m_propertyChangeSupport.firePropertyChange("age", m_age, m_age = age);
  }

  @Override
  public String toString()
  {
    return m_firstName + " " + m_lastName;
  }
 
  static public boolean isValidAge(int age)
  {
    return (age >= MIN_AGE && age <= MAX_AGE);
  }

  private String m_firstName;
  private String m_lastName;
  private boolean m_isMarried;
  private Gender m_gender;
  private int m_age;
  private final PropertyChangeSupport m_propertyChangeSupport =
    new PropertyChangeSupport(this);
}
