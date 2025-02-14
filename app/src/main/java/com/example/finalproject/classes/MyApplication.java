package com.example.finalproject.classes;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // שמירת השפה האחרונה שנבחרה
        String savedLanguage = getSharedPreferences("Settings", MODE_PRIVATE)
                .getString("Language", "en");
        setLocale(savedLanguage);
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
