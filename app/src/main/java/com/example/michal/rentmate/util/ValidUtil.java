package com.example.michal.rentmate.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidUtil {

  public static boolean isValidEmail(String email) {
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static boolean isValidPassword(String pass) {
    if (pass != null && pass.length() >= 4) {
      return true;
    }
    return false;
  }
}
