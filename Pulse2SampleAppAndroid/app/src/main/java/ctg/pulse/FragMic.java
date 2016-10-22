package ctg.pulse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragMic extends Fragment {
    @Bind(R.id.mic_sound_rec)
    ImageView micSoundRec;
    @Bind(R.id.mic_sound_val)
    TextView micSoundVal;
    @Bind(R.id.mic_sound_play)
    ImageView micSoundPlay;

    PulseDemo pulseDemo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mic, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        pulseDemo = (PulseDemo) getActivity();
    }
    public void setSoundValue(int value){
        micSoundVal.setText("" + value);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}