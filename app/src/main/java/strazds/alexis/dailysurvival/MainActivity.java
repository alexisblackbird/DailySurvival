package strazds.alexis.dailysurvival;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.util.List;

import androidx.work.WorkManager;
import androidx.work.WorkStatus;


public class MainActivity extends AppCompatActivity {

private static final String TAG = "MainActivity";
private DrawerLayout drawerLayout;
private FragmentScrollableList tasksFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        // copypasta from tutorial on setting up fragments
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            tasksFragment = new FragmentScrollableList();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            tasksFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, tasksFragment).commit();
        }

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        drawerLayout.closeDrawers();


                        switch (item.getItemId()){
                            case R.id.nav_tasks:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, tasksFragment).addToBackStack(null).commit();
                                break;

                            case R.id.nav_player_stats:
                                FragmentPlayerStats playerStatsFragment = new FragmentPlayerStats();

                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, playerStatsFragment).addToBackStack(null).commit();
                                break;
                        }


                        return true;
                    }
                }
        );

        //que up daily reset if it hasn't been and ensure it is only sheduled once
        // due to LiveData it needs to be temporarily wrapped in an observer for the logic to work
        final LiveData<List<WorkStatus>> quedWorkLD = WorkManager.getInstance().getStatusesByTag(DailyUpdateWorker.WORK_TAG);
        quedWorkLD.observe(this, new Observer<List<WorkStatus>>() {
            @Override
            public void onChanged(@Nullable List<WorkStatus> workStatuses) {
                Log.d(TAG, "Observer onChanged() called");
                if(workStatuses != null){
                    if(workStatuses.size() == 0){
                        Log.d(TAG, "No daily update scheduled, scheduling new daily update");
                        DailyUpdateWorker.scheduleUpdate();
                    } else if (workStatuses.size() > 1){
                        Log.d(TAG, "More than one daily update scheduled: clearing scheduled work and rescheduling.");
                        WorkManager.getInstance().cancelAllWorkByTag(DailyUpdateWorker.WORK_TAG);
                        DailyUpdateWorker.scheduleUpdate();
                    }

                } else {
                    Log.d(TAG, "Daily update already scheduled");
                }
                quedWorkLD.removeObserver(this);
            }
        });



    }

    // this chunk isn't in the how-to but is needed to get the overflow menu to show up!
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarthings, menu);
        return true;
    }

    // here we add our handling of the action bar buttons
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_moarhealth:
               Log.i(TAG, "Action button pressed.");

                return true;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_addTask:
                FragmentAddTask fragmentAddTask = new FragmentAddTask();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragmentAddTask).addToBackStack(null).commit();

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }










}
