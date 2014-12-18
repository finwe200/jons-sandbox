package jon.sandbox.eclipse.ui.viewer.filter;

import jon.sandbox.eclipse.ui.model.person.Person;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class PersonNameFilter
  extends ViewerFilter
{
  public String getSearchText()
  {
    return m_searchText;
  }

  public void setSearchText(String s)
  {
    m_searchText = s;
    // Ensure that the value can be used for matching 
    m_searchString = ".*" + s + ".*";
  }

  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element)
  {
    if (m_searchString == null || m_searchString.length() == 0) {
      return true;
    }

    Person p = (Person)element;
    return (p.getFirstName().matches(m_searchString) ||p.getLastName().matches(m_searchString));
  }

  private String m_searchText;
  private String m_searchString;
}
