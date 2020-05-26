package com.example.database;

public class Car {
   private int id;
   private String model ;
   private String color;
   private double distance;

    public Car( String model, String color, double distance) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.distance = distance;
    }
    public Car(int id, String model, String color, double distance) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.distance = distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public double getDistance() {
        return distance;
    }
}
