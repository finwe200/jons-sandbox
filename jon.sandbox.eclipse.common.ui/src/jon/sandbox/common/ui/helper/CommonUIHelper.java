/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.helper;

import java.math.BigDecimal;
import java.net.URL;

import jon.sandbox.common.ui.ICommonUIConstants;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class CommonUIHelper extends Object
{
  // Helper method to load the images
  // Ensure to dispose the images in your @PreDestroy method
  public static Image getImage(Class<?> classFromBundle, String file)
  {
    // assume that the current class is called View.java
    Bundle bundle = FrameworkUtil.getBundle(classFromBundle);
    URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
    ImageDescriptor image = ImageDescriptor.createFromURL(url);
    return image.createImage();
  }

  // Added this method here because SWT won't allow me to subclass Combo
  public static void add(Combo comboBox, String item, Object itemData)
  {
    if (item != null)
    {
      comboBox.add(item);
      comboBox.setData(item, itemData);
    }
  }

  // Added this method here because SWT won't allow me to subclass Combo
  public static String getSelectedItem(Combo comboBox)
  {
    int index = comboBox.getSelectionIndex();
    String item = comboBox.getItem(index);
    return item;
  }

  // Added this method here because SWT won't allow me to subclass Combo
  public static Object getData(Combo comboBox, int index)
  {
    Object data = null;
    String item = comboBox.getItem(index);
    if (item != null)
    {
      data = comboBox.getData(item);
    }
    return data;
  }

  // Added this method here because SWT won't allow me to subclass Combo
  public static Object getSelectedItemData(Combo comboBox)
  {
    int index = comboBox.getSelectionIndex();
    if(index<0)
      return null;
    Object data = getData(comboBox, index);
    return data;
  }

  // Added this method here because SWT won't allow me to subclass Combo
  public static int select(Combo comboBox, String item)
  {
    int index = comboBox.indexOf(item);
    comboBox.select(index);
    return index;
  }

  public static Image dispose(Image image)
  {
    if (image != null)
    {
      image.dispose();
    }
    return null;
  }
  
  public static Object[] getViewerSelection(StructuredViewer viewer)
  {
    IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
    if (sel != null && !sel.isEmpty())
    {
      return sel.toArray();
    }
    return new Object[0];
  }

  //** The returned image is managed by Eclipse and must not be disposed. */
  public static Image getSystemImage(String imagePath)
  {
    Image image = PlatformUI.getWorkbench().getSharedImages().getImage(imagePath);
    return image;
  }

  /*
  public static CommonAction disposeAction(final CommonAction action)
  {
    if (action != null)
    {
      action.dispose();
    }
    return null;
  }
  */

  public static MenuManager createSetHookContextMenu(IMenuListener menuOwner, Control ctrl)
  {
    MenuManager menuMgr = new MenuManager(ICommonUIConstants.ms_popupId);
    menuMgr.setRemoveAllWhenShown(true);
    menuMgr.addMenuListener(menuOwner);

    Menu menu = menuMgr.createContextMenu(ctrl);
    ctrl.setMenu(menu);

    return menuMgr;
  }

  public static Shell getShell()
  {
    return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
  }

  public static Shell getShellIfNecessary(Shell shell)
  {
    return (shell == null) ? getShell() : shell;
  }

  /**
   * Fill the styledText with the given tooltip, and hilight the keys (Key is
   * words at the start of the line.)
   * @param title the title of the tooltip
   * @param keys an array of keys of the pair
   * @param tooltip multi-line tooltip text (include title) which seperated by \r\n 
   * @param control styledText control
   * @see TreeViewerWithTooltip#LINE_SEP
   */
//public static void fillStyledTooltip(String title, String[] keys, String tooltip, final StyledText control){
//  control.setFont(JFaceResources.getFontRegistry().get(JFaceResources.DIALOG_FONT));
//  control.setText(tooltip);
//  StyleRange range = new StyleRange();
//  range.start = 0;
//  range.length = title.length();
//  range.fontStyle = SWT.BOLD;
////    range.underline = true;
//  control.setStyleRange(range);
//
//  control.addLineBackgroundListener(new LineBackgroundListener() {
//    public void lineGetBackground(LineBackgroundEvent event) {
//      if(control.getLineAtOffset(event.lineOffset) == 0){
//        event.lineBackground = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
//      }
//    }
//  });
//  
//  for(int i=0;i<keys.length;i++){
//    int index=0;
//    do{
//      index=tooltip.indexOf(keys[i],index);
//      if(index>=0){
//        if(index-2>=0){
//          if(tooltip.substring(index-2, index).equals(ICommonConstants.ms_lineSeparator)){
//            range = new StyleRange();
//            range.start = index;
//            range.length = keys[i].length();
//            range.fontStyle = SWT.BOLD;
//            control.setStyleRange(range);
//          }
//        }else{
//          range = new StyleRange();
//          range.start = index;
//          range.length = keys[i].length();
//          range.fontStyle = SWT.BOLD;
//          control.setStyleRange(range);
//        }
//        index+=keys[i].length();
//      }
//    }while (index>0);
//  }
//}

  public static void displayErrorMessage(final Shell shell, final String errMsg)
  {
    // Execute asynchronously to avoid conflict with cell editor.
    Display.getDefault().asyncExec(new Runnable(){
      @Override
      public void run()
      {
        MessageDialog.openError(getShellIfNecessary(shell), "Error", errMsg);
      }
    });
  }

  public static BigDecimal validateNumber(Shell shell, Object value)
  {
    BigDecimal num = null;
    try
    {
      String tmp = ((String)value).trim();
      num = new BigDecimal(tmp);
    }
    catch (Throwable ex)
    {
      String errMsg = new String(
        "The value (" + value + ") is not a valid decimal number.\n\n" +
        "Error: " + ex.getMessage());
      displayErrorMessage(shell, errMsg);
    }
    return num;
  }

  public static void setPerspective(String id)
  {
    IWorkbenchPage page =
      PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    if (page != null)
    {
      IPerspectiveDescriptor[] perspects =
        PlatformUI.getWorkbench().getPerspectiveRegistry().getPerspectives();
      for (int i = 0; i < perspects.length; i++)
      {
        if (perspects[i].getId().equals(id))
        {
          page.setPerspective(perspects[i]);
          break;
        }
      }
    }
  }

  /**
   * @param control
   * @return true if control style is SWT.READ_ONLY
   */
  public static boolean isReadonlyStyle(Control control)
  {
    return isReadonlyStyle(control.getStyle());
  }

  /**
   * @param style
   * @return true if style is SWT.READ_ONLY
   */
  public static boolean isReadonlyStyle(int style)
  {
    return ((style & SWT.READ_ONLY)!=0);
  }

  /**
   * Reposition the control to make it best fit the screen size.
   * @param control a control whose position is display coordinate.
   */
  public static void repositionControl(Control control)
  {
    Rectangle screenBound = control.getShell().getDisplay().getBounds();
    Rectangle bound = control.getBounds();
    
    Point oldOrigin = new Point(bound.x, bound.y);
    Point newOrigin = new Point(0, 0);

    // find a better location to pop-up the shell
    if (oldOrigin.x < 0)
      newOrigin.x = 0;
    else if (oldOrigin.x > screenBound.width - bound.width)
    {
      newOrigin.x = screenBound.width - bound.width;
    }
    else
    {
      newOrigin.x = oldOrigin.x;
    }
    if (oldOrigin.y < 0)
      newOrigin.y = 0;
    else if (oldOrigin.y > screenBound.height - bound.height)
    {
      newOrigin.y = screenBound.height - bound.height;
    }
    else
    {
      newOrigin.y = oldOrigin.y;
    }
   
    bound.x = newOrigin.x;
    bound.y = newOrigin.y;
    control.setBounds(bound);
  }

  static public void updateViewerCommand(ViewPart part, String cmdId)
  {
    IViewSite viewSite = (part == null) ? null : part.getViewSite();
    IActionBars bars = (viewSite == null) ? null : viewSite.getActionBars();
    IToolBarManager mgr = (bars == null) ? null : bars.getToolBarManager();

    if (mgr != null)
    {
      IContributionItem item = mgr.find(cmdId);
      if (item != null) {
        item.update();
      }
    }
  }

  static public void updateViewerCommands(ViewPart part, String[] cmdIds)
  {
    IViewSite viewSite = (part == null) ? null : part.getViewSite();
    IActionBars bars = (viewSite == null) ? null : viewSite.getActionBars();
    IToolBarManager mgr = (bars == null) ? null : bars.getToolBarManager();

    if (mgr != null)
    {
      for (String cmdId : cmdIds)
      {
        IContributionItem item = mgr.find(cmdId);
        if (item != null)
        {
          //Activator.getInstance().logTrace(
          //  CommonUIHelper.class,
          //  "The Contribution item will be manually updated. Part=" +
          //  part.getClass().getSimpleName() + ", CmdId=" + cmdId);
          item.update();
        }
      }
    }
  }

  public static String getClassName(Object obj)
  {
    return (obj == null) ?
      ICommonUIConstants.ms_null : obj.getClass().getSimpleName();
  }

}
