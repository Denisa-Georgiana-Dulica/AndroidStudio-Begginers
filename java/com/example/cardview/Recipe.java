package com.example.cardview;

import android.net.Uri;

public class Recipe
{
    private String name;
    private int image;
    private Uri url;
    //alt + insert
    public Recipe(String name, int image, Uri url) {
        this.name = name;
        this.image = image;
        this.url=url;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }
}
