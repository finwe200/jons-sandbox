/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;

import java.util.ArrayList;
import java.util.List;

import jon.sandbox.common.ui.helper.CommonUIHelper;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

/**
 * A CommonTreeViewer using a CommonTreeViewerContentProvider which allows
 * an extra layer adapter to plug into the the normal content provider.
 * <p>
 * It also has a NodeRefresher, which will be responsible for refresh the 
 * tree. When a EMF notification is fired, the NodeRefresher will refresh
 * the tree in an optimal way. NodeRefresher is configured by calling
 * configureNodeRefresher(NodeRefresher) function.
 *
 */
public class CommonTreeViewer
  extends TreeViewer
  implements ICommonViewer, IMenuListener, DisposeListener
{
  public CommonTreeViewer(Composite parent, int style)
  {
    super(parent, style);

    m_tree = getTree();
    m_menuMgr = null;;
    m_initialInputSet = false;
    m_nodeToSelect = null;;
    m_isForcedSelect = false;

    // Add a "dispose listener" so "actions" can be disposed
    m_tree.addDisposeListener(this);
  }

  @Override
  public final StructuredViewer getStructuredViewer()
  {
    return this;
  }

	@Override
  public final void widgetDisposed(DisposeEvent e)
  {
    // If one exists, free the manager for the context menu
    disposeMenuManagerCheck();

    if (m_tree != null)
    {
      m_tree.removeDisposeListener(this);
    }
  }

  @Override
  public final void menuAboutToShow(IMenuManager manager)
  {
    fillContextMenu(manager);
  }

  /** Call this method to update the "input" for this Tree Viewer. (could not
    * call this method SetInput because of a name-space conflict with the
    * StructuredViewer class)
    * 
    * @param input The new input for this Tree Viewer. A value of null is legal
    *  and will "clear" the Tree Viewer. If the specified input is the same as
    *  the current input then the Tree Viewer will NOT be refreshed.
    *  </p>
    *  If the input is not of the appropriate type then an error will be logged
    *  and the input for this pane will be set to null.
    */
   @Override
   public final void setTheInput(Object input)
   {
     setTheInput(input, false, true);
   }

  /** Call this method to update the "input" for this Tree Viewer. (could not
    * call this method SetInput because of a name-space conflict with the
    * StructuredViewer class)
    * 
    * @param input The new input for this pane. A value of null is legal and
    *  will "clear" the Tree Viewer. If the specified input is the same as the
    *  current input and forceRefresh is false then the Tree Viewer will NOT be
    *  refreshed.
    *  </p>
    *  If the input is not of the appropriate type then an error will be logged
    *  and the input for this Tree Viewer will be set to null.
    * @param forceRefresh If this flag is false and the specified input is the
    *  same as the current input then the pane will NOT be refreshed. If this
    *  flag is true then the pane will always be refreshed.
    * @param selectDefaultNode if this parameter is true then select the
    *  "default node".
    */
  public final void setTheInput(
    Object input, boolean forceRefresh, boolean selectDefaultNode)
  {
    if (!forceRefresh && !hasInputChanged(input))
    {
      return;
    }

    if (!updateInput(input))
    {
      //String className = (input == null) ? null : input.getClass().getName();
      //String msg = new String(
      //  "The input to CommonTreeViewer.setInput() is invalid. inputType=" +
      //  className);
      //Activator.getInstance().logTrace(getClass(), msg);
      updateInput(null);
    }

    // Build/set the "tree model" for the Tree Viewer Content Provider
    //setInput(input);
    setInputAndSizeTableIfFirstTimeInput(input);

    // Expand the tree accordingly and then refresh
    expandTree();
    refresh();

    // Select the 1st "specified node" (if not null) in the tree
    if (selectDefaultNode)
    {
      selectDefaultNode();
    }
  }

  /** Implement this method to return the current "input" for this Pane.
    * 
    * @return the current "input" for this Pane. A value of null is legal return
    *  value.
    */
  @Override
  public Object getTheInput()
  {
    return getInput();
  }

  @Override
  public final Object getSelectedItem()
  {
    return CommonTableViewer.getSelectedItem(this);
  }

  static public Object getSelectedItem(TreeViewer viewer)
  {
    return CommonTableViewer.getSelectedItem(viewer);
  }

  public final Object getSelectedItemParent()
  {
    TreeItem item = getSelectedTreeItem();
    if(item!=null){
      TreeItem parentItem = item.getParentItem();
      if(parentItem!=null)
        return parentItem.getData();
    }
    return null;
  }

  public final List<?> getSelectedItems()
  {
    return getSelectedItems(this);
  }

  /** Return the currently selected items.
   * 
   * @return the currently selected items. If no items are selected then return
   *  an "empty" collection.
   */
  public static List<?> getSelectedItems(StructuredViewer viewer)
  {
    return getSelectedItems(viewer.getSelection());
  }

  /** Return the currently selected items.
   * 
   * @return the currently selected items. If no items are seleced then return
   *  an "empty" collection.
   */
  public static List<?> getSelectedItems(ISelection sel)
  {
    List<?> rtn = ms_noSelectedItems;
    if (sel instanceof StructuredSelection)
    {
      StructuredSelection structSel = (StructuredSelection)sel;
      rtn = structSel.toList();
    }
    return rtn;
  }
  
  public final TreeItem getSelectedTreeItem()
  {
    return getSelectedTreeItem(this);
  }

  static public TreeItem getSelectedTreeItem(TreeViewer viewer)
  {
    Tree tree = (viewer == null) ? null : viewer.getTree();
    TreeItem[] items = (tree == null) ? null : tree.getSelection();
    TreeItem item = (items != null && items.length == 1) ? items[0] : null;
    return item;
  }

  static public TreeItem getSelectedItemParentItem(TreeViewer viewer)
  {
    TreeItem item = getSelectedTreeItem(viewer);
    return (item == null) ? null : item.getParentItem();
  }

  static public Object getSelectedItemParent(TreeViewer viewer)
  {
    TreeItem item = getSelectedTreeItem(viewer);
    if (item != null)
    {
      TreeItem parentItem = item.getParentItem();
      return (parentItem == null) ? viewer.getInput() : parentItem.getData();
    }
    return null;
  }

  public final TreeItem findAnItem(Object element)
  {
    Widget widget = findItem(element);
    return (widget instanceof TreeItem) ? (TreeItem)widget : null;
  }

  public final TreeItem[] getSelectedTreeItems()
  {
    return m_tree.getSelection();
  }

  public final void selectNode(Object node)
  {
    if (node == null)
    {
      return;
    }

    StructuredSelection sel = new StructuredSelection(node);
    setSelection(sel, true);
  }

  public final void selectNodeForce(Object node)
  {
    try
    {
      m_isForcedSelect = true;
      selectNode(node);
    }
    finally
    {
      m_isForcedSelect = false;
    }
  }

  public final boolean isForcedSelect()
  {
    return m_isForcedSelect;
  }

  @Override
  public boolean isReadonly()
  {
    return (getTree().getStyle() & SWT.READ_ONLY) !=0;   
  }

  protected final void setInputAndSizeTable(Object input)
  {
    setInput(input);
    refresh(true);
    expandAll();
    packColumns();
  }

  /** Check to see if the input for this tree viewer has changed. The default
    * implementation simply compares the new input with the exisint input.
    *
    * @param newInput The new input to evaluate.
    *
    * @return true if the input for this tree viewer has changed, otherwise
    *  return false.
    */
  protected boolean hasInputChanged(Object newInput)
  {
    return (newInput != getTheInput());
  }

  protected final void setInputAndSizeTableIfFirstTimeInput(Object input)
  {
    setInput(input);
    refresh(true);

    if (!m_initialInputSet)
    {
      IContentProvider provider = getContentProvider();
      if (provider instanceof IStructuredContentProvider)
      {
        Object[] elements = ((IStructuredContentProvider)provider).getElements(input);
        if (elements != null && elements.length > 0)
        {
          m_initialInputSet = true;
          packColumns();
        }
      }
    }
  }

  protected final TreeViewerColumn addColumn(int style, String heading, int width)
  {
    return addColumn(style, heading, width, null, null, false, false);
  }

  protected final TreeViewerColumn addColumn(
    int style, String heading, int width, CellLabelProvider labelProvider)
  {
    return addColumn(style, heading, width, labelProvider, null, false, false);
  }

  protected final TreeViewerColumn addColumn(
    int style, String heading, int width, SelectionAdapter sortSelectionAdaptor)
  {
    return addColumn(style, heading, width, null, sortSelectionAdaptor, false, false);
  }

  protected final TreeViewerColumn addColumn(
    int style, String heading, int width, CellLabelProvider labelProvider,
    SelectionAdapter sortSelectionAdaptor)
  {
    return addColumn(
      style, heading, width, labelProvider, sortSelectionAdaptor, false, false);
  }

  protected final TreeViewerColumn addColumn(
    int style, String heading, int width, CellLabelProvider labelProvider, 
    SelectionAdapter sortSelectionAdaptor,
    boolean isResizable, boolean isRemovable)
  {
    TreeViewerColumn viewerCol = new TreeViewerColumn(this, style);
    if (labelProvider != null) {
      viewerCol.setLabelProvider(labelProvider);
    }

    TreeColumn col = viewerCol.getColumn();
    col.setText(heading);
    col.setWidth(width);
    col.setResizable(isResizable);
    col.setMoveable(isRemovable);
    if (sortSelectionAdaptor != null) {
      col.addSelectionListener(sortSelectionAdaptor);
    }

    return viewerCol;
  }

  protected void init(Composite parent)
  {
    // Use a "hash table" to map "node elements" to SWT "tree items". This
    // speeds things up and it also allows update() and refresh() to work correctly
    // if a "node element" appears multiple times in the "tree"
    setUseHashlookup(true);

    // Turn on the header and the lines
    Tree tree = getTree();
    tree.setHeaderVisible(true);
    tree.setLinesVisible(true);

    // Initialize the "tree control"
    setSorter(getDefaultSorter());
  }

  /** This method is called by setInput() when a new "input" is being set in
    * the Tree Viewer. If the new "input" is valid then store it and return true,
    * otherwise return false. 
    * 
    * @param input The new "input" for this Tree Viewer.
    *
    * @return true if the new "input" is valid, otherwise return false.
    */
  protected final boolean updateInput(Object input)
  {
    return true;
  }

  protected void expandTree()
  {
    expandToLevel(2);
  }

  protected final Object getDefaultNode()
  {
    return m_nodeToSelect;
  }

  protected void selectDefaultNode()
  {
    selectNode(getDefaultNode());
  }

  @Override
  public final void packColumns()
  {
    Tree tree = getTree();
    for (int i = 0, n = tree.getColumnCount(); i < n; i++)
    {
      TreeColumn col = tree.getColumn(i);
      col.pack();
    }
  }

  protected final ViewerSorter getDefaultSorter()
  {
    return m_defSorter;
  }

  protected final void createSetContextMenu()
  {
    disposeMenuManagerCheck();
    createActions();
    m_menuMgr = CommonUIHelper.createSetHookContextMenu(this, getTree());
  }
  
  protected final void disposeMenuManagerCheck()
  {
    if (m_menuMgr != null)
    {
      disposeActions();
      m_menuMgr.dispose();
      m_menuMgr = null;
    }
  }

  protected void createActions()
  {
  }

  protected void disposeActions()
  {
  }
  
  /**
   * This will be called each time menu is about to show.
   * @param manager menu manager
   */
  protected void fillContextMenu(IMenuManager manager)
  {
  }

  private final ViewerSorter m_defSorter = new ViewerSorter()
  {
    @Override
    public int compare(Viewer viewer, Object e1, Object e2)
    {
      String name1;
      String name2;
      
      if (viewer == null || !(viewer instanceof ContentViewer))
      {
        name1 = e1.toString();
        name2 = e2.toString();
      }
      else
      {
        IBaseLabelProvider prov = ((ContentViewer) viewer).getLabelProvider();
        if (prov instanceof ILabelProvider)
        {
          ILabelProvider lprov = (ILabelProvider) prov;
          name1 = lprov.getText(e1);
          name2 = lprov.getText(e2);
        }
        else
        {
          name1 = e1.toString();
          name2 = e2.toString();
        }
      }
      if (name1 == null) {
        name1 = "";//$NON-NLS-1$
      }
      if (name2 == null) {
        name2 = "";//$NON-NLS-1$
      }
      return name1.compareTo(name2);
    }
  };

  private final static List<Object> ms_noSelectedItems = new ArrayList<Object>(0);

  protected Tree        m_tree;
  protected MenuManager m_menuMgr;
  protected boolean     m_initialInputSet;
  protected Object      m_nodeToSelect;
  private   boolean     m_isForcedSelect;
}
