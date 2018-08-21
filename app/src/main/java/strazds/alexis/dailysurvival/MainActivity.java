package strazds.alexis.dailysurvival;

import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;
import java.util.Vector;

import strazds.alexis.dailysurvival.Data.Task;


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

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



// should eventually move these to a class that is for menu methods
    public void quickAddTask(View view){
        EditText editText = (EditText) findViewById(R.id.quickaddtask_text);
        String name = editText.getText().toString();
        TaskManager.getInstance(this).addNewTask(name, Task.TaskType.INCIDENTAL, null);
        String message = "Quick Task created.";
        Snackbar.make(view, message,
                Snackbar.LENGTH_LONG)
                .show();


    }






}
