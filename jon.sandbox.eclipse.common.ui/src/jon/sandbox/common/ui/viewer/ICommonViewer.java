/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui.viewer;

import org.eclipse.jface.viewers.StructuredViewer;

public interface ICommonViewer
{
  public Object getSelectedItem();

  public StructuredViewer getStructuredViewer();

  public Object getTheInput();

  public void setTheInput(Object input);
  
  public boolean isReadonly();

  public void packColumns();
}
