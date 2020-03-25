package com.example.babybook;

import java.util.ArrayList;

public class Croissance {

    private ArrayList<Double> yAxis = new ArrayList<Double>();
    private ArrayList<Double> xAxis = new ArrayList<Double>();

    public Croissance(ArrayList<Double> yAxis, ArrayList<Double> xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
    }

    public Croissance(){

    }

    public ArrayList<Double> getyAxis() {
        return yAxis;
    }

    public void setyAxis(ArrayList<Double> yAxis) {
        this.yAxis = yAxis;
    }

    public ArrayList<Double> getxAxis() {
        return xAxis;
    }

    public void setxAxis(ArrayList<Double> xAxis) {
        this.xAxis = xAxis;
    }

}
