package strazds.alexis.dailysurvival.Data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;
import android.util.Log;

import strazds.alexis.dailysurvival.PlayerData;


/**
 * Created by Alexis on 2017-11-16.
 */

public abstract class Task {
// superclass for defining common features of tasks
// todo needs a toString() ... or maybe that should exist at the level of the specific types?

    @Ignore
    private static final String TAG = "Task";
    @ColumnInfo(name = "task_name")
    protected String taskName;
    @ColumnInfo(name = "task_description")
    protected String taskDescription;
    @PrimaryKey(autoGenerate = true)
    protected int id;
    @ColumnInfo(name = "task_completed")
    public boolean taskCompleted;


    public Task(){

        taskCompleted = false;


    }

    public void taskReset(){
        this.taskCompleted = false;
    }


    public void completeTask(){
        if(taskCompleted){
            Log.w(TAG, "Task already complete.");
        } else {
            this.taskCompleted = true;
            Log.v(TAG, this.taskName + "completed.");
        }




        // subclasses will override to provide type-specific rewards, but also call super.completeTask


    }

    public String getTaskName(){
        return taskName;
    }

    public boolean getTaskCompleted(){
        return taskCompleted;
    }

    @Nullable
    public String getTaskDescription(){
      return taskDescription;
    }




}

