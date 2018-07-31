package strazds.alexis.dailysurvival.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Incidental.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "survival_database";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){

            synchronized (AppDatabase.class){
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DATABASE_NAME).build();
                }
            }
            return instance;
    }

    public abstract TaskDao taskDao();

}
