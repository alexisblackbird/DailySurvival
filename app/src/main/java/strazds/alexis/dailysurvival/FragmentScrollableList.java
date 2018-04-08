package strazds.alexis.dailysurvival;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.Vector;


/**
 * Created by Gallus on 2018-01-22.
 */

public class FragmentScrollableList extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scrollable_list, container, false);

        ListView listView = (ListView)view.findViewById(R.id.listview);

        TaskManager.instance.addNewDaily("Test1");
        TaskManager.instance.addNewDaily("Test2");

        List<Daily> list = new Vector<Daily>(TaskManager.instance.dailyVector);


        TaskListAdapter adapter = new TaskListAdapter(getActivity().getBaseContext(), list);
        listView.setAdapter(adapter);

        return view;

    }

}
