package strazds.alexis.dailysurvival;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
