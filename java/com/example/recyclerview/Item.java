package com.example.recyclerview;

public class Item
{
    //TREBUIE SA AVEM ACELEASI CAMPURI CA CELE DIN LAYOUT-UL CARE DEFINESTE ITEM-UL
    int image;
    String description;
    String name;

    public Item(int image,String name,String description) {
        this.image = image;
        this.description = description;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
