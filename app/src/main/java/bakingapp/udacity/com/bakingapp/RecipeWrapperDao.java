package bakingapp.udacity.com.bakingapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface RecipeWrapperDao {

        @Query("SELECT * FROM recipewrapper WHERE id LIKE :requestId LIMIT 1")
        RecipeWrapper query(Integer requestId);

        @Insert
        void insert(RecipeWrapper recipeWrapper);

        @Delete
        void delete(RecipeWrapper recipeWrapper);
}
