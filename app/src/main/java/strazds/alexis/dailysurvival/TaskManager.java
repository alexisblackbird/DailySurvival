package strazds.alexis.dailysurvival;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import strazds.alexis.dailysurvival.Data.AppDatabase;
import strazds.alexis.dailysurvival.Data.Task;



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





    public void addNewTask(String name, Task.TaskType type, String description){
        final Task task = new Task(name, type, description);
        Log.d(TAG, "Adding task: " + name);
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run(){
                db.taskDao().insertTask(task);
                Log.d(TAG, "Task: " + task.getTaskName() + " added to database");
            }
        });
    }

    public LiveData<List<Task>> getTaskList (){
        // eventually have filters and shit


        return db.taskDao().loadAllTasks();

    }

    public void completeTask (Task task){
        if (task.completeTask()){
            final Task completedTask = task;

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    db.taskDao().updateTask(completedTask);
                    Log.d(TAG, "Task: " + completedTask.getTaskName() + " updated in database");
                }
            });
        }


    }


    public void resetAllDailies(){
        // Dailies not yet implemented
    }

}
