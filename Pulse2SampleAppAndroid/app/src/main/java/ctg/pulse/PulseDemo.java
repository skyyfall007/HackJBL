package ctg.pulse;


import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.ImplementPulseHandler;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.PulseThemePattern;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import toolbarslidingtab.SlidingTabLayout;
import util.adapter.MyFragPagerAdapter;
import util.proxy.HashMapProxy;
import util.widget.CustomViewPager;

public class PulseDemo extends AppCompatActivity implements PulseNotifiedListener {

    static String Tag = "PulseDemo";
    private ArrayList<Fragment> fragments;
    FragColorSplash fragColor;
    FragPattern fragPattern;
    FragMic fragMic;
    FragCamera fragCamera;
    FragChar fragChar;
    ArrayList<Map<String, Object>> adaptParam;
    CustomViewPager viewPager;
    SlidingTabLayout slidingTabLayout;
    MyFragPagerAdapter vpAdapter;
    Toolbar toolbar;
    static int mWidth, mHeight, statusBarHeight,realHeight, mDensityInt;
    static float scale, mDensity;
    boolean isActive;
    static int FRAG_COLOR_ID = 0, FRAG_PATTERN_ID = 1, FRAG_MIC_ID = 2, FRAG_CAMERA_ID = 3, FRAG_CHAR_ID = 4;
    Timer mTimer=null;
    boolean isConnectBT;
    int navigationBarHeight = 0;
    public ImplementPulseHandler pulseHandler = new ImplementPulseHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources rs = this.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar(this)) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }

        this.setContentView(R.layout.pulse);
        viewPager = (CustomViewPager) findViewById(R.id.pulse_vp);
        fragments = new ArrayList<Fragment>();
        fragments.add(fragColor = new FragColorSplash());
        fragments.add(fragPattern = new FragPattern());
        fragments.add(fragMic = new FragMic());
        fragments.add(fragCamera = new FragCamera());
        fragments.add(fragChar = new FragChar());
        adaptParam = new ArrayList<Map<String, Object>>();
        adaptParam.add(new HashMapProxy<String, Object>().putObject("title", "Color").putObject("image", (Object) R.mipmap.splash));
        adaptParam.add(new HashMapProxy<String, Object>().putObject("title", "Pattern").putObject("image", (Object) R.mipmap.broadcast));
        adaptParam.add(new HashMapProxy<String, Object>().putObject("title", "Mic").putObject("image", (Object) R.mipmap.mic));
        adaptParam.add(new HashMapProxy<String, Object>().putObject("title", "Picker").putObject("image", (Object) R.mipmap.picker));
        adaptParam.add(new HashMapProxy<String, Object>().putObject("title", "Character").putObject("image", (Object) R.mipmap.character));

        vpAdapter = new MyFragPagerAdapter(getSupportFragmentManager(), fragments, adaptParam);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(vpAdapter);
        viewPager.setScanScroll(false);
        LocationManager locationManager = (LocationManager)getSystemService(this.LOCATION_SERVICE);
        WindowManager wm = (WindowManager)getSystemService(this.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = wm.getDefaultDisplay().getHeight();
        statusBarHeight = getStatusBarHeight();
        realHeight = mHeight-statusBarHeight;
        scale = getResources().getDisplayMetrics().density/2;
        mDensity = getResources().getDisplayMetrics().density;
        mDensityInt = (int) mDensity;
        //toolbar
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        // 设置SlidingTab
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.pulse_toolbar);
        slidingTabLayout.setCustomTabView(R.layout.toolbaritem, R.id.toolbar_img, R.id.toolbar_txt, R.id.toolbar_under);
        slidingTabLayout.setViewPager(viewPager, mWidth);
        pulseHandler.ConnectMasterDevice(this);
        pulseHandler.registerPulseNotifiedListener(this);
        isActive = true;
        setTimer();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        isActive = false;
        System.exit(0);
    }
    public static int getStatusBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height",
                        "dimen", "android"));
    }

    private static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }

        return hasNavigationBar;
    }

    public int pxTodip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void pick_color(View v){
        pulseHandler.CaptureColorFromColorPicker();
    }

    public void record_sound(View v){
        pulseHandler.GetMicrophoneSoundLevel();
    }


    public void setTimer()
    {
        if(mTimer!=null)
            return;

        mTimer=new Timer();
        TimerTask task=new TimerTask()
        {
            @Override
            public void run() {
                PulseDemo.this.runOnUiThread(new Runnable() {
                    @Override
                    public synchronized void run() {
                        if (isActive) {
                            pulseHandler.ConnectMasterDevice(PulseDemo.this);
                        }
                    }
                });
            }
        };
        mTimer.schedule(task, 1000, 1500);
    }

    private void cancelTimer()
    {
        if(mTimer!=null)
        {
            mTimer.cancel();
            mTimer=null;
        }
    }

    @Override
    public void onConnectMasterDevice() {
        Log.i(Tag, "onConnectMasterDevice");
        isConnectBT = true;
        cancelTimer();
        Toast.makeText(this, "onConnectMasterDevice", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnectMasterDevice() {
        Log.i(Tag, "onDisconnectMasterDevice");
        isConnectBT = false;
        setTimer();
        Toast.makeText(this, "onDisconnectMasterDevice", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLEDPatternChanged(PulseThemePattern pattern) {
        //Toast.makeText(this, "onLEDPatternChanged:" + pattern.name(), Toast.LENGTH_SHORT);
        Log.i(Tag, "onLEDPatternChanged");

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    @Override
    public void onSoundEvent(final int soundLevel) {
        //Toast.makeText(this, "onSoundEvent: level=" + soundLevel, Toast.LENGTH_SHORT);
        Log.i(Tag, "soundLevel:"+soundLevel);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragMic.setSoundValue(soundLevel);
            }
        });
    }

    @Override
    public void onRetCaptureColor(final PulseColor capturedColor) {
//        Toast.makeText(this,
//                "onRetCaptureColor: red=" + capturedColor.red + " green=" + capturedColor.green + " blue=" + capturedColor.blue,
//                Toast.LENGTH_SHORT);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pulseHandler.SetBackgroundColor(capturedColor, false);
                fragCamera.setPickColorVal(String.format("#%02x%02x%02x", capturedColor.red, capturedColor.green, capturedColor.blue));
                Log.i(Tag, "red:" + (((int)capturedColor.red)&0xff) + " green:" + (((int)capturedColor.green)&0xff) + " blue:" + (((int)capturedColor.blue)&0xff));
            }
        });
    }

    @Override
    public void onRetCaptureColor(byte red, byte green, byte blue) {
        //Toast.makeText(this, "onRetCaptureColor1: red=" + red + " green=" + green + " blue=" + blue, Toast.LENGTH_SHORT);
    }

    @Override
    public void onRetSetDeviceInfo(boolean ret) {
        //Toast.makeText(this, "onRetSetDeviceInfo:"+ret, Toast.LENGTH_SHORT);
    }

    @Override
    public void onRetGetLEDPattern(PulseThemePattern pattern) {
        //Toast.makeText(this, "onRetGetLEDPattern:" + (pattern== null ? "null":pattern.name()), Toast.LENGTH_SHORT);
    }

    @Override
    public void onRetRequestDeviceInfo(DeviceModel[] deviceModel) {
        //Toast.makeText(this, "onRetRequestDeviceInfo:"+deviceModel.toString(), Toast.LENGTH_SHORT);
    }

    @Override
    public void onRetSetLEDPattern(boolean b) {
        Log.i(Tag, "onRetSetLEDPattern:"+b);
        if(b && fragPattern != null && fragPattern.isBroadcastSlave){
            pulseHandler.PropagateCurrentLedPattern();
        }
    }

    @Override
    public void onRetBrightness(int i) {

    }
}
