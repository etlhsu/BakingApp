package bakingapp.udacity.com.bakingapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RecipeWrapper {

    @PrimaryKey
    Integer id;
    String ingredientData;
    String recipeName;

    public RecipeWrapper() {

    }

    public RecipeWrapper(Integer id, String ingredientData, String recipeName) {

        this.id = id;
        this.ingredientData = ingredientData;
        this.recipeName = recipeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngredientData() {
        return ingredientData;
    }

    public void setIngredientData(String ingredientData) {
        this.ingredientData = ingredientData;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
