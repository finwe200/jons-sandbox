package jon.sandbox.eclipse.ui.viewer.edit;

import jon.sandbox.eclipse.ui.model.person.Person;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;

public class IsMarriedEditingSupport
  extends
    EditingSupport
{
  public IsMarriedEditingSupport(TableViewer viewer)
  {
    super(viewer);

    m_viewer = viewer;
    m_editor = new CheckboxCellEditor(viewer.getTable(), SWT.CHECK | SWT.READ_ONLY);
  }

  @Override
  protected CellEditor getCellEditor(Object element)
  {
    return m_editor;
  }
  
  @Override
  protected boolean canEdit(Object element)
  {
    return ((Person)element).getGender().canMarry();
  }
  
  @Override
  protected Object getValue(Object element)
  {
    return ((Person)element).isMarried();
  }
  
  @Override
  protected void setValue(Object element, Object value)
  {
    ((Person)element).setMarried(((Boolean)value).booleanValue());
    m_viewer.update(element, null);
  }

  private final TableViewer m_viewer;
  private final CellEditor m_editor;
}
