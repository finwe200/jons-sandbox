/**
  * Copyright (c) 2014 Sorensen Software Inc.  All rights reserved.
  *
  * @author Jon Sorensen
  */
package jon.sandbox.common.ui;

/**
 * Constants.
 * @author chengdong
 */
public interface ICommonUIConstants
{
  public static final String ms_tooltipLineSep = "\n";
  public static final String ms_tooltipPairSep = ":";
  public static final String ms_unknown = new String("<Unknown>");
  public static final String ms_NA = new String("N/A");
  public static final String ms_null = new String("<Null>");
  public static final String ms_none = new String("<None>");
  public static final String ms_true = new String("True");
  public static final String ms_false = new String("False");
  public static final String ms_yes = new String("Yes");
  public static final String ms_no = new String("No");
  public static final String ms_negativeInfinity = new String("-\u221E");
  public static final String ms_positiveInfinity = new String("+\u221E");
  public static final String ms_emptyString = new String("");
  public static final Object[] ms_emptyArray = new Object[0];
  public static final String ms_blankString = new String(" ");
  public static final String ms_popupId = new String("#PopupMenu");
  public static final String ms_comma = new String(",");
  public static final String ms_lineSeparator = "\r\n";
  public static final String ms_defRegExp = "^ $";
  public static final String[] ms_booleanList = {
    "true",
    "false"
  }; // Order can not be changed
  public static final String ms_unavailable = "No content available";
  public static final int ms_observeDelay = 400;
}
