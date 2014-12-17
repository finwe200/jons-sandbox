package jon.sandbox.eclipse.ui.viewer.edit;

import jon.sandbox.eclipse.ui.model.Gender;
import jon.sandbox.eclipse.ui.model.Person;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;

public class GenderEditingSupport
  extends
    EditingSupport
{

  @SuppressWarnings("deprecation")
  public GenderEditingSupport(TableViewer viewer)
  {
    super(viewer);

    m_viewer = viewer;
    m_editor = new ComboBoxViewerCellEditor(viewer.getTable());
    m_editor.setContenProvider(new ArrayContentProvider());
    m_editor.setLabelProvider(
      new LabelProvider()
      {

        @Override
        public String getText(Object element)
        {
          return ((Gender)element).toString();
        }
      }
    );
    m_editor.setInput(Gender.values());
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
    return ((Person)element).getGender();
  }

  @Override
  protected void setValue(Object element, Object value)
  {
    Gender gender = (Gender)value;
    Person person = (Person)element;
    person.setGender(gender);
    if (!gender.canMarry() && person.isMarried()) {
      person.setMarried(false);
    }
    m_viewer.update(element, null);
  }

  private final TableViewer m_viewer;
  private final ComboBoxViewerCellEditor m_editor;
}
