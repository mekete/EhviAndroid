package net.ehvi.android.update.utility;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.Random;
import java.util.UUID;

public class StringUtil {

    public static final String DATE_FORMAT_CHAT_TIME = "MMM dd, hh:mm a";
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int ALPHANUMERIC_LENGTH = ALPHANUMERIC.length();

    // ---------------------------------------------------------------------------------------------
    public static String generateShortUuid() {
        return generateShortUuid(8);
    }

    public static String generateShortUuid(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC_LENGTH)));
        }
        return builder.toString();
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

    public static String toString(Object object) {
        if (object == null || StringUtil.isNullOrEmpty(object.toString())) {
            return "";
        }
        return object.toString();
    }

    public static int toSafeInteger(String string) {
        if (string == null || isNullOrEmpty(string) || !isIntegerConvertible(string)) {
            return 0;
        }
        return Integer.parseInt(string);
    }

    public static <T> String getSafeString(T value) {
        return getSafeString(value, "");
    }

    public static <T> String getSafeString(T value, String defaultValue) {
        return value == null ? defaultValue : "" + value;
    }

    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static float toSafeFloat(String string) {
        if (string == null || isNullOrEmpty(string) || !isFloatConvertible(string)) {
            return 0F;
        }
        return Float.parseFloat(string);
    }

    public static double toSafeDouble(String string) {
        if (string == null || isNullOrEmpty(string) || !isFloatConvertible(string)) {
            return 0F;
        }
        return Double.parseDouble(string);
    }


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

    public static boolean isDoubleConvertible(String string) {
        try {
            Double.valueOf(string);
            return true;
        } catch (Exception ex) {
        }
        return false;
    }


    public static boolean isFloatConvertible(String string) {
        try {
            Float.valueOf(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
