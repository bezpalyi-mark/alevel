package com.alevel.java.nix.annotations;

public class AppProperties {

    @PropertyKey("bakery.number.of.buns")
    public int numberOfBuns;

    @PropertyKey("bakery.cost.donut")
    public int donutCost;

    @PropertyKey("bakery.name")
    String bakeryName;

    @Override
    public String toString() {
        return String.format(
                "Number of buns = %d\n" +
                        "Donut cost = %d\n" +
                        "Bakery name = %s", numberOfBuns, donutCost, bakeryName);
    }

    public static void main(String[] args) throws IllegalAccessException {
        AppProperties app = new AppProperties();
        PropertyAnnotationHandler.initializeProperties(app, AppProperties.class, "bakery.properties");
        System.out.println(app);
    }
}
