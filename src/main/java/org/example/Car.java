package org.example;

public abstract class Car {
    private final int year_of_release;
    private final int horsepower;
    private final String transmission_box;
    private final String name_of_model;
    private String color;
    private final boolean isElectric;

    public Car(String name_of_model, int year_of_release, String transmission_box, String color, int horsepower, boolean isElectric) {
        this.name_of_model = name_of_model;
        this.year_of_release = year_of_release;
        this.transmission_box = transmission_box;
        this.color = color;
        this.horsepower = horsepower;
        this.isElectric = isElectric;
    }

    public abstract String getFeature();

    public int getYear () {return year_of_release;}

    public String getName() {return name_of_model;}

    public String getTransmission_box() {return transmission_box;}

    public String getColor() {return color;}

    public void setColor(String newColor) {
        this.color = newColor;
    }

    public String getInfo() {return String.format("Model: %s, Year: %d, Transmission: %s, Color: %s, Horsepower: %d, Electric: %b", name_of_model, year_of_release, transmission_box, color, horsepower, isElectric);}
}
