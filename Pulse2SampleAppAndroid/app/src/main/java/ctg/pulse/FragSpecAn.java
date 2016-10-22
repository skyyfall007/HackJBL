package ctg.pulse;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;

import com.harman.pulsesdk.ImplementPulseHandler;
import com.harman.pulsesdk.PulseColor;

import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.SppCmdHelper;
import com.harman.pulsesdk.WebColorHelper;
import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseThemePattern;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.ImplementPulseHandler;



import Hexagon.HexagonButtonLayout;
import butterknife.Bind;
import util.widget.MyGridView;




public class FragSpecAn extends android.support.v4.app.Fragment {

    public Button button;

    @Bind(R.id.textView4)
    TextView text;
    @Bind(R.id.btn)



    PulseHandlerInterface pulseHander = new ImplementPulseHandler();


    PulseDemo pulseDemo;

    public ImplementPulseHandler lights = new ImplementPulseHandler();

    public PulseHandlerInterface phi;

    public static Pulse pulse = new Pulse();

    public static final int FACEBOOK_COLOR = 0x0011ff;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_specan, container, false);


        button = (Button)view.findViewById(R.id.btn);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.d("tag","hello");
//               switch (view.getId()) {
//                   case R.id.btn:
//                       PulseColor[] pixes = new PulseColor[99];
//                       for (int i = 0; i < 99; i++) {
//                           //set image by yourself
//                           pixes[i] = new PulseColor((byte)225, (byte)225, (byte)225);
//                       }
//                       pulseHander.SetColorImage(pixes);
//                       break;
//               }
               PulseColor[] pixes = new PulseColor[99];
               for (int i = 0; i < 99; i++) {
                   //set image by yourself
                   pixes[i] = new PulseColor((byte)225, (byte)0, (byte)0);
               }
               pulseDemo.pulseHandler.SetColorImage(pixes);

           }
       });
        return view;
    }











}


