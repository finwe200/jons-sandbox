/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;


import java.util.ArrayList;
import java.util.List;

import jon.sandbox.common.ui.ICommonUIConstants;
import jon.sandbox.common.ui.helper.CommonUIHelper;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public abstract class CommonTableViewer
  extends TableViewer
  implements ICommonViewer, IMenuListener, DisposeListener
{
  public static final String LINE_SEP = ICommonUIConstants.ms_lineSeparator;

  protected CommonTableViewer(Composite parent, int style)
  {
    super(parent, style);
    Assert.isNotNull(parent);

    m_inputSet = false;

    // Add a "dispose listener" so this table will dispose it's affiliate
    // content provider
    Control ctrl = getControl(); 
    ctrl.addDisposeListener(this);
  }

  @Override
  public StructuredViewer getStructuredViewer()
  {
    return this;
  }

  @Override
  public boolean isReadonly()
  {
    return (getTable().getStyle() & SWT.READ_ONLY) !=0;      
  }

  @Override
  public final void menuAboutToShow(IMenuManager manager)
  {
    fillContextMenu(manager);
  }

  @Override
  public void widgetDisposed(DisposeEvent evt)
  {
    // If one exists, free the manager for the context menu
    disposeMenuManagerCheck();

    Control ctrl = getControl();
    if (ctrl != null)
    {
      ctrl.removeDisposeListener(this);
    }

    IContentProvider contentProvider = getContentProvider();
    if (contentProvider != null)
    {
      //-**THIS CODE IS NEVER CALLED!**
      contentProvider.dispose();
    }

    IBaseLabelProvider labelProvider = getLabelProvider();
    if (labelProvider != null)
    {
      labelProvider.dispose();
    }
  }

  @Override
  public Object getTheInput()
  {
    return getInput();
  }

  public final void setInputAndSizeTable(Object input)
  {
    setInput(input);
    initViewerForNewInput();
    packColumns();
  }

  public final void setInputAndSizeColumnsIfFirstTimeInput(Object input)
  {
    setInput(input);
    if (!m_inputSet)
    {
      IContentProvider provider = getContentProvider();
      if (provider instanceof IStructuredContentProvider)
      {
        Object[] elements = ((IStructuredContentProvider)provider).getElements(input);
        if (elements != null && elements.length > 0)
        {
          m_inputSet = true;
          packColumns();
        }
      }
    }
    initViewerForNewInput();
  }

  public int getNumElements()
  {
    int rtn = getTable().getItemCount();
    return rtn;
  }
  
  /** Return the currently selected item.
   * 
   * @return the currently selected item. Return null if more than one item
   *  is selected or no items are selected.
   */
  @Override
  public Object getSelectedItem()
  {
    return getSelectedItem(this);
  }

 /** Return the currently selected item.
   * 
   * @return the currently selected item. Return null if more than one item
   *  is selected or no items are selected.
   */
  public static Object getSelectedItem(StructuredViewer viewer)
  {
    return getSelectedItem(viewer.getSelection());
  }

  /** Return the currently selected item.
   * 
   * @return the currently selected item. Return null if more than one item
   *  is selected or no items are selected.
   */
  public static Object getSelectedItem(ISelection sel)
  {
    Object rtn = null;
    if (sel instanceof StructuredSelection)
    {
      StructuredSelection structSel = (StructuredSelection)sel;
      rtn = (structSel.size() == 1) ? structSel.getFirstElement() : null;
    }
    return rtn;
  }

  /** Return the currently selected items.
   * 
   * @return the currently selected items. If no items are seleced then return
   *  an "empty" collection.
   */
  public List<?> getSelectedItems()
  {
    return getSelectedItems(this);
  }

  /** Return the currently selected items.
   * 
   * @return the currently selected items. If no items are seleced then return
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

  public int getSelectedItemIndex()
  {
    Table tbl = getTable();
    int index = tbl.getSelectionIndex();
    return index;
  }

  static public void selectItem(StructuredViewer viewer, Object item)
  {
    if (item != null)
    {
      ISelection sel = new StructuredSelection(item);
      viewer.setSelection(sel);
    }
  }

  public void selectItem(Object item)
  {
    selectItem(this, item);
  }

  public void selectItems(List<Object> items)
  {
    if (items != null && items.size() > 0)
    {
      ISelection sel = new StructuredSelection(items);
      setSelection(sel);
    }
  }

  static public void selectItemInList(TableViewer viewer, int index)
  {
    Table table = viewer.getTable();
    if (index >= table.getItemCount())
    {
      return;
    }

    TableItem ti = table.getItem(index);
    Object itemData = (ti == null) ? null : ti.getData();
    if (itemData != null)
    {
      selectItem(viewer, itemData);
    }
  }

  public void selectItemInList(int index)
  {
    selectItemInList(this, index);
  }

  static public void selectFirstItemInList(TableViewer viewer)
  {
    selectItemInList(viewer, 0);
  }

  public void selectFirstItemInList()
  {
    selectFirstItemInList(this);
  }

  /** Override initViewerForNewInput() if you need to do nothing special after
    * the input is set. This method will call
    * setInputAndSizeColumnsIfFirstTimeInput().
    */
  @Override
  public void setTheInput(Object input)
  {
    setInputAndSizeColumnsIfFirstTimeInput(input);
  }

  protected void initViewerForNewInput()
  {
  }

  protected TableViewerColumn addColumn(
    String label, int width, CellLabelProvider labelProvider,
    EditingSupport editingSupport,
    ViewerSortSelectionAdaptor sortSelectionAdaptor,
    boolean isResizable, boolean isMovable)
  {
    TableViewerColumn viewerCol = new TableViewerColumn(this, SWT.NONE);

    viewerCol.setLabelProvider(labelProvider);
    viewerCol.setEditingSupport(editingSupport);

    TableColumn col = viewerCol.getColumn();
    col.setWidth(width);
    col.setText(label);
    col.setResizable(isResizable);
    col.setMoveable(isMovable);
    col.addSelectionListener(sortSelectionAdaptor);

    return viewerCol;
  }

  protected TableViewerColumn addColumn(
    String label, int width, CellLabelProvider labelProvider)
  {
    return addColumn(label, width, labelProvider, null, null, true, true);
  }

  protected TableViewerColumn addColumn(
    String label, int width, CellLabelProvider labelProvider,
    EditingSupport editingSupport)
  {
    return addColumn(label, width, labelProvider, editingSupport, null, true, true);
  }

  protected TableViewerColumn addColumn(
    String label, int width, CellLabelProvider labelProvider,
    ViewerSortSelectionAdaptor sortSelectionAdaptor)
  {
    return addColumn(
      label, width, labelProvider, null, sortSelectionAdaptor, true, true);
  }

  protected TableViewerColumn addColumn(
    String label, int width, CellLabelProvider labelProvider,
    EditingSupport editingSupport, ViewerSortSelectionAdaptor sortSelectionAdaptor)
  {
    return addColumn(
      label, width, labelProvider, editingSupport, sortSelectionAdaptor, true, true);
  }

  static public void packColumns(TableViewer viewer)
  {
    Table table = viewer.getTable();
    for (int i = 0, n = table.getColumnCount(); i < n; i++)
    {
      TableColumn col = table.getColumn(i);
      if(col.getWidth()!=0) // avoid pack the invisible columns
        col.pack();
    }
  }

  @Override
  public void packColumns()
  {
    packColumns(this);
  }

  // Override this method for adding columns, etc.
  protected void init()
  {
    // Make lines and header visible
    final Table table = getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
  }

  protected final void createSetContextMenu()
  {
    Control ctrl = getControl();
    disposeMenuManagerCheck();
    createActions();
    m_menuMgr = CommonUIHelper.createSetHookContextMenu(this, ctrl);
  }

  protected void createActions()
  {
  }

  protected void fillContextMenu(IMenuManager manager)
  {
  }

  protected void disposeActions()
  {
  }

  protected Shell getShell()
  {
    return getControl().getShell();
  }

  private void disposeMenuManagerCheck()
  {
    if (m_menuMgr != null)
    {
      disposeActions();
      m_menuMgr.dispose();
      m_menuMgr = null;
    }
  }

  private final static List<Object> ms_noSelectedItems = new ArrayList<Object>(0);
  protected boolean m_inputSet;
  protected MenuManager m_menuMgr;
}
