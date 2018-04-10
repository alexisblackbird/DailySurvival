package strazds.alexis.dailysurvival;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;



import java.util.List;


/**
 * Created by straz on 2018-04-04.
 */

public class TaskListAdapter extends ArrayAdapter implements CompoundButton.OnCheckedChangeListener {
    private ViewHolder holder;
    public List<Task> list;
    private Context context;
    private FragmentScrollableList fragment;

    private static final String TAG = "TaskListAdapter";

    public TaskListAdapter(Context context, List<Task> list, FragmentScrollableList fragment) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    static class ViewHolder {
        public CheckBox taskCheckBox;
        public Task task;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.task_checker_row, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.taskCheckBox = (CheckBox) view.findViewById(R.id.taskCheckBox);
            view.setTag(viewHolder);
        }



        ViewHolder holder = (ViewHolder) view.getTag();
        holder.task = list.get(position);
        //Set task's checkbox label & checked state
        holder.taskCheckBox.setText(list.get(position).taskName);
        holder.taskCheckBox.setChecked(list.get(position).taskCompleted);
        holder.taskCheckBox.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

        View row = (View) buttonView.getParent();
        ViewHolder holder = (ViewHolder) row.getTag();

        if (isChecked){
            holder.task.completeTask();
        } else {
            // will probably need this for undo logic
        }

        Log.i(TAG, holder.task.taskName + "checked");

       Log.i(TAG, "Task checked" );
       if (list != null){
           Log.i(TAG, "List-ho!");
       }

       if (holder != null){
           Log.i(TAG, "Holder-ho!");
       }
    }
}
