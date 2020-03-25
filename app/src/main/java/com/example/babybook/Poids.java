package com.example.babybook;

import java.util.ArrayList;

public class Poids {
    private ArrayList<Double> yAxis = new ArrayList<Double>();
    private ArrayList<Double> xAxis = new ArrayList<Double>();

    public Poids(ArrayList yAxis, ArrayList xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
    }

    public Poids(){

    }

    public ArrayList getyAxis() {
        return yAxis;
    }

    public void setyAxis(ArrayList yAxis) {
        this.yAxis = yAxis;
    }

    public ArrayList getxAxis() {
        return xAxis;
    }

    public void setxAxis(ArrayList xAxis) {
        this.xAxis = xAxis;
    }
}
