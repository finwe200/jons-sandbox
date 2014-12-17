package jon.sandbox.eclipse.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class CreatePersonHandler
  extends AbstractHandler
  implements IHandler
{
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    MessageDialog.openWarning(
      Display.getCurrent().getActiveShell(), "Unimplmented Feature",
      "The command to create a Person is a work in progress!"
    );
    return null;
  }
}
