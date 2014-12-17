/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


public abstract class TableViewerSorter
  extends
    CommonViewerSorter
{
  public TableViewerSorter(TableViewer viewer)
  {
    super();
    m_tableViewer = viewer;
  }

  @Override
  protected final void setSortColumn(int columnIndex)
  {
    Table table = m_tableViewer.getTable();
    TableColumn[] cols = table.getColumns();
    if (columnIndex >= 0 && columnIndex < cols.length) {
      table.setSortColumn(cols[m_curColIndex]);
    }
  }

  @Override
  protected final void setSortDirection(int direction)
  {
    Table table = m_tableViewer.getTable();
    table.setSortDirection(direction);
  }

  private final TableViewer m_tableViewer;
}
