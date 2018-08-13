package strazds.alexis.dailysurvival;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by Alexis on 2017-11-15.
 */

public class PlayerData {

    // might be more efficient to store the references in local memory and only get from sharedpreferences when updated
    // but I would have to look in to that and this is definitely cleaner for now

    // might one day want to separate out defaults into something that initializes a new game to make it more editable
    // but for now this is cleaner, and works perfectly well for a smooth experience if no one is going to be resetting

    private static PlayerData instance;
    private static SharedPreferences savedPlayerData;

    private static final String momentumKey = "MOMENTUM";
    private static final String playerHealthKey = "PLAYER_HEALTH";
    private static final String playerExperienceKey = "PLAYER_EXPERIENCE";
    private static final String squalorKey = "SQUALOR";


    public static PlayerData getInstance(@NonNull Context context) {
        synchronized (PlayerData.class){
            if (instance == null){
                instance = new PlayerData(context);
            }
        }
        return instance;
    }


    private PlayerData(Context context) {

        savedPlayerData = context.getSharedPreferences(context.getString(R.string.playerData_pref_key), Context.MODE_PRIVATE);


    }

    public int getMomentum() {
        return savedPlayerData.getInt(momentumKey, 0);
    }

    public void incrementMomentum(){
        int current = getMomentum();
        SharedPreferences.Editor editor = savedPlayerData.edit();
        editor.putInt(momentumKey, current + 1);
        editor.apply();
    }

    public void decrementMomentum(){
        int current = getMomentum();
        SharedPreferences.Editor editor = savedPlayerData.edit();
        editor.putInt(momentumKey, current - 1);
        editor.apply();
    }

    public int getPlayerHealth(){
        return savedPlayerData.getInt(playerHealthKey, 5);
    }

    public void addPlayerHealth(int change){
        int current = getPlayerHealth();
        SharedPreferences.Editor editor = savedPlayerData.edit();
        editor.putInt(playerHealthKey, current + change);
        editor.apply();
    }

    public void subtractPlayerHealth(int change){
        int current = getPlayerHealth();
        SharedPreferences.Editor editor = savedPlayerData.edit();
        editor.putInt(playerHealthKey, current - change);
        editor.apply();
    }

    public int getPlayerExperience(){
        return savedPlayerData.getInt(playerExperienceKey, 0);
    }

    public void addPlayerExperience(int change){
        int current = getPlayerExperience();
        SharedPreferences.Editor editor = savedPlayerData.edit();
        editor.putInt(playerExperienceKey, current + change);
        editor.apply();
    }

    public void subtractPlayerExperience(int change){
        int current = getPlayerExperience();
        SharedPreferences.Editor editor = savedPlayerData.edit();
        editor.putInt(playerExperienceKey, current - change);
        editor.apply();
    }

    public int getSqualor(){
        return savedPlayerData.getInt(squalorKey, 0);
    }

    public void incrementSqualor(){
        int current = getSqualor();
        SharedPreferences.Editor editor = savedPlayerData.edit();
        editor.putInt(squalorKey, current + 1);
        editor.apply();
    }

    public void decrementSqualor(){
        int current = getSqualor();
        SharedPreferences.Editor editor = savedPlayerData.edit();
        editor.putInt(squalorKey, current - 1);
        editor.apply();
    }


}
