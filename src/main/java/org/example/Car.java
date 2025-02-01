package org.example;

public abstract class Car {
    protected int year_of_release;
    protected String transmission_box;
    protected String name_of_model;
    protected String color;
    protected int horsepower;
    protected boolean isElectric;

    public Car(String name_of_model, int year_of_release, String transmission_box, String color, int horsepower, boolean isElectric) {
        this.name_of_model = name_of_model;
        this.year_of_release = year_of_release;
        this.transmission_box = transmission_box;
        this.color = color;
        this.horsepower = horsepower;
        this.isElectric = isElectric;
    }

    public void changeColor(String newColor) {
        this.color = newColor;
    }

    public String getInfo() {
        return String.format("Model: %s, Year: %d, Transmission: %s, Color: %s, Horsepower: %d, Electric: %b",
                name_of_model, year_of_release, transmission_box, color, horsepower, isElectric);
    }

    public boolean canBeConvertedToElectric() {
        return !isElectric && year_of_release > 2010;
    }
}
