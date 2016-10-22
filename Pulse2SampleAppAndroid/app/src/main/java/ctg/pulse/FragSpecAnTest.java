package ctg.pulse;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.harman.pulsesdk.ImplementPulseHandler;
import com.harman.pulsesdk.PulseHandlerInterface;

import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseHandlerInterface;

import Hexagon.HexagonButtonLayout;
import Hexagon.HexagonItem;

/**
 * Created by leemartinc on 10/22/16.
 */

public class FragSpecAnTest extends android.support.v4.app.Fragment  {

    byte b = 127;
    ImplementPulseHandler color = new ImplementPulseHandler();

    PulseDemo pulseDemo;
    private boolean mBroadcast = false;
    private TextView mColorText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pulseDemo = (PulseDemo)getActivity();

        View view = inflater.inflate(R.layout.frag_color,null);
        LinearLayout layout = (LinearLayout)view.findViewById(R.id.main_layout);

        HexagonButtonLayout HexagonButtonLayout = new HexagonButtonLayout(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        HexagonButtonLayout.setLayoutParams(lp);

        HexagonButtonLayout.setNavigationBarHeight(pulseDemo.navigationBarHeight);

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor01, 0, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor32, 3, 2));


       /*pulseDemo.pulseHandler.SetCharacterPattern(myChar.getText().charAt(0),
                new PulseColor((byte)255, (byte)0, (byte)0),
                new PulseColor((byte)0, (byte)0, (byte)255),
                false);*/


        //int width, height;

       // pixels[1][1] = color.GetMicrophoneSoundLevel();



        HexagonButtonLayout.setOnItemClickListener(new HexagonButtonLayout.OnItemClickListener() {



            @Override
            public void onItemClick(HexagonItem item) {
                Log.i("hello", "setOnItemClickListener");
                int color = getActivity().getResources().getColor(item.getPaint());
                color = color&0xffffff;
                String str = String.format("#%06x", color);
                mColorText.setText(str);


                PulseColor[] pixes = new PulseColor[99];
                for (int i = 0; i < 99; i++) {
                    //set image by yourself
                    pixes[i] = new PulseColor((byte)0, (byte)0, (byte) 0);
                }
                pixes[11] = new PulseColor((byte)0, (byte)0, (byte) 255);
                pulseDemo.pulseHandler.SetColorImage(pixes);





//                PulseColor pulseColor = new PulseColor();
//                pulseColor.red = (byte)(0);
//                pulseColor.green = (byte)(250);
//                pulseColor.blue = (byte)(250);
//                pulseDemo.pulseHandler.SetBackgroundColor(pulseColor, mBroadcast);
//
//                final PulseColor[] pixels = new PulseColor[2];
//                pixels[0] = new PulseColor((byte)0, (byte) 255, (byte)0);
//                pulseDemo.pulseHandler.SetColorImage(pixels);



            }
        });



        layout.addView(HexagonButtonLayout);


        Switch switchTest = (Switch)view.findViewById(R.id.switchBroadcast);
        switchTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mBroadcast = isChecked;
            }
        });

        mColorText = (TextView)view.findViewById(R.id.textColor);


        return view;
    }


    public void dance(){



    }

}
