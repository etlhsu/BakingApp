package bakingapp.udacity.com.bakingapp;

/**
 * A class to describe an object that holds recipe data in an easy-to-use way
 */
public class Recipe {
    private Integer id;
    private String name;
    private Integer servings;
    private String image;

    public Recipe() {
    }

    public Recipe(Integer id, String name, Integer servings, String image) {

        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
