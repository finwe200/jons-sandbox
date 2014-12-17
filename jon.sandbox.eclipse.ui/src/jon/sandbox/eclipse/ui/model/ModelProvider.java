package jon.sandbox.eclipse.ui.model;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider
{
  INSTANCE;

  private ModelProvider()
  {
    m_persons = new ArrayList<Person>(5);
    m_persons.add(new Person("Reiner", "Zufall", Gender.eMale, true, 20));
    m_persons.add(new Person("Reiner", "Babbel", Gender.eMale, true, 22));
    m_persons.add(new Person("Marie", "Dortmund", Gender.eFemale, false, 23));
    m_persons.add(new Person("Holger", "Adams", Gender.eMale, true, 25));
    m_persons.add(new Person("Juliane", "Adams", Gender.eFemale, true, 25));
    m_persons.add(new Person("Ziggy", "Stardust", Gender.eAndrogynous, false, 26));
  }

  public List<Person> getPersons()
  {
    return m_persons;
  }

  private final List<Person> m_persons;
}
