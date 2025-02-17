package com.example.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.models.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.myViewHolder> {
    private ArrayList<Animal> animalList;
    private Context context;
    private ArrayList<Animal> filteredList;

    public AnimalAdapter(ArrayList<Animal> animalList, Context context) {
        this.animalList = animalList;
        this.context = context;
        this.filteredList = new ArrayList<>(animalList);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView animalName;
        TextView animalType;
        TextView animalDescription;
        ImageView imageViewAnimal;

        public myViewHolder(View itemView) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new myViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.myViewHolder holder, int position) {
        if (animalList != null && position < filteredList.size()) {
            Animal animal = filteredList.get(position);
            if (animal != null) {
                holder.animalName.setText(animal.getName());
                holder.animalType.setText(animal.getType());
                holder.animalDescription.setText(animal.getDescription());
                holder.imageViewAnimal.setImageResource(animal.getImage());

                // הוספת מאזין לחיצה שיציג את הדיאלוג
                holder.cardView.setOnClickListener(view -> {
                    showAnimalDetailsDialog(animal);

                    // אנימציה של הכרטיס
                    view.animate()
                            .scaleX(0.95f)
                            .scaleY(0.95f)
                            .setDuration(100)
                            .withEndAction(() -> {
                                view.animate()
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(100)
                                        .start();
                            })
                            .start();
                });
            }
        }
    }

    private void showAnimalDetailsDialog(Animal animal) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_animal_details);

        // הגדרת גודל הדיאלוג
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        // מציאת Views בדיאלוג
        ImageView imageView = dialog.findViewById(R.id.dialog_animal_image);
        TextView nameTextView = dialog.findViewById(R.id.dialog_animal_name);
        TextView typeTextView = dialog.findViewById(R.id.dialog_animal_type);
        TextView descriptionTextView = dialog.findViewById(R.id.dialog_animal_description);
        Button closeButton = dialog.findViewById(R.id.dialog_close_button);

        // הגדרת הנתונים
        imageView.setImageResource(animal.getImage());
        nameTextView.setText(animal.getName());
        typeTextView.setText(animal.getType());
        descriptionTextView.setText(animal.getDescription());

        // הגדרת כפתור הסגירה
        closeButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
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
            text = text.toLowerCase(Locale.ROOT);
            for (Animal animal : animalList) {
                String name = animal.getName().toLowerCase(Locale.ROOT);
                String type = animal.getType().toLowerCase(Locale.ROOT);

                if (name.contains(text) || type.contains(text)) {
                    filteredList.add(animal);
                }
            }
        }
        notifyDataSetChanged();
    }
}