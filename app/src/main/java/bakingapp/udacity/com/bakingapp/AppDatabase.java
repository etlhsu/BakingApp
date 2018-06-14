package bakingapp.udacity.com.bakingapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {RecipeWrapper.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeWrapperDao recipeWrapperDao();
}
