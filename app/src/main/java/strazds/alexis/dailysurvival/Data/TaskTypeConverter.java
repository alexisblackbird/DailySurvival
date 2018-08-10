package strazds.alexis.dailysurvival.Data;

import android.arch.persistence.room.TypeConverter;

public class TaskTypeConverter {

    @TypeConverter
    public static Task.TaskType toType(int type){
        if(type == 1){
            return Task.TaskType.INCIDENTAL;
        } else if(type == 2){
            return Task.TaskType.DAILY;
        } else if (type == 3){
            return Task.TaskType.WEEKLY;
        } else if (type == 4){
            return Task.TaskType.MONTHLY;
        } else {
            throw new IllegalArgumentException("Invalid task type.");
        }
    }

    @TypeConverter
    public static int toInt(Task.TaskType type){
        if(type == Task.TaskType.INCIDENTAL){
            return 1;
        } else if(type == Task.TaskType.DAILY){
            return 2;
        } else if (type == Task.TaskType.WEEKLY){
            return 3;
        } else if (type == Task.TaskType.MONTHLY){
            return 4;
        } else {
            throw new IllegalArgumentException("Invalid task type.");
        }
    }



}
