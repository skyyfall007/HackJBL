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
import java.util.*;

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


import java.util.Random;

import Hexagon.HexagonButtonLayout;
import butterknife.Bind;
import util.widget.MyGridView;




public class FragSpecAn extends android.support.v4.app.Fragment {


    private boolean mBroadcast = false;

    @Bind(R.id.textView4)
    TextView text;
    @Bind(R.id.toggleButton)
    ToggleButton button;




    PulseHandlerInterface pulseHander = new ImplementPulseHandler();


    PulseDemo pulseDemo;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_specan, container, false);



        button = (ToggleButton)view.findViewById(R.id.toggleButton);






       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               Random r = new Random();
               int ranred = r.nextInt(225) + 1;

               Random b = new Random();
               int ranblue = b.nextInt(225) + 1;

               Random g = new Random();
               int rangreen = g.nextInt(225) + 1;

               PulseColor pulseColor = new PulseColor();

               while(button.isChecked()){
                   pulseColor.red = (byte)(ranred);
                   pulseColor.green = (byte)(rangreen);
                   pulseColor.blue = (byte)(ranblue);

                   try{
                       Thread.sleep(1500);
                   }catch(InterruptedException e){
                       System.out.println("got interrupted!");
                   }
                   pulseDemo.pulseHandler.SetBackgroundColor(pulseColor, mBroadcast);

               }



           }
       });
        return view;
    }











}


