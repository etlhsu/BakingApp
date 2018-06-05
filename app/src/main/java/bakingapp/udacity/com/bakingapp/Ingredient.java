package bakingapp.udacity.com.bakingapp;

/**
* A class to describe an object that allows easy access to ingredient data
*/
public class Ingredient {
    private Integer quantity;
    private Unit unit;
    private String name;

    public Ingredient(Integer quantity, Unit unit, String name) {

        this.quantity = quantity;
        this.unit = unit;
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
    * A set of names to easily declare the units of ingredients
    */
    enum Unit {
        CUPS, TSP, TBLSP, K, G, OZ, UNIT,
    }
}
