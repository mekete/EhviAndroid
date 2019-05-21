package net.ehvi.android.update;

import android.content.Context;
import android.preference.PreferenceManager;

public class SettingsManager {

    private static final String PREF_KEY_FIRST_TIME_LAUNCH ="PREF_KEY_FIRST_TIME_LAUNCH";
    private static final String PREF_KEY_VERSION_CODE ="PREF_KEY_VERSION_CODE";

    public static boolean isFirstTimeLaunch(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_FIRST_TIME_LAUNCH, true);
    }

    public static void setFirstTimeLaunch(boolean value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_FIRST_TIME_LAUNCH, value).commit();
    }

    public static int getVersionCode(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(PREF_KEY_VERSION_CODE, -1);
    }

    public static void setVersionCode(int value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(PREF_KEY_VERSION_CODE, value).commit();
    }

    // -------------------------------------------------------------------------

}


