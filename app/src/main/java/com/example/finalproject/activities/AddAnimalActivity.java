package com.example.finalproject.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;
import com.example.finalproject.models.Animal;

import java.util.ArrayList;

public class AddAnimalActivity extends AppCompatActivity {

    private EditText animalNameEditText, animalaTypeEditText,animalaDescriptionEditText;
    private Button addAnimalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_animal);

        animalNameEditText = findViewById(R.id.AnimalName);
        animalaTypeEditText = findViewById(R.id.AnimalType);
        animalaDescriptionEditText = findViewById(R.id.AnimalDescription);
        addAnimalButton = findViewById(R.id.Add_animal_button);

        addAnimalButton.setOnClickListener(v -> {
            String animalName = animalNameEditText.getText().toString();
            String animalType = animalaTypeEditText.getText().toString();
            String animalDescription = animalaDescriptionEditText.getText().toString();

            if (animalName.isEmpty() || animalType.isEmpty() || animalDescription.isEmpty()) {
                Toast.makeText(AddAnimalActivity.this, "Enter name + type + description", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("animalName", animalName);
            resultIntent.putExtra("animalType", animalType);
            resultIntent.putExtra("animalDescription", animalDescription);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish(); // סוגר את ה-Activity ומחזיר ל-MainActivityAnimalRV
            }
        });

    }

}