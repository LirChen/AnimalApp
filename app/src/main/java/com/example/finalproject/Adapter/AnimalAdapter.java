package com.example.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.models.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.myViewHolder>{
    private ArrayList<Animal> animalList;

    private ArrayList<Animal> filteredList;

    public AnimalAdapter(ArrayList<Animal> animalList) {
            this.animalList = animalList;
            this.filteredList= new ArrayList<>(animalList);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView animalName;
        TextView animalType;
        TextView animalDescription;
        ImageView imageViewAnimal;

        public myViewHolder ( View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            animalName = itemView.findViewById(R.id.animal_name_text1);
            animalType = itemView.findViewById(R.id.animal_type_text);
            animalDescription = itemView.findViewById(R.id.animal_description_text);
            imageViewAnimal = itemView.findViewById(R.id.imageView);

        }

    }

    @NonNull
    @Override
    public AnimalAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new myViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.myViewHolder holder, int position) {
        Animal animal = filteredList.get(position);
        holder.animalName.setText(animal.getName());
        holder.animalType.setText(animal.getType());
        holder.animalDescription.setText(animal.getDescription());
        holder.imageViewAnimal.setImageResource(animal.getImage());
        holder.animalName.setText("Name: " +animal.getName());
        holder.animalType.setText("Type: " + animal.getType());
        holder.animalDescription.setText("Description:" + animal.getDescription());
    }

    @Override
    public int getItemCount() {
        return animalList != null ? animalList.size() : 0;
    }

    public void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(animalList);
        } else {
            text = text.toLowerCase();
            for (Animal animal : animalList) {
                // סינון לפי שם או סוג
                if (animal.getName().toLowerCase().contains(text) ||
                        animal.getType().toLowerCase().contains(text)) {
                    filteredList.add(animal);
                }
            }
        }
        notifyDataSetChanged();
    }

}
