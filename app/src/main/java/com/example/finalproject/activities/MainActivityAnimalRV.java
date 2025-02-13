package com.example.finalproject.activities;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Adapter.AnimalAdapter;
import com.example.finalproject.Fragments.FragmentAddAnimal;
import com.example.finalproject.Fragments.FragmentZoo;
import com.example.finalproject.LocaleUtils;
import com.example.finalproject.R;
import com.example.finalproject.classes.myAnimals;
import com.example.finalproject.models.Animal;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivityAnimalRV extends AppCompatActivity {

    private static final int ADD_PRODUCT_REQUEST = 1;
    private ArrayList<Animal> animalList;
    //private LinearLayout animalContainer;
    private ActivityResultLauncher<Intent> addAnimalLauncher;
    private FirebaseAuth mAuth;
    private AnimalAdapter adapter;
    private RecyclerView animalContainer;

    private SearchView searchView;

    private LinearLayoutManager layoutManager;

    public MainActivityAnimalRV() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_animal_rv);

        Intent intent = getIntent();
        //String email1 = ((TextView)findViewById(R.id.Email_login)).getText().toString();

        TextView welcomeTextView = findViewById(R.id.welcome_text);
        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        welcomeTextView.setText("Hello! welcome to the Zoo, " + email + "!");

        RecyclerView animalContainer = findViewById(R.id.animal_type_container);
        layoutManager = new LinearLayoutManager(this);
        animalContainer.setLayoutManager(layoutManager);
        animalContainer.setItemAnimator(new DefaultItemAnimator());
        Button languageButton = findViewById(R.id.language);

        animalList = new ArrayList<>();
        for (int i = 0; i < myAnimals.nameArray.length; i++) {
            animalList.add(new Animal(
                    myAnimals.nameArray[i],
                    myAnimals.typeArray[i],
                    myAnimals.descriptionArray[i],
                    myAnimals.drawableArray[i]
                    ));
        }

        adapter = new AnimalAdapter(animalList);
        animalContainer.setAdapter(adapter);
        searchView=findViewById(R.id.searchView);
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

        /*Button addAnimalButton = findViewById(R.id.add_animal_page);
        Button languageButton = findViewById(R.id.language);


        addAnimalLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                String animalName = data.getStringExtra("animalName");
                String animalType = data.getStringExtra("animalType");
                String animalDescription = data.getStringExtra("animalDescription");
                Animal newAnimal = new Animal(animalName, animalType, animalDescription);
                animalList.add(newAnimal);
                adapter.notifyDataSetChanged();
                refreshAnimalList();
            }
        });

        addAnimalButton.setOnClickListener(v -> {
            Intent addAnimalIntent = new Intent(MainActivityAnimalRV.this, AddAnimalActivity.class);
            addAnimalLauncher.launch(addAnimalIntent);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            String animalName = data.getStringExtra("animalName");
            String animalType = data.getStringExtra("animalType");
            String animalDescription = data.getStringExtra("animalDescription");
            Animal newAnimal = new Animal(animalName, animalType, animalDescription);
            animalList.add(newAnimal);
            refreshAnimalList();
        }
    }

    private void refreshAnimalList() {
        //animalContainer.removeAllViews();
        for (Animal animal : animalList) {
            @SuppressLint("InflateParams") View animalView = getLayoutInflater().inflate(R.layout.cardview, null);
            TextView animalNameTextView = animalView.findViewById(R.id.animal_name_text1);
            TextView animalTypeTextView = animalView.findViewById(R.id.animal_type_text);
            TextView animalDescriptionTextView = animalView.findViewById(R.id.animal_description_text);

            animalNameTextView.setText("Name:" + animal.getName());
            animalTypeTextView.setText("Type: " + animal.getType());
            animalDescriptionTextView.setText("Description: " + animal.getType());

            animalContainer.addView(animalView);
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged(); // עדכון ה-RecyclerView
        }*/
    }

}