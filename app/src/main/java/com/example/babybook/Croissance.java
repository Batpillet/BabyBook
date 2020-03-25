package com.example.babybook;

public class Croissance {


    private double taille;
    private int temps;
    private int cle;

    public Croissance(){}

    public Croissance (double taille, int temps) {
        this.taille = taille;
        this.temps = temps;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public int getCle() {
        return cle;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }

}
