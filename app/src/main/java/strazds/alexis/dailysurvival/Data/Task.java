package strazds.alexis.dailysurvival.Data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.Nullable;
import android.util.Log;

import strazds.alexis.dailysurvival.PlayerData;


/**
 * Created by Alexis on 2017-11-16.
 */

@Entity(tableName = "tasks")
public class Task {
/*
Class for tasks of all types. I had originally planned on using inheritance w/ subclasses for each type,
but getting Room to play nice with List<Task> where each object was instantiated as its actual type is
more effort than it's worth, and I decided that for now the benefits of architecture components outweigh
the benefits of polymorphism for task types. I can refactor it one day when Room's functionality improves
or when I know enough for it to be worth writing my own database implementation.
 */
// todo needs a toString()

    @Ignore
    private static final String TAG = "Task";



    @PrimaryKey(autoGenerate = true)
    protected int id;

    @ColumnInfo(name = "task_name")
    private String taskName;
    @ColumnInfo(name = "task_type")
    @TypeConverters(TaskTypeConverter.class)
    private TaskType taskType;
    @ColumnInfo(name = "task_description")
    private String taskDescription;
    @ColumnInfo(name = "task_completed")
    private boolean taskCompleted;


    public enum TaskType {INCIDENTAL, DAILY, WEEKLY, MONTHLY}

    @Ignore
    public Task(String name, TaskType type, @Nullable String description){

        taskName = name;
        taskType = type;
        taskDescription = description;
    }

    //Constructor for Room use only. Ignore annotated one is to be used in code.
    public Task(int id, String taskName, TaskType taskType, String taskDescription, boolean taskCompleted){
        this.id = id;
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskCompleted = taskCompleted;
    }

    public void taskReset(){
        this.taskCompleted = false;
    }


    public boolean completeTask(){
        if(taskCompleted){
            Log.w(TAG, "Task already complete.");
            return false;
        } else {
            this.taskCompleted = true;
            Log.v(TAG, this.taskName + "completed.");
            return true;
        }

    }

    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getTaskCompleted(){
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
        Log.w(TAG, "taskCompleted should not be set manually, this is for Room use only.");
    }

    @Nullable
    public String getTaskDescription(){
      return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        Log.w(TAG, "id should not be set manually, this is for Room use only.");
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }




}

