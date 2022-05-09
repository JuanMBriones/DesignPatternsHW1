package com.itesm.financial;

import java.util.ArrayList;
import java.util.List;

/**
 * Taken from https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */
public class CSVUtils {

  private static final char DEFAULT_SEPARATOR = ',';
  private static final char DEFAULT_QUOTE = '"';

  public static List<String> parseLine(String cvsLine) {
    List<String> result = new ArrayList<>();

    if (cvsLine == null || cvsLine.isEmpty()) {
      return result;
    }

    StringBuffer curVal = new StringBuffer();
    boolean inQuotes = false;
    boolean startCollectChar = false;
    boolean doubleQuotesInColumn = false;

    char[] chars = cvsLine.toCharArray();

    for (char ch : chars) {
      if (inQuotes) {
        startCollectChar = true;
        if (ch == DEFAULT_QUOTE) {
          inQuotes = false;
          doubleQuotesInColumn = false;
        } else {
          if (ch == '\"') {
            if (!doubleQuotesInColumn) {
              curVal.append(ch);
              doubleQuotesInColumn = true;
            }
          } else {
            curVal.append(ch);
          }
        }
      } else {
        if (ch == DEFAULT_QUOTE) {
          inQuotes = true;

          if (chars[0] != '"') {
            curVal.append('"');
          }
          if (startCollectChar) {
            curVal.append('"');
          }

        } else if (ch == DEFAULT_SEPARATOR) {
          result.add(curVal.toString());

          curVal = new StringBuffer();
          startCollectChar = false;

        } else if (ch == '\r') {
          // ignore LF characters
          continue;
        } else if (ch == '\n') {
          break;
        } else {
          curVal.append(ch);
        }
      }
    }

    result.add(curVal.toString());

    // result.forEach(System.out::println);

    return result;
  }
}
