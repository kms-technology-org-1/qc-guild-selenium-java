package org.kms.com.groupup04.utils;

public class StringUtil {

    public static String castRestParameter(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        return locator;
    }
}