package com.example.babybook;

public class Baby extends Poids {

    private String name_baby;
    private String lastName_baby;
    private Poids poids;
    private Croissance croissance;

    // Constructeur
    public Baby(String nom, String lastName, Poids poids) {
        this.name_baby=nom;
        this.lastName_baby = lastName;
        this.poids = poids;
    }

    public Baby(){

    }

    public Croissance getCroissance() {
        return croissance;
    }

    public void setCroissance(Croissance croissance) {
        this.croissance = croissance;
    }

    public Baby(Poids poids){
        this.poids = poids;
    }

    public Poids getPoids() {
        return poids;
    }

    public void setPoids(Poids poids) {
        this.poids = poids;
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

}
