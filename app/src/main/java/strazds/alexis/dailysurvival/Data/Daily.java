package strazds.alexis.dailysurvival.Data;

import strazds.alexis.dailysurvival.Data.Task;

/**
 * Created by Alexis on 2017-11-23.
 */

class Daily extends Task {

    // todo needs a toString()
// todo needs to do the encapsulation

    private static final String TAG = "Daily";
    private static int dailyCount = 0;

    public Daily (String name){
        taskName = name;
        dailyCount++;
        //Log.i(TAG, "Daily " + taskName + " made. Daily count: " + dailyCount + ", done?: " + taskCompleted);

    }


}
