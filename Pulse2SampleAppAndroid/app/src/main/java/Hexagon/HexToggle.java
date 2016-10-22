package Hexagon;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import ctg.MyApplication;
import ctg.pulse.PulseDemo;
import ctg.pulse.R;
import android.widget.TextView;

/**
 * Created by leemartinc on 10/21/16.
 */

public class HexToggle extends Activity {
    TextView text = (TextView) findViewById(R.id.textView4);
    ToggleButton toggle;
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_specan);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    if (toggle.isChecked()) {
                        text.setVisibility(View.INVISIBLE);
                    } else {
                        text.setVisibility(View.VISIBLE);
                    }
            }
        });
    }






    /*public void changeStatus(View v){

        boolean checked = ((ToggleButton)v).isChecked();
        if(checked)
        {

            text.setText("Toggle is on");

        }
        else{

            text.setText("Toggle is off");

        }
*/















}
