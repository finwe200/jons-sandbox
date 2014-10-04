package jon.sandbox.jdt.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class CreateBasePackageHandler
  extends
    AbstractHandler
{
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IWorkspaceRoot root = workspace.getRoot();
    // Get all projects in the workspace
    IProject[] projects = root.getProjects();
    // Loop over all projects
    for (IProject project : projects)
    {
      try 
      {
        // only work on open projects with the Java nature
        if (project.isOpen()
            & project.isNatureEnabled(JavaCore.NATURE_ID)) {
          createPackage(project);
        }
      }
      catch (CoreException e)
      {
        e.printStackTrace();
      }
    }
    return null;
  }
  
  private void createPackage(IProject project) throws JavaModelException
  {
    IJavaProject javaProject = JavaCore.create(project);
    IFolder folder = project.getFolder("src");
    // folder.create(true, true, null);
    IPackageFragmentRoot srcFolder = javaProject.getPackageFragmentRoot(folder);
    //IPackageFragment fragment =
    String name = project.getName();
    srcFolder.createPackageFragment(name, true, null);
  }
}
