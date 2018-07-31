package strazds.alexis.dailysurvival;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import strazds.alexis.dailysurvival.Data.Incidental;


/**
 * Created by Gallus on 2018-01-22.
 */

public class FragmentScrollableList extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // still needs the overflow menu refresh option for accessibility reasons
    private static final String TAG = "FragmentScrollableList";
    private LiveData<List<Incidental>> list;
    private TaskListAdapter adapter;
    private RecyclerView taskListView;
    private SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scrollable_list, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);



        taskListView = view.findViewById(R.id.rv_scroll_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        taskListView.setLayoutManager(layoutManager);

        adapter = new TaskListAdapter();

        taskListView.setAdapter(adapter);

        setupViewModel();

        return view;

    }

    @Override
    public void onRefresh(){

        //Will rejig this to refresh based on settings for what tasks to see from a radial menu
        //With manual refresh so it's not reloading every time someone touches anything maybe
        //or not and then I can delete this

        Log.v(TAG, "Refreshing!");
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setupViewModel(){
        TaskListViewModel viewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);
        viewModel.getTaskList().observe(this, new Observer<List<Incidental>>() {
            @Override
            public void onChanged(@Nullable List<Incidental> taskList) {
                adapter.setTaskList(taskList);
                Log.d(TAG, "Receiving database update from LiveData.");
            }
        });

    }


}
