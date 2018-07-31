package strazds.alexis.dailysurvival;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import strazds.alexis.dailysurvival.Data.AppDatabase;
import strazds.alexis.dailysurvival.Data.Incidental;



/**
 * Created by Alexis on 2017-11-23.
 */

class TaskManager {

    private static TaskManager instance;
    private AppDatabase db;
    private static final String TAG = "TaskManager";

    /*
    Uses singleton pattern so it can be globally accessed as a helper/utility for getting/saving tasks.
    This is important to have a clean interface between the SQLite database and the rest of the code,
    so that it can work in Task/Daily/etc objects and any direct changes to the database can be limited.

    Further, the database helper needs a context and the singleton pattern is best for globally accessing
    the app context as needed, see: https://www.fwd.cloud/commit/post/android-context-on-demand/
     */

    static TaskManager getInstance(@NonNull Context context) {
        synchronized (TaskManager.class){
            if (instance == null){
                instance = new TaskManager(context);
            }
        }
        return instance;
    }

    private TaskManager(@NonNull Context context) {
        db = AppDatabase.getInstance(context);
    }





    public void addNewTask(String name){
        final Incidental task = new Incidental(name);
        Log.d(TAG, "Adding task: " + name);
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run(){
                db.taskDao().insertIncidental(task);
                Log.d(TAG, "Task: " + task.getTaskName() + " added to database");
            }
        });
    }

    public LiveData<List<Incidental>> getTaskList (){
        // eventually have filters and shit
        // I want to also pass out a list of Task that can later be casted as needed to support passing a variety of types
        // but I'm not sure how to do that unless the dao supports instantiating everything as Tasks that can later be casted
        LiveData<List<Incidental>> taskList = db.taskDao().loadAllIncidentals();
        return taskList;

    }

    public void completeTask (Incidental task){
        task.completeTask();

        final Incidental completedTask = task;

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.taskDao().updateIncidental(completedTask);
                Log.d(TAG, "Task: " + completedTask.getTaskName() + " updated in database");
            }
        });
    }

    public void addNewDaily(String name){
        // Dailies not yet implemented
    }

    public void resetAllDailies(){
        // Dailies not yet implemented
    }

}
