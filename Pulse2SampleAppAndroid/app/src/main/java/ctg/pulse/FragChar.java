package ctg.pulse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.harman.pulsesdk.PulseColor;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import util.widget.MyGridView;

//import com.harman.pulsesdk.PulseColor;

public class FragChar extends Fragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.my_char)
    TextView myChar;
    @Bind(R.id.char_num)
    MyGridView charNum;
    @Bind(R.id.char_alphabeta)
    MyGridView charAlphabeta;
    @Bind(R.id.char_other)
    MyGridView charOther;

    PulseDemo pulseDemo;
    ArrayAdapter adapterNum;
    ArrayAdapter adapterAlpha;
    ArrayAdapter adapterOther;

    static String otherStr[] = {"?", "!", "$", "+", "-", "=", "%", "*", "/", "#"};
    static ArrayList<String> numList = new ArrayList<String>();
    static ArrayList<String> alphaList = new ArrayList<String>();
    static ArrayList<String> otherList = new ArrayList<String>();
    static {
        for(int i = 0; i < 10; i++) {
            numList.add(Integer.toString(i));
            otherList.add(otherStr[i]);
        }
        for(int j = 0; j < 26; j++) {
            alphaList.add(new String(new char[]{(char)(65+j)}));//'A' = 65
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_char, container, false);

        ButterKnife.bind(this, view);
        charNum.setAdapter(adapterNum);
        charAlphabeta.setAdapter(adapterAlpha);
        charOther.setAdapter(adapterOther);
        charNum.setOnItemClickListener(this);
        charAlphabeta.setOnItemClickListener(this);
        charOther.setOnItemClickListener(this);
        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(parent == charNum){
            myChar.setText(Long.toString(id));
        }
        else if(parent == charAlphabeta){
            myChar.setText(new String(new char[]{(char)(65+id)}));
        }
        else {//charOther
            myChar.setText(otherStr[(int) id]);
        }
        pulseDemo.pulseHandler.SetCharacterPattern(myChar.getText().charAt(0),
                new PulseColor((byte)255, (byte)0, (byte)0),
                new PulseColor((byte)0, (byte)0, (byte)255),
                false);
    }
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        pulseDemo = (PulseDemo) getActivity();
        //listItems = new ArrayList<HashMapProxy<String, Object>>();

        adapterNum = new ArrayAdapter(pulseDemo,
                R.layout.frag_char_itm,
                R.id.char_item_tv,
                numList);

        adapterAlpha = new ArrayAdapter(pulseDemo,
                R.layout.frag_char_itm,
                R.id.char_item_tv,
                alphaList);

        adapterOther = new ArrayAdapter(pulseDemo,
                R.layout.frag_char_itm,
                R.id.char_item_tv,
                otherList);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}