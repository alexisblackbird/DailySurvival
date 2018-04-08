package strazds.alexis.dailysurvival;

import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
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


public class MainActivity extends AppCompatActivity {

private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
            FragmentScrollableList firstFragment = new FragmentScrollableList();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }




        // todo: need to redo the displayHealth to make it work with fragments
        //displayHealth();

        // old/Daily testTask = new Daily("testamongo");


    }

    // this chunk isn't in the how-to but is needed to get the overflow menu to show up!
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarthings, menu);
        return true;
    }

    // here we add our handling of the action bar buttons ... I should probably make a class that handles this so I don't need to copy/paste the whole thing to each activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_moarhealth:
               Log.i(TAG, "Action button pressed.");
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void moarHealth(View view){
        PlayerData.playerHealth++;
        displayHealth();
        Log.v(TAG, "MOAR");
        String tempName = "healthtimes" + PlayerData.playerHealth;
        TaskManager.instance.addNewDaily(tempName);
    }

// should eventually move these to a class that is for menu methods
    public void quickAddTask(View view){
        EditText editText = (EditText) findViewById(R.id.quickaddtask_text);
        String name = editText.getText().toString();
        TaskManager.instance.addNewDaily(name);
        String message = "Task created.";
        Snackbar.make(view, message,
                Snackbar.LENGTH_LONG)
                .show();


    }

    public void displayHealth(){
        String testString = "Health: " + PlayerData.playerHealth;
        TextView textView = findViewById(R.id.healthDisplay);
        textView.setText(testString);

    }




}
