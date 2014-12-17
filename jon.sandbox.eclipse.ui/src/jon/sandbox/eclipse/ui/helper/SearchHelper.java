package jon.sandbox.eclipse.ui.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class SearchHelper
{
  /**
   * Searches "searchTerm" in "content" and returns an array of int pairs
   * (index, length) for each occurrence. The search is case-sensitive. The
   * consecutive occurrences are merged together.<code>
   * Examples:
   *   content = "123123x123", searchTerm = "1" --> [0, 1, 3, 1, 7, 1]
   *   content = "123123x123", searchTerm = "123" --> [0, 6, 7, 3]
   * 
   * @param searchTerm Can be null or empty. int[0] is returned in this case!
   * @param content A not-null string (can be empty!)
   *
   * @return an array of int pairs (index, length)
   */
  public static int[] getSearchTermOccurrences(
    final String searchTerm, final String content)
  {
    if (searchTerm == null || searchTerm.length() == 0) {
      return ms_noTermOccurrences;
    }
    if (content == null) {
      throw new IllegalArgumentException("content is null");
    }

    final List<Integer> list = new ArrayList<Integer>(10);
    int searchTermLength = searchTerm.length();
    int index;
    int fromIndex = 0;
    int lastIndex = -1;
    int lastLength = 0;

    while (true)
    {
      index = content.indexOf(searchTerm, fromIndex);
      if (index == -1)
      {
        // no occurrence of "searchTerm" in "content" starting from
        // index "fromIndex"
        if (lastIndex != -1)
        {
          // but there was a previous occurrence
          list.add(Integer.valueOf(lastIndex));
          list.add(Integer.valueOf(lastLength));
        }
        break;
      }
      if (lastIndex == -1)
      {
        // the first occurrence of "searchTerm" in "content"
        lastIndex = index;
        lastLength = searchTermLength;
      }
      else
      {
        if (lastIndex + lastLength == index)
        {
          // the current occurrence is right after the previous
          // occurrence
          lastLength += searchTermLength;
        }
        else
        {
          // there is at least one character between the current
          // occurrence and the previous one
          list.add(Integer.valueOf(lastIndex));
          list.add(Integer.valueOf(lastLength));
          lastIndex = index;
          lastLength = searchTermLength;
        }
      }
      fromIndex = index + searchTermLength;
    }

    final int n = list.size();
    final int[] result = new int[n];
    for (int i = 0; i != n; i++)
    {
      result[i] = list.get(i);
    }
    return result;
  }

  public static StyleRange[] getSearchTermStyleRanges(String searchText, String content)
  {
    StyleRange[] styleRanges = ms_noRanges;
    if (searchText != null && searchText.length() > 0)
    {
      int termRanges[] = getSearchTermOccurrences(searchText, content);
      List<StyleRange> styleRangeList = new ArrayList<StyleRange>(termRanges.length / 2);
      for (int i = 0; i < termRanges.length; i++)
      {
        int start = termRanges[i];
        i++;
        int length = termRanges[i];
        StyleRange range = new StyleRange(start, length, null, ms_colorYellow);
        styleRangeList.add(range);
      }
      styleRanges = styleRangeList.toArray(new StyleRange[styleRangeList.size()]);
    }
    return styleRanges;
  }

  private static final Color ms_colorYellow = Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
  private static final StyleRange[] ms_noRanges = new StyleRange[0];
  private static final int[] ms_noTermOccurrences = new int[0];
}
