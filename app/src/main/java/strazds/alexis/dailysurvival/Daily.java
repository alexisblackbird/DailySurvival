package strazds.alexis.dailysurvival;

import android.util.Log;

import strazds.alexis.dailysurvival.Task;

/**
 * Created by Alexis on 2017-11-23.
 */

public class Daily extends Task {

    private static final String TAG = "Daily";
    public static int dailyCount = 0;

    public Daily (String name){
        taskName = name;
        dailyCount++;
        //Log.i(TAG, "Daily " + taskName + " made. Daily count: " + dailyCount + ", done?: " + taskCompleted);

    }


}
