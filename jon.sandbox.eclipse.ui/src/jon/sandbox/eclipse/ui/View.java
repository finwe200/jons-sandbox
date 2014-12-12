package jon.sandbox.eclipse.ui;

import java.net.URL;

import jon.sandbox.eclipse.ui.model.ModelProvider;
import jon.sandbox.eclipse.ui.model.Person;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class View
  extends
    ViewPart
{
  public static final String ID = "jon.sandbox.eclipse.ui.view";
  
  
  /**
   * This is a callback that will allow us to create the viewer and initialize
   * it.
   */
  @Override
  public void createPartControl(Composite parent)
  {
    parent.setLayout(new GridLayout(2, false));

    Label searchLabel = new Label(parent, SWT.NONE);
    searchLabel.setText("Search: ");
    searchLabel.setLayoutData(
      new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 1, 1));
    
    final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
    searchText.setLayoutData(
      new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));

    // Create the viewer
    createViewer(parent);
    m_viewer.getControl().setLayoutData(
      new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));
  }
  
  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus()
  {
    m_viewer.getControl().setFocus();
  }
  
  @Override
  public void dispose()
  {
    // TODO Auto-generated method stub
    super.dispose();
  }

  private void createViewer(Composite parent)
  {
    m_viewer = new TableViewer(
      parent,
      SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER
    );

    // Create the various table columns
    addColumn("First Name", 80, new FirstNameLabelProvider());
    addColumn("Last Name", 120, new LastNameLabelProvider());
    addColumn("Gender", 100, new GenderLabelProvider());
    addColumn("Is Married", 80, new IsMarriedLabelProvider());
    addColumn("Age", 50, new AgeLabelProvider());

    // Make lines and header visible
    final Table table = m_viewer.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    // Set the content for the viewer, setInput will call getElements in the
    // contentProvider
    m_viewer.setContentProvider(new ArrayContentProvider());
    m_viewer.setInput(ModelProvider.INSTANCE.getPersons());

    // Make the selection available to other views
    getSite().setSelectionProvider(m_viewer);

    // Set the sorter for the table
  }

  private TableViewerColumn addColumn(
    String label, int width, CellLabelProvider labelProvider,
    boolean isResizable, boolean isMovable)
  {
    TableViewerColumn viewerCol = new TableViewerColumn(m_viewer, SWT.NONE);
    viewerCol.setLabelProvider(labelProvider);

    TableColumn col = viewerCol.getColumn();
    col.setWidth(width);
    col.setText(label);
    col.setResizable(isResizable);
    col.setMoveable(isMovable);

    return viewerCol;
  }

  private TableViewerColumn addColumn(
    String label, int width, CellLabelProvider labelProvider)
  {
    return addColumn(label, width, labelProvider, true, true);
  }

  // Helper method to load the images
  // Ensure to dispose the images in your @PreDestroy method
  private static Image getImage(String file)
  {
    // assume that the current class is called View.java
    Bundle bundle = FrameworkUtil.getBundle(View.class);
    URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
    ImageDescriptor image = ImageDescriptor.createFromURL(url);
    return image.createImage();
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

      //ms_checkedImage = Activator.getImageDescriptor("icons/checked.gif").createImage();
      //ms_uncheckedImage = Activator.getImageDescriptor("icons/unchecked.gif").createImage();
      ms_checkedImage = View.getImage("checked.gif");
      ms_uncheckedImage = View.getImage("unchecked.gif");
    }

    @Override
    public void dispose()
    {
      ms_checkedImage.dispose();
      ms_uncheckedImage.dispose();

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

  private TableViewer m_viewer;
}