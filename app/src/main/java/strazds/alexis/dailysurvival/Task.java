package strazds.alexis.dailysurvival;


import android.util.Log;




/**
 * Created by Alexis on 2017-11-16.
 */

public abstract class Task {
// superclass for defining common features of tasks
    // eventually lol, for now just gonna try to get basics working

    private static final String TAG = "Task";
    public static int taskCount = 0;

    // fuck enums for now public enum taskPeriod {daily, monthly, weekly, repeatable, todo}
    public String taskName;

    public boolean taskCompleted;



    public Task(){

        taskCompleted = false;


    }

    public void taskReset(){
        this.taskCompleted = false;
    }


    public void completeTask(){
        this.taskCompleted = true;
        PlayerData.playerExperience++;
        // needs a switch for task type rewards, the period rewards will be at the sub level
        // but like enums are fucked???? so not sure what to do yet


    }





}

