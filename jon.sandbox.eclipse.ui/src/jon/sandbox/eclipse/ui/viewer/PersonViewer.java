package jon.sandbox.eclipse.ui.viewer;

import jon.sandbox.common.ui.helper.CommonUIHelper;
import jon.sandbox.common.ui.viewer.CommonTableViewer;
import jon.sandbox.common.ui.viewer.TableViewerSorter;
import jon.sandbox.common.ui.viewer.ViewerSortSelectionAdaptor;
import jon.sandbox.eclipse.ui.helper.SearchHelper;
import jon.sandbox.eclipse.ui.model.person.ModelProvider;
import jon.sandbox.eclipse.ui.model.person.Person;
import jon.sandbox.eclipse.ui.viewer.edit.AgeEditingSupport;
import jon.sandbox.eclipse.ui.viewer.edit.FirstNameEditingSupport;
import jon.sandbox.eclipse.ui.viewer.edit.GenderEditingSupport;
import jon.sandbox.eclipse.ui.viewer.edit.IsMarriedEditingSupport;
import jon.sandbox.eclipse.ui.viewer.edit.LastNameEditingSupport;
import jon.sandbox.eclipse.ui.viewer.filter.PersonNameFilter;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.custom.StyleRange;
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
    init(parent);
  }

  public PersonNameFilter getPersonNameFilter()
  {
    return m_nameFilter;
  }

  @Override
  protected final void init(Composite parent)
  {
    // Perform common initialization
    super.init(parent);

    // Set a first/last name Filter
    addFilter(m_nameFilter);

    // Create/configure the various table columns for editing and sorting
    addColumn(
      "First Name", 90, new FirstNameLabelProvider(),
      new FirstNameEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, ms_colIndexFirstName)
    );
    addColumn(
      "Last Name", 120, new LastNameLabelProvider(),
      new LastNameEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, ms_colIndexLastName)
    );
    addColumn(
      "Gender", 100, new GenderLabelProvider(), new GenderEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, ms_colIndexGender)
    );
    addColumn(
      "Is Married", 80, new IsMarriedLabelProvider(),
      new IsMarriedEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, ms_colIndexIsMarried)
    );
    addColumn(
      "Age", 50, new AgeLabelProvider(),
      new AgeEditingSupport(this),
      new ViewerSortSelectionAdaptor(this, ms_colIndexAge)
    );

    // Set the sorter for the table and make the initial sort be by
    // "first name" (must call this method after columns have been
    // defined/added to the table viewer)
    PersonViewerSorter sorter = new PersonViewerSorter();
    setSorter(sorter);
    sorter.updateSortColumn(ms_colIndexFirstName);

    // Set the content for the viewer, setInput will call getElements in the
    // contentProvider
    setContentProvider(new ArrayContentProvider());
    setInput(ModelProvider.INSTANCE.getPersons());

    // Create and set the "context menu" for the table
    createSetContextMenu();
  }

  @Override
  protected void createActions()
  {
  }

  @Override
  protected void fillContextMenu(IMenuManager mgr)
  {
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
        switch (m_curColIndex)
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
            if (rtn == 0)
            {
              // Do a secondary sort by "last name"
              rtn = compare(p1.getLastName(), p2.getLastName());
              if (rtn == 0) {
                // Do a third sort by "first name"
                rtn = compare(p1.getFirstName(), p2.getFirstName());
              }
            }
          break;
  
          case ms_colIndexIsMarried:
            rtn = compare((p1.isMarried()) ? 1 : 0, (p2.isMarried()) ? 1 : 0);
            if (rtn == 0)
            {
              // Do a secondary sort by "last name"
              rtn = compare(p1.getLastName(), p2.getLastName());
              if (rtn == 0) {
                // Do a third sort by "first name"
                rtn = compare(p1.getFirstName(), p2.getFirstName());
              }
            }
          break;
  
          case ms_colIndexAge:
            rtn = compare(p1.getAge(), p2.getAge());
            if (rtn == 0)
            {
              // Do a secondary sort by "last name"
              rtn = compare(p1.getLastName(), p2.getLastName());
              if (rtn == 0) {
                // Do a third sort by "first name"
                rtn = compare(p1.getFirstName(), p2.getFirstName());
              }
            }
          break;
        }
      }
      catch (Throwable ex)
      {
        rtn = 0;
      }
      return rtn;
    }
  }
  
  private class FirstNameLabelProvider extends StyledCellLabelProvider
  {
    @Override
    public void update(ViewerCell cell)
    {
      String searchText = m_nameFilter.getSearchText();
      Person person = (Person) cell.getElement();
      String cellText = person.getFirstName();
      cell.setText(cellText);
      
      StyleRange[] ranges = SearchHelper.getSearchTermStyleRanges(searchText, cellText);
      cell.setStyleRanges(ranges);

      super.update(cell);
    }
  }

  private class LastNameLabelProvider extends StyledCellLabelProvider
  {
    @Override
    public void update(ViewerCell cell)
    {
      String searchText = m_nameFilter.getSearchText(); // searchText.getText();
      Person person = (Person) cell.getElement();
      String firstName = person.getFirstName();
      String cellText = person.getLastName();
      cell.setText(cellText);

      // Only highlight the "search text" if it was NOT found in the "first name"
      StyleRange[] ranges = null;
      if (SearchHelper.getSearchTermOccurrences(searchText, firstName).length == 0)
      {
        ranges = SearchHelper.getSearchTermStyleRanges(searchText, cellText);
      }
      cell.setStyleRanges(ranges);

      super.update(cell);
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

      m_checkedImage = CommonUIHelper.getImage(PersonViewer.class, "checked.gif");
      m_uncheckedImage = CommonUIHelper.getImage(PersonViewer.class, "unchecked.gif");
    }

    @Override
    public void dispose()
    {
      CommonUIHelper.dispose(m_checkedImage);
      CommonUIHelper.dispose(m_uncheckedImage);

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
      return (((Person)element).isMarried()) ? m_checkedImage : m_uncheckedImage;
    }

    private final Image m_checkedImage;
    private final Image m_uncheckedImage;
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
