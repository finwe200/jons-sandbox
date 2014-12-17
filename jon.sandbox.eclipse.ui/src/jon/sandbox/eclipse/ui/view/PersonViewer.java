package jon.sandbox.eclipse.ui.view;

import jon.sandbox.common.ui.helper.CommonUIHelper;
import jon.sandbox.common.ui.viewer.CommonTableViewer;
import jon.sandbox.common.ui.viewer.TableViewerSorter;
import jon.sandbox.common.ui.viewer.ViewerSortSelectionAdaptor;
import jon.sandbox.eclipse.ui.model.ModelProvider;
import jon.sandbox.eclipse.ui.model.Person;
import jon.sandbox.eclipse.ui.view.edit.AgeEditingSupport;
import jon.sandbox.eclipse.ui.view.edit.FirstNameEditingSupport;
import jon.sandbox.eclipse.ui.view.edit.GenderEditingSupport;
import jon.sandbox.eclipse.ui.view.edit.IsMarriedEditingSupport;
import jon.sandbox.eclipse.ui.view.edit.LastNameEditingSupport;
import jon.sandbox.eclipse.ui.view.filter.PersonNameFilter;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class PersonViewer
  extends
    CommonTableViewer
{
  public PersonViewer(Composite parent, int style)
  {
    super(parent, style);
 
    m_nameFilter = new PersonNameFilter();
    init();
  }

  public PersonNameFilter getPersonNameFilter()
  {
    return m_nameFilter;
  }

  @Override
  protected final void init()
  {
    // Perform common initialization
    super.init();

    // Set the sorter for the table
    PersonViewerSorter sorter = new PersonViewerSorter();
    setSorter(sorter);

    // Set a first/last name Filter
    addFilter(m_nameFilter);

    // Create/configure the various table columns for editing and sorting
    addColumn(
      "First Name", 90, new FirstNameLabelProvider(),
      new FirstNameEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, sorter, ms_colIndexFirstName)
    );
    addColumn(
      "Last Name", 120, new LastNameLabelProvider(),
      new LastNameEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, sorter, ms_colIndexLastName)
    );
    addColumn(
      "Gender", 100, new GenderLabelProvider(), new GenderEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, sorter, ms_colIndexGender)
    );
    addColumn(
      "Is Married", 80, new IsMarriedLabelProvider(),
      new IsMarriedEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, sorter, ms_colIndexIsMarried)
    );
    addColumn(
      "Age", 50, new AgeLabelProvider(),
      new AgeEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, sorter, ms_colIndexAge)
    );

    // Make the initial sort be by "first name" (must call this method after
    // columns have been defined/added to the table viewer)
    sorter.updateSortColumn(ms_colIndexFirstName);

    // Set the content for the viewer, setInput will call getElements in the
    // contentProvider
    setContentProvider(new ArrayContentProvider());
    setInput(ModelProvider.INSTANCE.getPersons());
  }

  private class PersonViewerSorter
    extends TableViewerSorter
  {
    public PersonViewerSorter()
    {
      super(PersonViewer.this);
    }
  
    @Override
    protected int doCompare(Viewer viewer, Object arg1, Object arg2)
    {
      int rtn = 0;
      try
      {
        Person p1 = (Person)arg1;
        Person p2 = (Person)arg2;
  
        // Determine which column and do the appropriate sort
        switch (m_curColumn)
        {
          case ms_colIndexFirstName:
            rtn = compare(p1.getFirstName(), p2.getFirstName());
            if (rtn == 0) {
              // Do a secondary sort by "last name"
              rtn = compare(p1.getLastName(), p2.getLastName());
            }
          break;
  
          case ms_colIndexLastName:
            rtn = compare(p1.getLastName(), p2.getLastName());
            if (rtn == 0) {
              // Do a secondary sort by "first name"
              rtn = compare(p1.getFirstName(), p2.getFirstName());
            }
          break;
  
          case ms_colIndexGender:
            rtn = compare(p1.getGender().toString(), p2.getGender().toString());
            if (rtn == 0) {
              // Do a secondary sort by "last name"
              rtn = compare(p1.getLastName(), p2.getLastName());
            }
          break;
  
          case ms_colIndexIsMarried:
            rtn = compare((p1.isMarried()) ? 1 : 0, (p2.isMarried()) ? 1 : 0);
            if (rtn == 0) {
              // Do a secondary sort by "last name"
              rtn = compare(p1.getLastName(), p2.getLastName());
            }
          break;
  
          case ms_colIndexAge:
            rtn = compare(p1.getAge(), p2.getAge());
            if (rtn == 0) {
              // Do a secondary sort by "last name"
              rtn = compare(p1.getLastName(), p2.getLastName());
            }
          break;
        }
      }
      catch (Throwable ex) {
        rtn = 0;
      }
      return rtn;
    }
  }
  
  private class FirstNameLabelProvider extends ColumnLabelProvider
  {
    @Override
    public String getText(Object element)
    {
      Person p = (Person)element;
      return p.getFirstName();
    }
  }

  private class LastNameLabelProvider extends ColumnLabelProvider
  {
    @Override
    public String getText(Object element)
    {
      Person p = (Person)element;
      return p.getLastName();
    }
  }

  private class GenderLabelProvider extends ColumnLabelProvider
  {
    @Override
    public String getText(Object element)
    {
      Person p = (Person)element;
      return p.getGender().toString();
    }
  }

  private class IsMarriedLabelProvider extends ColumnLabelProvider
  {
    public IsMarriedLabelProvider()
    {
      super();

      ms_checkedImage = CommonUIHelper.getImage(PersonViewer.class, "checked.gif");
      ms_uncheckedImage = CommonUIHelper.getImage(PersonViewer.class, "unchecked.gif");
    }

    @Override
    public void dispose()
    {
      CommonUIHelper.dispose(ms_checkedImage);
      CommonUIHelper.dispose(ms_uncheckedImage);

      super.dispose();
    }

    @Override
    public String getText(Object element)
    {
      Person p = (Person)element;
      return "" + p.isMarried();
    }

    @Override
    public Image getImage(Object element)
    {
      return (((Person)element).isMarried()) ? ms_checkedImage : ms_uncheckedImage;
    }

    private final Image ms_checkedImage;
    private final Image ms_uncheckedImage;
  }

  private class AgeLabelProvider extends ColumnLabelProvider
  {
    @Override
    public String getText(Object element)
    {
      Person p = (Person)element;
      return "" + p.getAge();
    }
  }

  private static final int ms_colIndexFirstName = 0;
  private static final int ms_colIndexLastName = 1;
  private static final int ms_colIndexGender = 2;
  private static final int ms_colIndexIsMarried = 3;
  private static final int ms_colIndexAge = 4;

  final PersonNameFilter m_nameFilter;
}
