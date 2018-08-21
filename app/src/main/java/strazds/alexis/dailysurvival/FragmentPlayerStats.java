package strazds.alexis.dailysurvival;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentPlayerStats extends Fragment {

    private PlayerData playerData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_player_stats, container, false);

        playerData = PlayerData.getInstance(this.getContext());

        TextView healthDisplay = view.findViewById(R.id.healthDisplay);
        String healthToDisplay = "Health: " + playerData.getPlayerHealth() + "/" + playerData.getMaxPlayerHealth();
        healthDisplay.setText(healthToDisplay);

        TextView squalorDisplay = view.findViewById(R.id.squalorDisplay);
        String squalorToDisplay = "Squalor:" + playerData.getSqualor();
        squalorDisplay.setText(squalorToDisplay);

        TextView experienceDisplay = view.findViewById(R.id.experienceDisplay);
        String experienceToDisplay = "Experience: " + playerData.getPlayerExperience();
        experienceDisplay.setText(experienceToDisplay);

        TextView momentumDisplay = view.findViewById(R.id.momentumDisplay);
        String momentumToDisplay = "Momentum: " + playerData.getMomentum();
        momentumDisplay.setText(momentumToDisplay);


        return view;
    }
}
