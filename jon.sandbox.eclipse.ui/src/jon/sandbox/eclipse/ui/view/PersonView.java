package jon.sandbox.eclipse.ui.view;

import jon.sandbox.eclipse.ui.view.filter.PersonNameFilter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class PersonView
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

    // Listen to changes in the "search string" in order to "apply" the
    // modified string to the Table Filter
    final PersonNameFilter filter = m_viewer.getPersonNameFilter();
    searchText.addKeyListener(
      new KeyAdapter()
      {
        @Override
        public void keyReleased(KeyEvent event)
        {
          filter.setSearchText(searchText.getText());
          m_viewer.refresh();
        }
      }
    );
  }
  
  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus()
  {
    m_viewer.getControl().setFocus();
  }

  private void createViewer(Composite parent)
  {
    m_viewer = new PersonViewer(
      parent,
      SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER
    );

    // Make the selection available to other views
    getSite().setSelectionProvider(m_viewer);
  }

  private PersonViewer m_viewer;
}
