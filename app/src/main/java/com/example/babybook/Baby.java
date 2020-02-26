package com.example.babybook;

import java.util.Date;

public class Baby {

    private int id_baby;
    private String name_baby;
    private String lastName_baby;
    private int date_baby;

    // Constructeur
    public Baby(int id, String nom, String lastName, int date) {
        this.id_baby=id;
        this.name_baby=nom;
        this.lastName_baby = lastName;
        this.date_baby = date;
    }

    public int getId_baby() {
        return id_baby;
    }

    public void setId_baby(int id) {
        this.id_baby = id;
    }

    public String getName_baby() {
        return name_baby;
    }

    public void setName_baby(String name) {
        this.name_baby = name;
    }

    public void setLastName_baby(String lastName){
        this.lastName_baby = lastName;
    }

    public String getLastName_baby(){
        return lastName_baby;
    }

    public void setDate_baby(int date){
        this.date_baby = date;
    }

    public int getDate_baby(){
        return date_baby;
    }

}
