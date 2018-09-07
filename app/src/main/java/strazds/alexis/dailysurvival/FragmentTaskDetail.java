package strazds.alexis.dailysurvival;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import strazds.alexis.dailysurvival.Data.Task;

public class FragmentTaskDetail extends Fragment implements View.OnClickListener{

    private final static String TAG = "FragmentTaskDetail";
    private TaskManager taskManager;
    private TextView taskNameTextView;
    private TextView taskDescriptionTextView;
    private Task task;
    public final static String TASK_KEY = "taskKey";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        Bundle bundle = this.getArguments();
        if(bundle != null){
            this.task = (Task) bundle.getParcelable(TASK_KEY);
        }

        taskManager = TaskManager.getInstance(this.getContext());
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);

        taskNameTextView = view.findViewById(R.id.taskNameText);
        taskDescriptionTextView = view.findViewById(R.id.taskDetailText);

        taskNameTextView.setText(task.getTaskName());

        // build description text, setting to nothing if description is null
        String description = "";
        if(task.getTaskDescription() != null){ description = task.getTaskDescription(); }
        String detailText = Task.taskTypeToString(task.getTaskType()) + ". " + description;
        taskDescriptionTextView.setText(detailText);

        ImageButton cancelButton = view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(this);

        Button deleteTaskButton = view.findViewById(R.id.buttonDeleteTask);
        deleteTaskButton.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonCancel:
                getFragmentManager().popBackStack();
                break;

            case R.id.buttonDeleteTask:
                //TODO needs a confirm prompt
                taskManager.deleteTask(task);
                //todo should do a toast... or maybe one with an undo button instead of a confirm prompt
                getFragmentManager().popBackStack();
                break;

        }
    }

}
