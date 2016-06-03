package com.edu.t2f.database_sample;

/**
 * Created by tuan on 05/23/2016.
 */
public class Country {
    public final static String TABLE_NAME = "Country";
    public final static String COL_ID = "_id";
    public final static String COL_NAME_EN = "NameEn";
    public final static String COL_NAME_VI = "NameVi";
    public final static String COL_FLAG = "Flag";
    public final static String CREATE_TABLE = "create table if not exists " + TABLE_NAME +"("
            + COL_ID + " integer primary key, "
            + COL_NAME_EN + " text,"
            + COL_NAME_VI + " text,"
            + COL_FLAG + " text)";
    public final static String DROP_TABLE = "drop table if exists " + TABLE_NAME;


    int _id;
    String nameEn;
    String nameVi;
    String flag;

    public Country() {
    }

    public Country(int _id, String nameEn, String nameVi, String flag) {
        this._id = _id;
        this.nameEn = nameEn;
        this.nameVi = nameVi;
        this.flag = flag;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameVi() {
        return nameVi;
    }

    public void setNameVi(String nameVi) {
        this.nameVi = nameVi;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
