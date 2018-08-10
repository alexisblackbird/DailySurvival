package strazds.alexis.dailysurvival;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import strazds.alexis.dailysurvival.Data.Task;

public class TaskListViewModel extends AndroidViewModel {


    private LiveData<List<Task>> taskList;

    public TaskListViewModel(@NonNull Application application) {
        super(application);
        taskList = TaskManager.getInstance(application.getApplicationContext()).getTaskList();
    }

    public LiveData<List<Task>> getTaskList() {
        return taskList;
    }
}
