package com.example.finalproject;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.finalproject.activities.MainActivity;

import java.util.Locale;
public class LocaleUtils {

    // פונקציה לשינוי השפה
    public static void setLocale(MainActivity activity, String languageCode) {
        // יצירת Locale חדש
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        // גישה למשאבים והגדרות
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        // עדכון השפה
        config.setLocale(locale);
        resources.updateConfiguration(config, metrics);

        // רענון ה-Activity
        activity.recreate();
    }
}