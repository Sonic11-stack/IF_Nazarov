package org.example;

class Volkswagen extends Car {
    public Volkswagen(String name_of_model, int year_of_release, String transmission_box, String color, int horsepower, boolean isElectric) {
        super(name_of_model, year_of_release, transmission_box, color, horsepower, isElectric);
    }
    @Override
    public String getFeature() {
        return "Volkswagen — немецкая автомобильная марка";
    }
}
