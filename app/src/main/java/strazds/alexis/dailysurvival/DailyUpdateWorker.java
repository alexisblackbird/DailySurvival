package strazds.alexis.dailysurvival;


import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;

public class DailyUpdateWorker extends Worker {

    private final static String TAG = "DailyUpdateWorker";
    public final static String WORK_TAG = "dailyUpdate";


    @Override
    public Worker.Result doWork(){

        TaskManager taskManager = TaskManager.getInstance(getApplicationContext());
        boolean weekReset = false;
        boolean monthReset = false;

        Date now = new Date();
        //use the user's system set time zone... Should take care of any oddities from people who move around
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
        calendar.setTime(now);

        if(calendar.get(Calendar.DAY_OF_WEEK) == 2){
            weekReset = true;
        }

        if(calendar.get(Calendar.DAY_OF_MONTH) == 1){
            monthReset = true;
        }

        taskManager.dailyResetTasks(weekReset, monthReset);

        Log.d(TAG, "Tasks should be reset");

        scheduleUpdate();

        //TaskManager can handle if things fail... for now I guess, this will get more complicated
        // as there's more things to update
        return Result.SUCCESS;
    }

    public static void scheduleUpdate(){
        Log.d(TAG, "Preparing to schedule next daily update");

        Date now = new Date();
        //use the user's system set time zone... Should take care of any oddities from people who move around
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
        calendar.setTime(now);

        int updateTime = 2; //hardcoding to 3:00 now, when I have settings it will use that

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, updateTime);
        Date targetReset = calendar.getTime();

        long millisecondsToUpdateTarget = targetReset.getTime() - now.getTime();

        OneTimeWorkRequest dailyUpdateWork = new OneTimeWorkRequest.Builder(DailyUpdateWorker.class)
                .setInitialDelay(millisecondsToUpdateTarget, TimeUnit.MILLISECONDS)
                .addTag(WORK_TAG)
                .build();

      WorkManager.getInstance().enqueue(dailyUpdateWork);

        Log.d(TAG, "Daily update scheduled");
    }

}
