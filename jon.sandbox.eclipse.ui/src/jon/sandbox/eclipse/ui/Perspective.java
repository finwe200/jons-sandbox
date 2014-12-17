package jon.sandbox.eclipse.ui;

import jon.sandbox.eclipse.ui.view.PersonView;
import jon.sandbox.eclipse.ui.view.VirtualView;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;

public class Perspective
  implements
    IPerspectiveFactory
{
  public static final String ms_mainFolderID =
    "jon.sandbox.ui.views.main_folder";
 
  @Override
  public void createInitialLayout(IPageLayout layout)
  {
    String editorAreaId = layout.getEditorArea();
    layout.setEditorAreaVisible(false);
    layout.setFixed(true);
      
    IFolderLayout folder = layout.createFolder(
      ms_mainFolderID, IPageLayout.LEFT, 0.50f, editorAreaId);
    
    folder.addView(PersonView.ID);
    IViewLayout viewLayout = layout.getViewLayout(PersonView.ID);
    viewLayout.setCloseable(false);

    folder.addView(VirtualView.ID);
    viewLayout = layout.getViewLayout(VirtualView.ID);
    //viewLayout.setCloseable(false);
  }
}
