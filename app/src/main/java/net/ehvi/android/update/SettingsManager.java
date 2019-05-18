package net.ehvi.android.update;

import android.content.Context;
import android.preference.PreferenceManager;

public class SettingsManager {


    public static boolean isFirstTimeLaunch(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.pref_key_first_time_launch), true);
    }

    public static void setFirstTimeLaunch(boolean value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(context.getString(R.string.pref_key_first_time_launch), value).commit();
    }

    public static int getVersionCode(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.pref_key_version_code), -1);
    }

    public static void setVersionCode(int value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(context.getString(R.string.pref_key_version_code), value).commit();
    }

    // -------------------------------------------------------------------------

}


