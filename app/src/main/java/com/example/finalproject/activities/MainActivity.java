package com.example.finalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.Navigation;

import com.example.finalproject.Fragments.FragmentZoo;
import com.example.finalproject.R;
import com.example.finalproject.models.Animal;
import com.example.finalproject.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
    }

    private FirebaseAuth mAuth;
    private EditText animalNameEditText, animalTypeEditText, animalDescriptionEditText, animalImageEdit;
    private Button addAnimalButton;
    private ActivityResultLauncher<Intent> addAnimalLauncher;

    public void login() {
        String email = ((EditText) findViewById(R.id.emailLogin)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString();
        Button button2login = findViewById(R.id.Login);


        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Email or password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login succeed", Toast.LENGTH_LONG).show();
                            String predefinedEmail = "test1@gmail.com";
                            if (email.equals(predefinedEmail)) {
                                Navigation.findNavController(findViewById(R.id.Login)).navigate(R.id.action_fragmentLogin_to_fragmentAddAnimal);
                            } else {
                                Navigation.findNavController(findViewById(R.id.Login)).navigate(R.id.action_fragmentAddAnimal_to_fragmentZoo);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void registration() {
        String email = ((EditText) findViewById(R.id.Email_address)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.re_password)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone_number)).getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registration succeed", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    void func (View view){

        Intent intent = new Intent(this, MainActivityAnimalRV.class);
        startActivity(intent);
    }

    public void addData() {

        String phone = ((EditText) findViewById(R.id.phone_number)).getText().toString();
        String email = ((EditText) findViewById(R.id.Email_address)).getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(phone);

        User s = new User(email, phone);

        myRef.setValue(s);
    }

    public void addAnimalData() {

        String name = ((EditText) findViewById(R.id.AnimalName)).getText().toString();
        String type = ((EditText) findViewById(R.id.AnimalType)).getText().toString();
        String description = ((EditText) findViewById(R.id.AnimalDescription)).getText().toString();
        String imageUrl = ((EditText) findViewById(R.id.AnimalImageURL)).getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("animals").child(name);

        Animal a = new Animal(name, type, description, imageUrl);

        myRef.setValue(a);
    }

    public void addAnimalRV() {

        setContentView(R.layout.fragment_add_animal);

        animalNameEditText = findViewById(R.id.AnimalName);
        animalTypeEditText = findViewById(R.id.AnimalType);
        animalDescriptionEditText = findViewById(R.id.AnimalDescription);
        addAnimalButton = findViewById(R.id.Add_animal_button);

        addAnimalButton.setOnClickListener(v -> {
            String animalName = animalNameEditText.getText().toString();
            String animalType = animalTypeEditText.getText().toString();
            String animalDescription = animalDescriptionEditText.getText().toString();

            if (animalName.isEmpty() || animalType.isEmpty() || animalDescription.isEmpty()) {
                Toast.makeText(MainActivity.this, "Enter name + type + description", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent addAnimalIntent = new Intent(MainActivity.this, MainActivityAnimalRV.class);
            addAnimalLauncher.launch(addAnimalIntent); //

            Intent resultIntent = new Intent();
            resultIntent.putExtra("animalName", animalName);
            resultIntent.putExtra("animalType", animalType);
            resultIntent.putExtra("animalDescription", animalDescription);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

}