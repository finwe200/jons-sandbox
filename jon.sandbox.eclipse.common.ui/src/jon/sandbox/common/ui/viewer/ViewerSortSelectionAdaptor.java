/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ViewerSortSelectionAdaptor extends SelectionAdapter
{
  public ViewerSortSelectionAdaptor(StructuredViewer viewer, int columnIndex)
  {
    super();
    Assert.isNotNull(viewer);

    m_viewer = viewer;
    m_colIndex = columnIndex;
  }

  @Override
  public void widgetSelected(SelectionEvent event)
  {
    ViewerSorter sorter = m_viewer.getSorter();
    if (sorter instanceof CommonViewerSorter)
    {
      ((CommonViewerSorter)sorter).updateSortColumn(m_colIndex);
      m_viewer.refresh(true);
    }
  }

  private final StructuredViewer m_viewer;
  private int m_colIndex = -1;
}
