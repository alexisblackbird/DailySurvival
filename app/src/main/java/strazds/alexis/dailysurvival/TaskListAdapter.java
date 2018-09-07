package strazds.alexis.dailysurvival;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;


import strazds.alexis.dailysurvival.Data.Task;



public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private List<Task> list;
    private static final String TAG = "TaskListAdapter";
    private Activity activity;

    public TaskListAdapter(MainActivity activity){
        this.activity = activity;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_checker_row, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = list.get(position);

        holder.task = task;
        holder.taskNameButton.setText(task.getTaskName());
        holder.taskCheckBox.setChecked(task.getTaskCompleted());

    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        } else {
            return list.size();
        }

    }

    public void setTaskList (List<Task> taskList){
        list = taskList;
        notifyDataSetChanged();
        Log.d(TAG, "Task list updated.");

    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, CompoundButton.OnClickListener{

        CheckBox taskCheckBox;
        Button taskNameButton;
        Task task;

        public TaskViewHolder(View taskView){
            super(taskView);

            taskCheckBox = (CheckBox) taskView.findViewById(R.id.taskCheckBox);
            taskCheckBox.setOnCheckedChangeListener(this);
            taskNameButton = (Button) taskView.findViewById(R.id.taskNameButton);
            taskNameButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View buttonView){

            // When task name is selected, build and show a detail view fragment of it

            FragmentTaskDetail fragmentTaskDetail = new FragmentTaskDetail();
            Bundle bundle = new Bundle();
            bundle.putParcelable(FragmentTaskDetail.TASK_KEY, task);
            fragmentTaskDetail.setArguments(bundle);

            ((MainActivity)activity).swapFragment(fragmentTaskDetail);

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

            if (buttonView.isPressed()){ /* isPressed() needed to get if user checked
                                            otherwise will be called infinitely */
                if(buttonView.isChecked()){
                    Log.d(TAG, task.getTaskName() + " checked");
                    TaskManager.getInstance(buttonView.getContext()).completeTask(task);
                } else {
                    Log.d(TAG, task.getTaskName() + " unchecked");
                    TaskManager.getInstance(buttonView.getContext()).resetTask(task);
                }

            }

        }


    }



}
