package ctg.pulse;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import util.widget.HorizontalListView;

public class FragCamera extends Fragment {

    @Bind(R.id.pick_color_val)
    TextView pickColorVal;
    @Bind(R.id.colorful_circle)
    HorizontalListView colorfulCircle;
    @Bind(R.id.refresh_icon)
    ImageView refreshIcon;


    PulseDemo pulseDemo;
    HorizontalListViewAdapter adapterColor;
    static ArrayList<Integer> colorList = new ArrayList<Integer>();
    @Bind(R.id.pick_color_back)
    ImageView pickColorBack;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_camera, container, false);
        ButterKnife.bind(this, view);
        colorfulCircle.setAdapter(adapterColor);
        return view;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        pulseDemo = (PulseDemo) getActivity();
        //listItems = new ArrayList<HashMapProxy<String, Object>>();
        colorList.add(pulseDemo.getResources().getColor(R.color.red));
        colorList.add(pulseDemo.getResources().getColor(R.color.orange));
        colorList.add(pulseDemo.getResources().getColor(R.color.yellow));
        colorList.add(pulseDemo.getResources().getColor(R.color.green));
        colorList.add(pulseDemo.getResources().getColor(R.color.blue));
        colorList.add(pulseDemo.getResources().getColor(R.color.purple));

        adapterColor = new HorizontalListViewAdapter(pulseDemo, colorList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setPickColorVal(String value) {
        pickColorVal.setText(value);
        Drawable draw = pickColorBack.getBackground();
        draw.setColorFilter(Color.parseColor(value), PorterDuff.Mode.SRC);
        pickColorBack.setBackgroundDrawable(draw);

    }

    public class HorizontalListViewAdapter extends BaseAdapter {
        public Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<Integer> mList;

        public HorizontalListViewAdapter(Context context, ArrayList<Integer> list) {
            mContext = context;
            mList = list;
        }

        public void refreshGrpList(ArrayList<Integer> newList) {
            mList = newList;
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.frag_camera_itm, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            int color = (int) mList.get(position);

            Drawable draw = holder.colorItemIv.getBackground();
            draw.setColorFilter(color, PorterDuff.Mode.SRC);
            holder.colorItemIv.setBackground(draw);
            return convertView;
        }

        /**
         * This class contains all butterknife-injected Views & Layouts from layout file 'frag_camera_itm.xml'
         * for easy to all layout elements.
         *
         * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
         */
        class ViewHolder {
            @Bind(R.id.color_item_iv)
            ImageView colorItemIv;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}