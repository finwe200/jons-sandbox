/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;


public abstract class TreeViewerSorter
  extends
    CommonViewerSorter
{
  public TreeViewerSorter(TreeViewer viewer)
  {
    super();
    m_treeViewer = viewer;
  }

  @Override
  protected final void setSortColumn(int columnIndex)
  {
    Tree tree = m_treeViewer.getTree();
    TreeColumn[] cols = tree.getColumns();
    if (columnIndex >= 0 && columnIndex < cols.length) {
      tree.setSortColumn(cols[m_curColIndex]);
    }
  }

  @Override
  protected final void setSortDirection(int direction)
  {
    Tree tree = m_treeViewer.getTree();
    tree.setSortDirection(direction);
  }

  private final TreeViewer m_treeViewer;
}
