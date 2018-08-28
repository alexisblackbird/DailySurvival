package strazds.alexis.dailysurvival;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import strazds.alexis.dailysurvival.Data.Task;

public class FragmentAddTask extends Fragment implements View.OnClickListener {

    private TaskManager taskManager;
    private final String TAG = "FragmentAddTask";
    private EditText editTextTaskName;
    private EditText editTextTaskDescription;
    private Spinner taskTypeSpinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState){


        taskManager = TaskManager.getInstance(this.getContext());
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        editTextTaskName = view.findViewById(R.id.editTextTaskName);
        editTextTaskDescription = view.findViewById(R.id.editTextTaskDescription);
        taskTypeSpinner = view.findViewById(R.id.addTaskTypeSpinner);

        ImageButton finishAddTaskButton = view.findViewById(R.id.buttonFinishAddTask);
        finishAddTaskButton.setOnClickListener(this);

        ImageButton cancelAddTaskButton = view.findViewById(R.id.buttonCancelAddTask);
        cancelAddTaskButton.setOnClickListener(this);



        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCancelAddTask:
                cancelAddTask();
                break;

            case R.id.buttonFinishAddTask:
                finishAddTask();
                break;
        }
    }

    public void cancelAddTask(){
        Log.d(TAG, "cancelAddTask() called");
        getFragmentManager().popBackStack();
    }





    public void finishAddTask(){
        Log.d(TAG, "finishAddTask() called");

        String taskName = editTextTaskName.getText().toString();
        Task.TaskType taskType = Task.stringToTaskType(taskTypeSpinner.getSelectedItem().toString());

        if(taskName.length() < 3){
            Toast.makeText(
                    this.getContext(),
                    "Please use a longer name.",
                    Toast.LENGTH_LONG).show();

        } else {
            taskManager.addNewTask(
                    taskName,
                    taskType,
                    editTextTaskDescription.getText().toString());

            getFragmentManager().popBackStack();
        }

    }

}
