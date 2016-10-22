package ctg.pulse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.harman.pulsesdk.PulseThemePattern;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import util.proxy.HashMapProxy;

public class FragPattern extends Fragment implements View.OnClickListener{
    @Bind(R.id.pattern_info_id)
    TextView patternInfoId;
    @Bind(R.id.tap_wave_id)
    ImageView tapWaveId;
    @Bind(R.id.tap_shake_id)
    ImageView tapShakeId;
    @Bind(R.id.tap_triangle_id)
    ImageView tapTriangleId;
    @Bind(R.id.broadcast_switch)
    Switch broadcastSwitch;

    @Bind(R.id.pattern_tap)
    GridView patternTap;

    SimpleAdapter adapter;
    PulseDemo pulseDemo;
    static ArrayList<HashMapProxy<String, Object>> listItems  = new ArrayList<HashMapProxy<String, Object>>();
    static int pattern_img_src[] = {R.mipmap.pat_fireworks,R.mipmap.pat_traffic, R.mipmap.pat_stars,
            R.mipmap.pat_waves, R.mipmap.pat_fireflies, R.mipmap.pat_rain,
            R.mipmap.pat_fire,R.mipmap.pat_canvas, R.mipmap.pat_hourglass,};
    static String pattern_text[] = { "FIREWORKS","TRAFFIC", "STARS", "WAVES",
            "FIREFLIES","RAIN","FIRE","CANVAS", "HOURGLASS"};
    static {
        for(int i = 0; i < 9; i++)
            listItems.add(new HashMapProxy<String, Object>().putObject("image", pattern_img_src[i]).putObject("text", pattern_text[i]));


    }
    public boolean isBroadcastSlave;
    //private ImplementPulseHandler pulseHander = new ImplementPulseHandler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_pattern, container, false);
        ButterKnife.bind(this, view);

        patternTap.setAdapter(adapter);
        patternTap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pulseDemo.pulseHandler.SetLEDPattern(PulseThemePattern.values()[position]);

            }
        });

        broadcastSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isBroadcastSlave = isChecked;
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        pulseDemo = (PulseDemo)getActivity();
        //listItems = new ArrayList<HashMapProxy<String, Object>>();

        adapter = new SimpleAdapter(pulseDemo,listItems,//
                R.layout.frag_pattern_item,
                new String[] {"image", "text"},
                new int[] {R.id.pattern_iv,R.id.content_tv}
        );
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:break;
        }
    }
}