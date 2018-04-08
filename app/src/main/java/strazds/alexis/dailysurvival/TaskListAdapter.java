package strazds.alexis.dailysurvival;

import android.content.Context;
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

    private List<Daily> list;
    private Context context;

    public TaskListAdapter(Context context, List<Daily> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;


    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.task_checker_row, parent, false);


        //Set task's checkbox label & checked state
        CheckBox taskCheckBox= (CheckBox) view.findViewById(R.id.taskCheckBox);
        taskCheckBox.setText(list.get(position).taskName);
        taskCheckBox.setChecked(list.get(position).taskCompleted);

        taskCheckBox.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

    }
}
