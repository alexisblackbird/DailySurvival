package strazds.alexis.dailysurvival;

import android.util.Log;

import java.util.Vector;

/**
 * Created by Alexis on 2017-11-23.
 */

class TaskManager {
    /* ~code that makes it a singleton, turns out one can easily make a variant of that that doesn't require static, but then is just class.instance.method() to call~
    private static final TaskManager ourInstance = new TaskManager();

    static TaskManager getInstance() {
        return ourInstance;
    }

    private TaskManager() {
    }
*/
    public static final TaskManager instance = new TaskManager();
    private TaskManager(){}

    private static final String TAG = "TaskManager";
    Vector<Daily> dailyVector = new Vector<Daily>(0);

    public void addNewDaily(String name){
        Daily newDaily = new Daily(name);

        dailyVector.addElement(newDaily);

        for (Daily daily: dailyVector) {
            Log.i(TAG, "Daily: " + daily.taskName);
        }

    }

    public void resetAllDailies(){
        for (Daily daily: dailyVector){
            daily.taskReset();
        }
    }

}
