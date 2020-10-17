package com.assignment.assignment.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Divyanshu  on 1/9/20.
 */
public class StringUtils {

  public static int getNextPageUrl(String url) {
    String[] urlParts = TextUtils.split(url, "8000/");
    int page = Integer.parseInt(urlParts[1].replaceAll("[^0-9]", ""));
    Log.d("TAG", "getNextPageUrl: " + urlParts[1] + "  " + page);
    return page;
  }

  public static int getNextPageNumber(String str){
    int i = 0;
    int num = 0;
    boolean isNeg = false;

    // Check for negative sign; if it's there, set the isNeg flag
    if (str.charAt(0) == '-') {
      isNeg = true;
      i = 1;
    }

    // Process each character of the string;
    while( i < str.length()) {
      num *= 10;
      num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
    }

    if (isNeg)
      num = -num;
    return num;
  }
}
