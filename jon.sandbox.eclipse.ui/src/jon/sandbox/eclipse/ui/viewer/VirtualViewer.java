package jon.sandbox.eclipse.ui.viewer;

import java.util.List;

import jon.sandbox.common.ui.helper.CommonUIHelper;
import jon.sandbox.common.ui.viewer.CommonTreeViewer;
import jon.sandbox.common.ui.viewer.TreeViewerSorter;
import jon.sandbox.common.ui.viewer.ViewerSortSelectionAdaptor;
import jon.sandbox.eclipse.ui.model.library.Author;
import jon.sandbox.eclipse.ui.model.library.Book;
import jon.sandbox.eclipse.ui.model.library.ModelProvider;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class VirtualViewer
  extends CommonTreeViewer
{
  public VirtualViewer(Composite parent, int style)
  {
    super(parent, style | SWT.VIRTUAL);
    init(parent);
  }

  @Override
  protected final void init(Composite parent)
  {
    // Perform common initialization
    super.init(parent);

    // Set a first/last name Filter
    //addFilter(m_nameFilter);

    // Create/configure the various table columns for editing and sorting
    addColumn(
      SWT.LEFT, "Author/Book", 200, new AuthorBookLabelProvider(),
      new ViewerSortSelectionAdaptor(this, ms_colIndexAuthorBook)
    );

    // Set the sorter for the table and make the initial sort be by
    // "author or book" (must call this method after columns have been
    // defined/added to the table viewer)
    LibraryTreeSorter sorter = new LibraryTreeSorter();
    setSorter(sorter);
    sorter.updateSortColumn(ms_colIndexAuthorBook);

    // Use a virtual "content provider"
    // MUST explicitly set the "items count"
    setContentProvider(new LibraryTreeContentProvider(this));
    setLabelProvider(new AuthorBookLabelProvider());

    // Set the input and then MUST set the "items count"
    List<Author> authors = ModelProvider.getInstance().getAuthors();
    setInput(authors);
    getTree().setItemCount(authors.size()); 

    // Create and set the "context menu" for the table
    //createSetContextMenu();
  }

  private class LibraryTreeContentProvider
    implements ILazyTreeContentProvider
  {
    public LibraryTreeContentProvider(VirtualViewer viewer)
    {
      super();
      m_viewer = viewer;
      m_elements = null;
    }

    @Override
    public void dispose()
    {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
      m_elements = (List<Author>)newInput;
    }

    @Override
    public void updateElement(Object parent, int index)
    {
      Object element = (parent instanceof Author) ?
        ((Author)parent).getBooks().get(index) : m_elements.get(index);
      m_viewer.replace(parent, index, element);
      updateChildCount(element, -1);
      
    }

    @Override
    public void updateChildCount(Object element, int currentChildCount)
    {
      int count = 0;
      if (element instanceof Author) {
        count = ((Author)element).getBooks().size();
      } 
      else if (element == m_elements) {
        count = m_elements.size();
      }
      m_viewer.setChildCount(element, count);
    }

    @Override
    public Object getParent(Object element)
    {
      return (element instanceof Author) ? m_elements : ((Book)element).getAuthor();
    }

    private final VirtualViewer m_viewer;
    private List<Author> m_elements;
  }

  private class AuthorBookLabelProvider extends ColumnLabelProvider
  {
    public AuthorBookLabelProvider()
    {
      super();

      m_authorImage = CommonUIHelper.getImage(VirtualViewer.class, "author.png");
      m_bookImage = CommonUIHelper.getImage(VirtualViewer.class, "book.gif");
    }

    @Override
    public void dispose()
    {
      CommonUIHelper.dispose(m_authorImage);
      CommonUIHelper.dispose(m_bookImage);

      super.dispose();
    }

    @Override
    public Image getImage(Object element)
    {
      return (element instanceof Author) ? m_authorImage : m_bookImage;
    }

    @Override
    public String getText(Object element)
    {
      if (element instanceof Author)
      {
        Author author = (Author)element;
        return author.getLastName() + " " + author.getFirstName();
      }
      return ((Book)element).getTitle();
    }

    private final Image m_authorImage;
    private final Image m_bookImage;
  }

  private class LibraryTreeSorter
  extends TreeViewerSorter
{
  public LibraryTreeSorter()
  {
    super(VirtualViewer.this);
  }

  @Override
  protected int doCompare(Viewer viewer, Object arg1, Object arg2)
  {
    int rtn = 0;
    try
    {
      if (arg1 instanceof Author)
      {
        Author a1 = (Author)arg1;
        Author a2 = (Author)arg2;

        rtn = compare(a1.getLastName(), a2.getLastName());
        if (rtn == 0)
        {
          // Do a secondary sort by "first name"
          rtn = compare(a1.getFirstName(), a2.getFirstName());
        }
      }
      else
      {
        Book b1 = (Book)arg1;
        Book b2 = (Book)arg2;
        rtn = compare(b1.getTitle(), b2.getTitle());
      }
    }
    catch (Throwable ex)
    {
      rtn = 0;
    }
    return rtn;
  }
}

  private static final int ms_colIndexAuthorBook = 0;
}
