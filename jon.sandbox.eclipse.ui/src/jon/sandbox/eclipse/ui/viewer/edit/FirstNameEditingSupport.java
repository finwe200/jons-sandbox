package jon.sandbox.eclipse.ui.viewer.edit;

import jon.sandbox.eclipse.ui.model.person.Person;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

public class FirstNameEditingSupport
  extends
    EditingSupport
{

  public FirstNameEditingSupport(TableViewer viewer)
  {
    super(viewer);

    m_viewer = viewer;
    m_editor = new TextCellEditor(viewer.getTable());
  }

  @Override
  protected CellEditor getCellEditor(Object element)
  {
    return m_editor;
  }

  @Override
  protected boolean canEdit(Object element)
  {
    return true;
  }

  @Override
  protected Object getValue(Object element)
  {
    return ((Person)element).getFirstName();
  }

  @Override
  protected void setValue(Object element, Object value)
  {
    ((Person)element).setFirstName(String.valueOf(value));
    m_viewer.update(element, null);
  }

  protected final TableViewer m_viewer;
  protected final CellEditor m_editor;
}
