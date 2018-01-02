package strazds.alexis.dailysurvival;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        displayHealth();

        // old/Daily testTask = new Daily("testamongo");


    }

    // this chunk isn't in the how-to but is needed to get the overflow menu to show up!
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarthings, menu);
        return true;
    }

    // here we add our handling of the buttons ... I should probably make a class that handles this so I don't need to copy/paste the whole thing to each activity
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

    public void displayHealth(){
        String testString = "Health: " + PlayerData.playerHealth;
        TextView textView = findViewById(R.id.healthDisplay);
        textView.setText(testString);

    }




}
