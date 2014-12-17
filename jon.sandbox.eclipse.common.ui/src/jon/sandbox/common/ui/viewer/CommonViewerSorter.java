/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;

public abstract class CommonViewerSorter extends ViewerSorter
{
  protected CommonViewerSorter()
  {
    super();
    m_curColIndex = -1;
    m_sortDirection = SWT.NONE;
  }

  /**
    * Update the column to sort. If the specified column is different from the
    * previously sorted column then do an ascending sort. Otherwise toggle the
    * sort direction.
    * 
    * @param columnIndex
    */
  public final void updateSortColumn(int columnIndex)
  {
    if (columnIndex == m_curColIndex)
    {
      // Same column as last sort; toggle the direction
      m_sortDirection = (m_sortDirection == SWT.DOWN) ? SWT.UP : SWT.DOWN;
    }
    else
    {
      // New column; do an ascending sort
      m_curColIndex = columnIndex;
      setSortColumn(columnIndex);
      m_sortDirection = SWT.DOWN;
    }
    setSortDirection(m_sortDirection);
  }

  @Override
  public final int compare(Viewer viewer, Object arg1, Object arg2)
  {
    int rtn = doCompare(viewer, arg1, arg2); 

    // If descending order, flip the direction
    if (m_sortDirection == SWT.UP) {
      rtn = -rtn;
    }

    return rtn;
  }

  /** Implement this method for custom sorting behavior.
   * 
   * @param viewer
   * @param e1
   * @param e2
   * @return
   */
  protected abstract int doCompare(Viewer viewer, Object arg1, Object arg2); 

  /* Inform the underlying viewer of the "sort column".
   * 
   * @param column The zero relative column index.
   */
  protected abstract void setSortColumn(int columnIndex);

  /* Inform the underlying viewer of the "sort direction".
   * 
   * @param direction Can be SWT.UP or SWT.DOWN
   */
  protected abstract void setSortDirection(int direction);

  @SuppressWarnings("unchecked")
  protected final int compare(Object arg1, Object arg2)
  {
    return getComparator().compare(arg1, arg2);
  }

  protected final int compare(int arg1, int arg2)
  {
    return (arg1 > arg2) ? 1 : (arg1 == arg2) ? 0 : -1;
  }

  protected int defaultCompare(Viewer viewer, Object arg1, Object arg2)
  {
    int result = 0;
    String n1 = arg1.getClass().getName();
    String n2 = arg2.getClass().getName();

    result = super.compare(viewer, n1, n2);
    if (result == 0) {
      result = super.compare(viewer, arg1, arg2);
    }
    return result;
  }

  protected int m_curColIndex;
  protected int m_sortDirection;
}
