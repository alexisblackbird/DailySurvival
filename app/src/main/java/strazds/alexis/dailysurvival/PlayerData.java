package strazds.alexis.dailysurvival;

/**
 * Created by Alexis on 2017-11-15.
 */

public class PlayerData {
    private static final PlayerData ourInstance = new PlayerData();

    public static PlayerData getInstance() {
        return ourInstance;
    }

    private PlayerData() {
    }

    static int playerHealth = 5;
    static int playerExperience = 0;
    static int squalor = 0;




}
