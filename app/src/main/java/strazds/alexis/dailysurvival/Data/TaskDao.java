package strazds.alexis.dailysurvival.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> loadAllTasks();

    @Insert
    void insertTask(Task task);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);
}
