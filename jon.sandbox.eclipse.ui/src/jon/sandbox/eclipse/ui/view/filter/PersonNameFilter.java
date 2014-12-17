package jon.sandbox.eclipse.ui.view.filter;

import jon.sandbox.eclipse.ui.model.Person;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class PersonNameFilter
  extends ViewerFilter
{
  public void setSearchText(String s)
  {
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

  private String m_searchString;
}
