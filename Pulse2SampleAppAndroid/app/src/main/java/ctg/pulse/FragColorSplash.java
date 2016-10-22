package ctg.pulse;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.harman.pulsesdk.PulseColor;

import Hexagon.HexagonButtonLayout;
import Hexagon.HexagonItem;

public class FragColorSplash extends Fragment {

    PulseDemo pulseDemo;
    private boolean mBroadcast = false;
    private TextView mColorText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pulseDemo = (PulseDemo)getActivity();

        View view = inflater.inflate(R.layout.frag_color, null);
        LinearLayout layout = (LinearLayout)view.findViewById(R.id.main_layout);

        HexagonButtonLayout HexagonButtonLayout = new HexagonButtonLayout(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        HexagonButtonLayout.setLayoutParams(lp);

        HexagonButtonLayout.setNavigationBarHeight(pulseDemo.navigationBarHeight);

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor00, 0, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor01, 0, 1));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor02, 0, 2));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor03, 0, 3));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor04, 0, 4));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor05, 0, 5));

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor10, 1, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor11, 1, 1));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor12, 1, 2));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor13, 1, 3));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor14, 1, 4));

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor20, 2, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor21, 2, 1));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor22, 2, 2));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor23, 2, 3));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor24, 2, 4));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor25, 2, 5));

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor30, 3, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor31, 3, 1));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor32, 3, 2));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor33, 3, 3));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor34, 3, 4));

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor40, 4, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor41, 4, 1));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor42, 4, 2));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor43, 4, 3));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor44, 4, 4));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor45, 4, 5));

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor50, 5, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor51, 5, 1));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor52, 5, 2));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor53, 5, 3));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor54, 5, 4));

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor60, 6, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor61, 6, 1));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor62, 6, 2));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor63, 6, 3));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor64, 6, 4));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor65, 6, 5));

        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor70, 7, 0));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor71, 7, 1));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor72, 7, 2));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor73, 7, 3));
        HexagonButtonLayout.addItem(HexagonButtonLayout.makeItemByColor(R.color.pulseColor74, 7, 4));

        HexagonButtonLayout.setOnItemClickListener(new HexagonButtonLayout.OnItemClickListener() {

            @Override
            public void onItemClick(HexagonItem item) {
                Log.i("hello", "setOnItemClickListener");
                int color = getActivity().getResources().getColor(item.getPaint());
                color = color&0xffffff;
                String str = String.format("#%06x", color);
                mColorText.setText(str);

                PulseColor pulseColor = new PulseColor();
                pulseColor.red = (byte)(Color.red(color));
                pulseColor.green = (byte)(Color.green(color));
                pulseColor.blue = (byte)(Color.blue(color));
                pulseDemo.pulseHandler.SetBackgroundColor(pulseColor, mBroadcast);
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

}