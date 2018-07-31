package strazds.alexis.dailysurvival.Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.support.annotation.Nullable;

@Entity
public class Incidental extends Task {

    @Ignore
    public Incidental(String name){
        taskName = name;

    }

    @Ignore
    public Incidental(String name, String description){
        taskName = name;
        taskDescription = description;
    }

    Incidental(int id, boolean taskCompleted, String taskName, @Nullable String taskDescription){
        this.id = id;
        this.taskCompleted = taskCompleted;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

}
