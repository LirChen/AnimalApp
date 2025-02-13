package com.example.finalproject.models;

public class Animal {
    private String name;
    private String type;
    private String description;
    private int image;
    private boolean isSelected;


    public Animal() {

    }
    public Animal(String name, String type,String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.isSelected = false;
    }

    public Animal(String name, String type, String description, int image) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
    }
    public String getType() { return type; }
    public void setType(String type) {this.type=type; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }

}
