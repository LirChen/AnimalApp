package com.example.finalproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.activities.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FragmentAddAnimal extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_animal, container, false);

        Button button;

        TextView animalEditName, animalEditType, animalEditDescription, animalEditImageUrl;

        // Initialize the EditTexts (assuming you have these views in the layout)
        animalEditName = rootView.findViewById(R.id.AnimalName);
        animalEditType = rootView.findViewById(R.id.AnimalType);
        animalEditDescription = rootView.findViewById(R.id.AnimalDescription);
        animalEditImageUrl = rootView.findViewById(R.id.AnimalImageURL);

        button = rootView.findViewById(R.id.Add_animal_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = animalEditName.getText().toString();
                String type = animalEditType.getText().toString();//
                String description = animalEditDescription.getText().toString();
                String imageUrl = animalEditImageUrl.getText().toString();
                if (animalEditName.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Name Empty", Toast.LENGTH_LONG).show();
                } else if (animalEditType.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Type Empty", Toast.LENGTH_LONG).show();
                } else if (animalEditDescription.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Description Empty", Toast.LENGTH_LONG).show();
                } else if (animalEditImageUrl.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "imageURL Empty", Toast.LENGTH_LONG).show();
                } else if (!isValidName(name)) {
                    Toast.makeText(getContext(), "Name is invalid", Toast.LENGTH_LONG).show();
                } else if (!isValidType(type)) {
                    Toast.makeText(getContext(), "Type is invalid", Toast.LENGTH_LONG).show();
                } else {
                    if (isValidName(name) && isValidType(type)) {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.addAnimalData();
                        mainActivity.addAnimalRV();
                        //mainActivity.getUser(phone);
                        Toast.makeText(getActivity(), "Animal added successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "There was an error with the animal data.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Button button1 = rootView.findViewById(R.id.back_to_main);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(rootView).navigate(R.id.action_fragmentAddAnimal_to_mainActivityAnimalRV);
            }
        });
            return rootView;
    }
    private boolean isValidName(String name) {
        String nameRegex = "^[A-Za-z]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidType(String type) {
        String nameRegex = "^[A-Za-z]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(type);
        return matcher.matches();
    }

    /*private boolean isValidDescription(String description) {
        String priceRegex = "^\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(priceRegex);
        Matcher matcher = pattern.matcher(description);
        return matcher.matches();
    }*/

    /*private boolean isValidImageUrl(String imageUrl) {
        String inventoryRegex = "^\\d+$";
        Pattern pattern = Pattern.compile(inventoryRegex);
        Matcher matcher = pattern.matcher(imageUrl);
        return matcher.matches();
    }*/

}