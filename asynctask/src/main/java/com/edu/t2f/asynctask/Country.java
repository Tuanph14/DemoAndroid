package com.edu.t2f.asynctask;

/**
 * Created by tuan on 05/07/2016.
 */
public class Country {

    String NameEn;
    int Image;


    public Country(String nameEn, int image) {
        NameEn = nameEn;
        Image = image;
    }

    public Country() {
    }

    public String getNameEn() {
        return NameEn;
    }

    public void setNameEn(String nameEn) {
        NameEn = nameEn;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }


}
