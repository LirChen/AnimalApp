package com.example.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import java.util.Locale;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.myViewHolder>{
    private ArrayList<Animal> animalList;
    private Context context;
    private ArrayList<Animal> filteredList;

    public AnimalAdapter(ArrayList<Animal> animalList, Context context) {
            this.animalList = animalList;
            this.context = context;
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
        if (animalList != null && position < filteredList.size()) {
            Animal animal = filteredList.get(position);
            if (animal != null) {
                holder.animalName.setText(context.getString(R.string.animal_name_prefix) + " " + animal.getName());
                holder.animalType.setText(context.getString(R.string.animal_type_prefix) + " " + animal.getType());
                holder.animalDescription.setText(context.getString(R.string.animal_description_prefix) + " " + animal.getDescription());
                holder.imageViewAnimal.setImageResource(animal.getImage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(animalList);
        } else {
            // השתמש ב-Locale.ROOT כדי לטפל בהמרה לאותיות קטנות באופן עקבי
            text = text.toLowerCase(Locale.ROOT);
            for (Animal animal : animalList) {
                // המרה של שם וסוג החיה לאותיות קטנות עם אותו Locale
                String name = animal.getName().toLowerCase(Locale.ROOT);
                String type = animal.getType().toLowerCase(Locale.ROOT);

                // בדיקה אם המחרוזת מכילה את טקסט החיפוש
                if (name.contains(text) || type.contains(text)) {
                    filteredList.add(animal);
                }
            }
        }
        notifyDataSetChanged();
    }

}
