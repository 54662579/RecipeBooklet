package com.recipebook.recipebook.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Recipe.class},  version = 1, exportSchema = false)
public abstract class RecipeRoomDatabase extends RoomDatabase {

    //abstract method that returns the DAO class
    public abstract RecipeDAO recipeDAO();

    /*Make the RecipeRoomDatabase a singleton to prevent having multiple instances of the database
    opened at the same time.*/

    public static volatile RecipeRoomDatabase INSTANCE;

    static RecipeRoomDatabase getDatabase (final Context context){
        if (INSTANCE == null){
            synchronized (RecipeRoomDatabase.class){
                if (INSTANCE == null){
                    //Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeRoomDatabase.class, "recipe_Database").allowMainThreadQueries()
                            .addCallback(rRoomDatabaseCallBack).build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback rRoomDatabaseCallBack = new RoomDatabase.Callback(){
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            //new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RecipeDAO rDao;

        PopulateDbAsync(RecipeRoomDatabase db) {
            rDao = db.recipeDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            rDao.deleteAllRecipes();
            return null;
        }
    }
}
