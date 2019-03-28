package com.example.siddheshsave.parkeazy;

import android.widget.ImageView;

public class Mall{
    private String name;
    private String location;
    private int image;

    public Mall(String name, String location, int image) {
        this.name = name;
        this.location = location;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getImage() {
        return image;
    }
}
