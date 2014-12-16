package jon.sandbox.eclipse.ui.view.edit;

import jon.sandbox.eclipse.ui.model.Person;

import org.eclipse.jface.viewers.TableViewer;

public class LastNameEditingSupport
  extends
    FirstNameEditingSupport
{

  public LastNameEditingSupport(TableViewer viewer)
  {
    super(viewer);
  }

  @Override
  protected Object getValue(Object element)
  {
    return ((Person)element).getLastName();
  }

  @Override
  protected void setValue(Object element, Object value)
  {
    ((Person)element).setLastName(String.valueOf(value));
    m_viewer.update(element, null);
  }
}
