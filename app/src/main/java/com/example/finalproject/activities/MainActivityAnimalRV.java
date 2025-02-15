package com.example.finalproject.activities;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Adapter.AnimalAdapter;
import com.example.finalproject.LocaleUtils;
import com.example.finalproject.R;
import com.example.finalproject.classes.myAnimals;
import com.example.finalproject.models.Animal;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivityAnimalRV extends AppCompatActivity {

    private static final int ADD_PRODUCT_REQUEST = 1;
    private ArrayList<Animal> animalList;
    private ActivityResultLauncher<Intent> addAnimalLauncher;
    private FirebaseAuth mAuth;
    private AnimalAdapter adapter;
    private RecyclerView animalContainer;
    private SearchView searchView;
    private TextView welcomeTextView;
    private LinearLayoutManager layoutManager;

    public MainActivityAnimalRV() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_animal_rv);

        myAnimals.initializeArrays(this);

        welcomeTextView = findViewById(R.id.welcome_text);
        searchView = findViewById(R.id.searchView);
        animalContainer = findViewById(R.id.animal_type_container);
        Button languageButton = findViewById(R.id.language);

        mAuth = FirebaseAuth.getInstance();
        updateWelcomeMessage();

        layoutManager = new LinearLayoutManager(this);
        animalContainer.setLayoutManager(layoutManager);
        animalContainer.setItemAnimator(new DefaultItemAnimator());

        animalList = new ArrayList<>();
        String[] names = myAnimals.getNameArray();
        String[] types = myAnimals.getTypeArray();
        String[] descriptions = myAnimals.getDescriptionArray();
        for (int i = 0; i < names.length; i++) {
            animalList.add(new Animal(
                    names[i],
                    types[i],
                    descriptions[i],
                    myAnimals.drawableArray[i]
            ));
        }
        adapter = new AnimalAdapter(animalList,this);
        animalContainer.setAdapter(adapter);

        searchView.setQueryHint(getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });

        languageButton.setOnClickListener(v -> showLanguageDialog());
    }

    private void updateWelcomeMessage() {
        String email = mAuth.getCurrentUser().getEmail();
        String currentLanguage = getResources().getConfiguration().locale.getLanguage();

        if (currentLanguage.equals("iw")) {
            welcomeTextView.setText("שלום! ברוכים הבאים לגן החיות, " + email + "!");
        } else {
            welcomeTextView.setText("Hello! Welcome to the Zoo, " + email + "!");
        }
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        updateWelcomeMessage(); // עדכון הודעת הברוכים הבאים לפני יצירה מחדש של האקטיביטי
        recreate();
    }

    private void showLanguageDialog() {
        String[] languages = {"English", "עברית"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(languages, -1, (dialog, which) -> {
            switch (which) {
                case 0:
                    setLocale("en");
                    break;
                case 1:
                    setLocale("iw");
                    break;
            }
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}