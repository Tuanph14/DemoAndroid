package com.example.custom_listview;

import android.widget.ImageView;

/**
 * Created by tuan on 05/07/2016.
 */
public class Country {

    String NameEn;
    String NameVi;
    int Image;


    public Country(String nameEn, String nameVi, int image) {
        NameEn = nameEn;
        NameVi = nameVi;
        Image = image;
    }

    public String getNameEn() {
        return NameEn;
    }

    public void setNameEn(String nameEn) {
        NameEn = nameEn;
    }

    public String getNameVi() {
        return NameVi;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setNameVi(String nameVi) {
        NameVi = nameVi;
    }
}
