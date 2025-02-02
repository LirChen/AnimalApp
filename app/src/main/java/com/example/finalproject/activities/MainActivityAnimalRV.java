package com.example.finalproject.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.Fragments.FragmentAddAnimal;
import com.example.finalproject.Fragments.FragmentZoo;
import com.example.finalproject.R;
import com.example.finalproject.models.Animal;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivityAnimalRV extends AppCompatActivity {

    private static final int ADD_PRODUCT_REQUEST = 1;
    private final MainActivityAnimalRV mainActivityAnimalRV;
    private final ArrayList<Animal> animalList = new ArrayList<>();
    private LinearLayout animalContainer;
    private FragmentZoo animalListFragment;
    private ActivityResultLauncher<Intent> addAnimalLauncher;
    private FirebaseAuth mAuth;

    public MainActivityAnimalRV(MainActivityAnimalRV mainActivityAnimalRV) {
        this.mainActivityAnimalRV = mainActivityAnimalRV;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_animal_rv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*if (savedInstanceState == null) {
            animalListFragment = new FragmentZoo();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, animalListFragment, "list")
                    .commit();
        } else {
            animalListFragment = (FragmentZoo)
                    getSupportFragmentManager().findFragmentByTag("list");
        }*/

        Intent intent = getIntent();
        //String email1 = ((TextView)findViewById(R.id.Email_login)).getText().toString();

        TextView welcomeTextView = findViewById(R.id.welcome_text);
        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        welcomeTextView.setText("Hello! welcome to the Zoo, " + email + "!");

        animalContainer = findViewById(R.id.animal_type_container);

        //Button languageButton = findViewById(R.id.language);


        addAnimalLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                String animalName = data.getStringExtra("animalName");
                String animalType = data.getStringExtra("animalType");
                String animalDescription = data.getStringExtra("animalDescription");
                Animal newAnimal = new Animal(animalName, animalType, animalDescription);
                animalList.add(newAnimal);
                refreshAnimalList();
            }
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

    public void showAddAnimalFragment() {
        FragmentAddAnimal addFragment = new FragmentAddAnimal();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, addFragment)
                .addToBackStack(null)
                .commit();
    }

    private void refreshAnimalList() {
        animalContainer.removeAllViews();
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
    }

}