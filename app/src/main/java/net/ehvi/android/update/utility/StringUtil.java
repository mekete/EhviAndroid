package net.ehvi.android.update.utility;

import android.text.TextUtils;

import androidx.annotation.NonNull;

public class StringUtil {

    public static boolean isNullOrEmpty(@NonNull String string) {
        return TextUtils.isEmpty(string) || string.equalsIgnoreCase("null");
    }

    @NonNull
    public static String safeTrim(@NonNull String string) {
        return isNullOrEmpty(string) ? "" : string.toString().trim();
    }

    public static boolean isIntegerConvertible(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (Exception ex) {
        }
        return false;
    }
    public static int toSafeInteger(String string) {
        try {
           return Integer.valueOf(string);
        } catch (Exception ex) {
            return Integer.MIN_VALUE;

        }
    }
}
