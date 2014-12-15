/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ViewerSortSelectionAdaptor extends SelectionAdapter
{
  public ViewerSortSelectionAdaptor(
    StructuredViewer viewer, CommonViewerSorter sorter, int initialColIndex)
  {
    super();

    m_viewer = viewer;
    m_sorter = sorter;
    m_colIndex = initialColIndex;
  }

  @Override
  public void widgetSelected(SelectionEvent event)
  {
    m_sorter.doSort(m_colIndex);
    m_viewer.refresh(true);
  }

  private final StructuredViewer m_viewer;
  private final CommonViewerSorter m_sorter;
  private int m_colIndex = -1;
}
