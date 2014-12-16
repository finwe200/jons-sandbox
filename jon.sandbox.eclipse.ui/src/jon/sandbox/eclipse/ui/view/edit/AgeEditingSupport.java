package jon.sandbox.eclipse.ui.view.edit;

import java.text.MessageFormat;

import jon.sandbox.eclipse.ui.model.Person;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;

public class AgeEditingSupport
  extends
    EditingSupport
{
  public AgeEditingSupport(TableViewer viewer)
  {
    super(viewer);

    m_viewer = viewer;
    m_editor = new TextCellEditor(viewer.getTable(), SWT.BORDER);
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
    return "" + ((Person)element).getAge();
  }
  
  @Override
  protected void setValue(Object element, Object value)
  {
    try
    {
      int age = Integer.parseInt((String)value);
      if (age >= 18 && age < 100)
      {
        ((Person)element).setAge(age);
      }
      else
      {
        MessageDialog.openError(
          m_viewer.getTable().getShell(), "Invalid Age",
          MessageFormat.format("The Age ({0}) must be >= 18 and <= 100!", age)
        );
      }
    }
    catch (NumberFormatException e)
    {
      MessageDialog.openError(
        m_viewer.getTable().getShell(), "Invalid Age",
        MessageFormat.format("The Age (''{0}'') must be a valid Integer!", (String)value)
      );
    }
  }

  private final TableViewer m_viewer;
  private final CellEditor m_editor;
}
