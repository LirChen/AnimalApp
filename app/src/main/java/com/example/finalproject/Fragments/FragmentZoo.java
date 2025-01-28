package com.example.finalproject.Fragments;

import static android.icu.util.ULocale.getLanguage;
import static androidx.core.app.ActivityCompat.recreate;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.finalproject.LocaleUtils;
import com.example.finalproject.R;
import com.example.finalproject.activities.MainActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Locale;

public class FragmentZoo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zoo, container, false);
        // מציאת הכפתור לפי ה-ID
        Button languageButton = view.findViewById(R.id.language);

        // הגדרת לחיצה על הכפתור
        languageButton.setOnClickListener(v -> showLanguageDialog());
        return view;
    }

    // פונקציה שמציגה את חלון הבחירה
    private void showLanguageDialog() {
        // רשימת השפות שיופיעו בתפריט
        String[] languages = {"English", "עברית", "Français", "Español"};
        String[] languageCodes = {"en", "he", "fr", "es"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose a Language")
                .setItems(languages, (dialog, which) -> {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    if (mainActivity != null) {
                        // שינוי השפה לפי הבחירה של המשתמש
                        LocaleUtils.setLocale(mainActivity, languageCodes[which]);
                    }
                });

        builder.create().show();
    }
}