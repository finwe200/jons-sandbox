package jon.sandbox.jdt.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;

public class BuildASTHandler
  extends
    AbstractHandler
{
  
  @Override
  public boolean isEnabled()
  {
    //return super.isEnabled();
    return true;
  }

  /*
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IWorkspaceRoot root = workspace.getRoot();
    // Get all projects in the workspace
    IProject[] projects = root.getProjects();

    // Loop over all projects
    for (IProject project : projects) {
      try
      {
        if (project.isNatureEnabled(JDT_NATURE)) {
          analyseMethods(project);
        }
      }
      catch (CoreException e)
      {
        e.printStackTrace();
      }
    }
    return null;
  }
  */

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException
  { 
    IEditorPart editor = HandlerUtil.getActiveEditor(event);
    if (editor == null) {
      return null;
    }

    IEditorInput input = editor.getEditorInput();
    IFile file = (input instanceof FileEditorInput) ? ((FileEditorInput)input).getFile() : null;
    IProject project = (file instanceof IResource) ? file.getProject() : null;
    if (project != null)
    {
      try
      {
        if (project.isNatureEnabled(JDT_NATURE)) {
          analyseMethods(project, file);
        }
      }
      catch (Throwable e)
      {
      }
    }
    return null;

    /*
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IWorkbench iworkbench = PlatformUI.getWorkbench();
    if (iworkbench == null)...
    IWorkbenchWindow iworkbenchwindow = iworkbench.getActiveWorkbenchWindow();
    if (iworkbenchwindow == null) ...
    IWorkbenchPage iworkbenchpage = iworkbenchwindow.getActivePage();
    if (iworkbenchpage == null) ...
    IEditorPart ieditorpart = iworkbenchpage.getActiveEditor();
     */

    /*
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    Assert.isTrue(selection instanceof IStructuredSelection);
    IStructuredSelection structuredSelctn = (IStructuredSelection)selection;
    Assert.isTrue(structuredSelctn.size() > 0);
  
    IWorkbenchPart part = HandlerUtil.getActivePart(event);
    Assert.isTrue(part instanceof PageBookView);
    PageBookView view = (PageBookView)part;
    IPage page = view.getCurrentPage();
    Assert.isTrue(page instanceof AbstractTreeControlBasedPageBookViewPage);
    */
  }

  /*
  public static IProject getCurrentProject(){    
    ISelectionService selectionService =     
        Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();    

    ISelection selection = selectionService.getSelection();    

    IProject project = null;    
    if(selection instanceof IStructuredSelection) {    
        Object element = ((IStructuredSelection)selection).getFirstElement();    

        if (element instanceof IResource) {    
            project= ((IResource)element).getProject();    
        } else if (element instanceof PackageFragmentRootContainer) {    
            IJavaProject jProject =     
                ((PackageFragmentRootContainer)element).getJavaProject();    
            project = jProject.getProject();    
        } else if (element instanceof IJavaElement) {    
            IJavaProject jProject= ((IJavaElement)element).getJavaProject();    
            project = jProject.getProject();    
        }    
    }     
    return project;    
  }
  */

  private void analyseMethods(IProject project) throws JavaModelException
  {
    IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
    // parse(JavaCore.create(project));
    for (IPackageFragment mypackage : packages) {
      if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
        createAST(mypackage);
      }
    }
  }

  private void analyseMethods(IProject project, IFile file) throws JavaModelException
  {
    IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
    // parse(JavaCore.create(project));
    for (IPackageFragment mypackage : packages) {
      if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
        createAST(mypackage, file);
      }
    }
  }

  private void createAST(IPackageFragment mypackage)
      throws JavaModelException
  {
    System.out.println("Package name=" + mypackage.getElementName());
    for (ICompilationUnit unit : mypackage.getCompilationUnits())
    {
      // now create the AST for the ICompilationUnits
      createAST(unit);
    }
  }

  private void createAST(IPackageFragment mypackage, IFile file)
    throws JavaModelException
{
  System.out.println("Package name=" + mypackage.getElementName());
  String fileName = file.getName();
  for (ICompilationUnit unit : mypackage.getCompilationUnits())
  {
    String unitName = unit.getResource().getName();
    if (unitName.equals(fileName))
    {
      // now create the AST for the ICompilationUnits
      createAST(unit);
    }
  }
}
  @SuppressWarnings("unchecked")
  private void createAST(ICompilationUnit unit) throws JavaModelException
  {
    // now create the AST for the ICompilationUnits
    CompilationUnit parse = parse(unit);
    MethodVisitor visitor = new MethodVisitor();
    parse.accept(visitor);

    for (MethodDeclaration method : visitor.getMethods())
    {
      Type rt = method.getReturnType2();
      String str = (rt == null) ? "void" : rt.toString();
      System.out.print(" " + str + " " +  method.getName() + "(");
      List<SingleVariableDeclaration> params = method.parameters();
      boolean firstParam = true;
      for (SingleVariableDeclaration param : params)
      {
        if (!firstParam) {
          System.out.print(", ");
        } else {
          firstParam = false;
        }
        System.out.print(param.getType().toString() + " " + param.getName());
      }
      System.out.println(")");
    }
  }

  /**
   * Reads a ICompilationUnit and creates the AST DOM for manipulating the Java
   * source file
   * 
   * @param unit
   * @return
   */
  private static CompilationUnit parse(ICompilationUnit unit)
  {
    ASTParser parser = ASTParser.newParser(AST.JLS4); // AST.JLS3
    parser.setKind(ASTParser.K_COMPILATION_UNIT);
    parser.setSource(unit);
    parser.setResolveBindings(true);
    return (CompilationUnit) parser.createAST(null); // parse
  }
  
  private static final String JDT_NATURE = "org.eclipse.jdt.core.javanature";
}
