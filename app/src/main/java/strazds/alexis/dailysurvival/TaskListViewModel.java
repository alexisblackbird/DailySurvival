package strazds.alexis.dailysurvival;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import strazds.alexis.dailysurvival.Data.Incidental;

public class TaskListViewModel extends AndroidViewModel {


    private LiveData<List<Incidental>> taskList;

    public TaskListViewModel(@NonNull Application application) {
        super(application);
        taskList = TaskManager.getInstance(application.getApplicationContext()).getTaskList();
    }

    public LiveData<List<Incidental>> getTaskList() {
        return taskList;
    }
}
