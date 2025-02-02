package com.example.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.models.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ProductViewHolder>{
    private ArrayList<Animal> animalList;

    public AnimalAdapter(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ProductViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Animal animal = animalList.get(position);
        holder.nameTextView.setText(animal.getName());
        holder.typeTextView.setText(animal.getType());
        holder.descriptionTextView.setText(animal.getDescription());
        holder.imageUrlTextView.setText(animal.getImageUrl());
        holder.animalNameTextView.setText("Name: " +animal.getName());
        holder.animalTypeTextView.setText("Type: " + animal.getType());
        holder.animalDescriptionTextView.setText("Description:" + animal.getDescription());
        holder.animalCheckBox.setChecked(animal.isSelected());
        holder.animalCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> animal.setSelected(isChecked));

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, typeTextView, descriptionTextView, imageUrlTextView;
        TextView animalNameTextView, animalTypeTextView, animalDescriptionTextView;
        CheckBox animalCheckBox;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.AnimalName);
            typeTextView = itemView.findViewById(R.id.AnimalType);
            descriptionTextView = itemView.findViewById(R.id.AnimalDescription);
            imageUrlTextView = itemView.findViewById(R.id.AnimalImageURL);
            animalNameTextView = itemView.findViewById(R.id.animal_name_text1);
            animalTypeTextView = itemView.findViewById(R.id.animal_type_text);
            animalDescriptionTextView = itemView.findViewById(R.id.animal_description_text);
            animalCheckBox = itemView.findViewById(R.id.animal_checkbox);

        }
    }

}
