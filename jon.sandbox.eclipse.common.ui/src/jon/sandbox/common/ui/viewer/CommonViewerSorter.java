/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public abstract class CommonViewerSorter extends ViewerSorter
{
  protected CommonViewerSorter()
  {
    this(-1);
  }

  protected CommonViewerSorter(int column)
  {
    super();
    m_curColumn = column;
    m_sortDirection = ms_ascendingSort;
  }

  /**
    * Does the sort. If it's a different column from the previous sort, do an
    * ascending sort. If it's the same column as the last sort, toggle the sort
    * direction.
    * 
    * @param column
    */
  public final void doSort(int column)
  {
    if (column == m_curColumn)
    {
      // Same column as last sort; toggle the direction
      m_sortDirection = !m_sortDirection;
    }
    else
    {
      // New column; do an ascending sort
      m_curColumn = column;
      m_sortDirection = ms_ascendingSort;
    }
  }

  @Override
  public final int compare(Viewer viewer, Object arg1, Object arg2)
  {
    int rtn = doCompare(viewer, arg1, arg2); 

    // If descending order, flip the direction
    if (m_sortDirection == ms_descendingSort)
    {
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

  protected static final boolean ms_ascendingSort = true;
  protected static final boolean ms_descendingSort = false;

  protected int m_curColumn;
  private boolean m_sortDirection;
}
