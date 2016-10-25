package ctg.pulse;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.GpsStatus;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
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

import java.util.Random;

import Hexagon.HexagonButtonLayout;
import Hexagon.HexagonItem;

import static android.graphics.Bitmap.createBitmap;
import static ctg.pulse.R.color.blue;

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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
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


                    Boolean test = true;


                    PulseColor[] pixes = new PulseColor[99];
                    for (int i = 0; i < 99; i++) {
                        //set image by yourself
                        pixes[i] = new PulseColor((byte)0, (byte)0, (byte) 0);
                    }








                    while( test == true){
                        Random r = new Random();
                        int ranred = r.nextInt(225) + 1;

                        Random b = new Random();
                        int ranblue = b.nextInt(225) + 1;

                        Random g = new Random();
                        int rangreen = g.nextInt(225) + 1;

                        PulseColor pulseColor = new PulseColor();
                    pulseColor.red = (byte)(ranred);
                    pulseColor.green = (byte)(rangreen);
                    pulseColor.blue = (byte)(ranblue);

                    try{
                        Thread.sleep(10);
                    }catch(InterruptedException e){
                        System.out.println("got interrupted!");
                    }
                    pulseDemo.pulseHandler.SetBackgroundColor(pulseColor, mBroadcast);

                }




                for (int i = 0; i < 99; i++) {
                    //set image by yourself
                    pixes[i] = new PulseColor((byte)0, (byte)0, (byte) 0);
                }
                Random rn = new Random();
                int num = rn.nextInt(98) + 1;

//                pixes[88] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[77] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[66] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[55] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[44] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[33] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[22] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[11] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[0] = new PulseColor((byte)0, (byte)0, (byte) 255);
//
//
//                pixes[91] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[80] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[69] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[58] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[47] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[36] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[25] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[14] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[3] = new PulseColor((byte)0, (byte)0, (byte) 255);
//
//
//                pixes[94] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[83] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[72] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[61] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[50] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[39] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[28] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[17] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[6] = new PulseColor((byte)0, (byte)0, (byte) 255);
//
//
//
//                pixes[97] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[86] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[75] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[64] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[53] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[42] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[31] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[20] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[10] = new PulseColor((byte)0, (byte)0, (byte) 255);
//
//                pulseDemo.pulseHandler.SetColorImage(pixes);
//
//                pixes[88] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[77] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[66] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[55] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[44] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[33] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[22] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[11] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[0] = new PulseColor((byte)0, (byte)0, (byte) 255);
//
//
//                pixes[91] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[80] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[69] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[58] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[47] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[36] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[25] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[14] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[3] = new PulseColor((byte)0, (byte)0, (byte) 255);
//
//
//                pixes[94] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[83] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[72] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[61] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[50] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[39] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[28] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[17] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[6] = new PulseColor((byte)0, (byte)0, (byte) 255);
//
//
//
//                pixes[97] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[86] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[75] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[64] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[53] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[42] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[31] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                pixes[20] = new PulseColor((byte)0, (byte)0, (byte) 255);
//                //pixes[10] = new PulseColor((byte)0, (byte)0, (byte) 255);
//
//                pulseDemo.pulseHandler.SetColorImage(pixes);


                createImage(11,9,blue);
            }

//      LED LAYOUT
//                        {0,   1,   2, 3,  4,  5,  6,  7,  8,  9, 10},
//                        {11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
//                        {22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32},
//                        {33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43},
//                        {44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54},
//                        {55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65},
//                        {66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76},
//                        {77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87},
//                        {88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98}



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


    public static Bitmap createImage(int width, int height, int color) {
        Bitmap bitmap = createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint);
        int[] pixels = new int[bitmap.getHeight()*bitmap.getWidth()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i=0; i<bitmap.getWidth()*5; i++)
            pixels[i] = Color.BLUE;
        bitmap.setPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return bitmap;
    }





}
