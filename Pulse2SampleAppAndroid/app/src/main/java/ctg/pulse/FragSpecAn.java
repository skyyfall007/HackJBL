package ctg.pulse;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.harman.pulsesdk.PulseColor;

import com.harman.pulsesdk.PulseColor;

import Hexagon.HexagonButtonLayout;
import Hexagon.HexagonItem;
import Hexagon.HexToggle;
import butterknife.Bind;
import util.widget.MyGridView;

public class FragSpecAn extends android.support.v4.app.Fragment {
    @Bind(R.id.textView4)
    TextView text;
    @Bind(R.id.toggleButton)
    ToggleButton toggle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_specan, container, false);

    }





            public void onClick(View view) {
                //view =
            if (toggle.isChecked()) {
                text.setVisibility(View.VISIBLE);
            } else {
                text.setVisibility(View.INVISIBLE);
            }
        }
    }


