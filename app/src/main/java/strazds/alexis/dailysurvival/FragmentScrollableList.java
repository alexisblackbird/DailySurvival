package strazds.alexis.dailysurvival;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class FragmentScrollableList extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "FragmentScrollableList";
    List<Task> list;
    TaskListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scrollable_list, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        ListView listView = view.findViewById(R.id.listview);

        TaskManager.instance.addNewDaily("Test1");
        TaskManager.instance.addNewDaily("Test2");

        list = populateTaskList();

        adapter = new TaskListAdapter(getActivity().getBaseContext(), list, this);
        listView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onRefresh(){
        updateListView();
    }

    public List populateTaskList (){
        list = new Vector<Task>(TaskManager.instance.dailyVector);
        // can eventually make this so it builds the list based on a set of options in the top: ie show dailies + weeklies etc
        return list;

    }



    void updateListView(){

        list = populateTaskList();
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }




}
