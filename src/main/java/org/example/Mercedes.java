package org.example;

class Mercedes extends Car {
    public Mercedes(String name_of_model, int year_of_release, String transmission_box, String color, int horsepower, boolean isElectric) {
        super(name_of_model, year_of_release, transmission_box, color, horsepower, isElectric);
    }
    @Override
    public String getFeature() {
        return "Mercedes-Benz — немецкая торговая марка, производитель легковых автомобилей премиального класса, грузовых автомобилей";
    }
}
