package ctg.pulse;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.harman.pulsesdk.BluetoothHelper;
import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.HexHelper;
import com.harman.pulsesdk.ImplementPulseHandler;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.PulseThemePattern;
import com.harman.pulsesdk.SppCmdHelper;
import com.harman.pulsesdk.SppConstant;
import com.harman.pulsesdk.WebColorHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)


import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.util.Log;
import com.harman.pulsesdk.BluetoothHelper;
import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.HexHelper;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.PulseThemePattern;
import com.harman.pulsesdk.SppCmdHelper;
import com.harman.pulsesdk.SppConstant;
import com.harman.pulsesdk.WebColorHelper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Created by leemartinc on 10/22/16.
 */





    public class IPH {
        private Activity mActivity;
        private PulseNotifiedListener pulseNotifiedListener = null;
        private Boolean bConnectMasterDevice = Boolean.valueOf(false);
        private Boolean bGetDeviceInfo = Boolean.valueOf(false);
        private BluetoothSocket mSocket = null;
        private InputStream is = null;
        private OutputStream os = null;
        private BluetoothDevice bluetoothDevice = null;
        private Lock lock = new ReentrantLock();
        private Condition conditionA;
        private Lock lockSetDev;
        private Condition condSetDev;
        private DeviceModel[] device;
        private PulseThemePattern LEDPattern;
        private int SetDevInfoACK;
        private PulseColor captureColor;

        public IPH() {

            this.conditionA = this.lock.newCondition();
            this.lockSetDev = new ReentrantLock();
            this.condSetDev = this.lockSetDev.newCondition();
            this.device = new DeviceModel[2];
            this.LEDPattern = PulseThemePattern.PulseTheme_Firework;
            this.SetDevInfoACK = 0;
            this.captureColor = new PulseColor();
            this.device[0] = new DeviceModel();
            this.device[1] = new DeviceModel();
        }

        public void registerPulseNotifiedListener(PulseNotifiedListener listener) {
            this.pulseNotifiedListener = listener;
        }

        public Boolean isConnectMasterDevice() {
            return this.bConnectMasterDevice;
        }




        public Boolean RequestDeviceInfo() {
            return !this.bConnectMasterDevice.booleanValue()?Boolean.valueOf(false):Boolean.valueOf(true);
        }

        public Boolean GetLEDPattern() {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                SppCmdHelper.reqLedPatternInfo();
                return Boolean.valueOf(true);
            }
        }

        public Boolean SetDeviceName(String devName, int devIndex) {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                SppCmdHelper.setDeviceName(devName, 0);
                this.device[devIndex].DeviceName = devName;
                return Boolean.valueOf(true);
            }
        }

        public Boolean SetDeviceChannel(int devIndex, int channel) {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                SppCmdHelper.setDeviceChannel(devIndex, channel);
                this.device[devIndex].ActiveChannel = channel;
                return Boolean.valueOf(true);
            }
        }

        public Boolean SetLEDPattern(PulseThemePattern pattern) {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                SppCmdHelper.setLedPattern(pattern.ordinal());
                this.LEDPattern = pattern;
                return Boolean.valueOf(true);
            }
        }

        public Boolean SetBrightness(int brightness) {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                SppCmdHelper.SetBrightness(brightness);
                return Boolean.valueOf(true);
            }
        }

        public Boolean SetBackgroundColor(PulseColor color, boolean inlcudeSlave) {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                int idx = WebColorHelper.RGBToWeb216Index(color);
                SppCmdHelper.SetBackgroundColor(idx, inlcudeSlave);
                return Boolean.valueOf(true);
            }
        }










        public Boolean SetColorImage(PulseColor[][] bitmap) {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                int[] idxPixel[] = new int[11][9];

                for(int width = 1; width <= 11; ++width) {
                    for(int height = 1; height <= 9; height++) {
                        idxPixel[width][height] = WebColorHelper.RGBToWeb216Index(bitmap[width][height]);
                    }
                }

                //SppCmdHelper.setColorImage(idxPixel);
                return Boolean.valueOf(true);
            }
        }















        public Boolean SetCharacterPattern(char character, PulseColor foreground, PulseColor background, boolean inlcudeSlave) {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                int foregroundColor = WebColorHelper.RGBToWeb216Index(foreground);
                int backgroundColor = WebColorHelper.RGBToWeb216Index(background);
                SppCmdHelper.SetCharacterPattern(character, foregroundColor, backgroundColor, inlcudeSlave);
                return Boolean.valueOf(true);
            }
        }

        public Boolean CaptureColorFromColorPicker() {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                SppCmdHelper.reqColorFromColorPicker();
                return Boolean.valueOf(true);
            }
        }

        public Boolean PropagateCurrentLedPattern() {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                SppCmdHelper.PropagateLedPattern();
                return Boolean.valueOf(true);
            }
        }

        public void GetMicrophoneSoundLevel() {
            if(this.bConnectMasterDevice.booleanValue()) {
                SppCmdHelper.GetMicrophoneSoundLevel();
            }
        }

        public void SetLEDAndSoundFeedback(int devIndex) {
            SppCmdHelper.reqLEDAndSoundFeedback(devIndex);
        }

        public Boolean GetBrightness() {
            if(!this.bConnectMasterDevice.booleanValue()) {
                return Boolean.valueOf(false);
            } else {
                SppCmdHelper.cmd_getBrightness();
                return Boolean.valueOf(true);
            }
        }

        private void processMessage(byte[] buffer) {
            String result = HexHelper.encodeHexStr(buffer);
            Log.i("my_msg", result);
            if(buffer[0] == SppConstant.RET_CMD_ACK[0] && buffer[1] == SppConstant.RET_CMD_ACK[1]) {
                if(buffer[3] == 21) {
                    this.SetDevInfoACK = buffer[4];
                } else if(buffer[3] == 83) {
                    boolean ret = buffer[4] == 0;
                    this.pulseNotifiedListener.onRetSetLEDPattern(ret);
                }
            } else if(buffer[0] == SppConstant.RET_CMD_DEV_INFO[0] && buffer[1] == SppConstant.RET_CMD_DEV_INFO[1]) {
                this.parseDevInfo(buffer);
            } else if(buffer[0] == SppConstant.RET_LED_PATTERN_CHANGE[0] && buffer[1] == SppConstant.RET_LED_PATTERN_CHANGE[1]) {
                if(buffer[2] <= PulseThemePattern.PulseTheme_Ripple.ordinal()) {
                    this.LEDPattern = PulseThemePattern.values()[buffer[2]];
                    this.pulseNotifiedListener.onLEDPatternChanged(this.LEDPattern);
                } else {
                    this.pulseNotifiedListener.onLEDPatternChanged((PulseThemePattern)null);
                }
            } else if(buffer[0] == SppConstant.RET_SOUND_EVENT[0] && buffer[1] == SppConstant.RET_SOUND_EVENT[1]) {
                this.pulseNotifiedListener.onSoundEvent(buffer[3]);
            } else if(buffer[0] == SppConstant.RET_LED_PATTERN[0] && buffer[1] == SppConstant.RET_LED_PATTERN[1]) {
                if(buffer[3] <= PulseThemePattern.PulseTheme_Ripple.ordinal()) {
                    this.LEDPattern = PulseThemePattern.values()[buffer[3]];
                    this.pulseNotifiedListener.onRetGetLEDPattern(this.LEDPattern);
                } else {
                    this.LEDPattern = PulseThemePattern.PulseTheme_Canvas;
                    this.pulseNotifiedListener.onLEDPatternChanged(this.LEDPattern);
                }
            } else if(buffer[0] == SppConstant.RET_COLOR_PICKER[0] && buffer[1] == SppConstant.RET_COLOR_PICKER[1]) {
                this.captureColor.red = buffer[3];
                this.captureColor.green = buffer[4];
                this.captureColor.blue = buffer[5];
                this.pulseNotifiedListener.onRetCaptureColor(this.captureColor);
                this.pulseNotifiedListener.onRetCaptureColor(buffer[3], buffer[4], buffer[5]);
            } else if(buffer[0] == SppConstant.RET_BRIGHTNESS[0] && buffer[1] == SppConstant.RET_BRIGHTNESS[1]) {
                this.pulseNotifiedListener.onRetBrightness(buffer[3]);
            }

        }

        private void parseDevInfo(byte[] buffer) {
            byte pos = 2;
            byte msgLen = buffer[pos];
            int var8 = pos + 1;
            byte devIndex = buffer[var8];
            this.device[devIndex].deviceIndex = devIndex;
            ++var8;
            if(buffer[var8] == -63) {
                ++var8;
                byte macbyte = buffer[var8];
                ++var8;
                char[] i = new char[macbyte];

                for(int i1 = 0; i1 < macbyte; ++i1) {
                    i[i1] = (char)buffer[var8 + i1];
                }

                var8 += macbyte;
                this.device[devIndex].DeviceName = String.copyValueOf(i);
            }

            if(var8 < msgLen) {
                byte[] var9;
                int var11;
                if(buffer[var8] == 66) {
                    ++var8;
                    var9 = new byte[2];

                    for(var11 = 0; var11 < 2; ++var11) {
                        var9[var11] = buffer[var8 + var11];
                    }

                    this.device[devIndex].product = this.getPID(var9);
                    var8 += 2;
                }

                if(var8 < msgLen) {
                    if(buffer[var8] == 67) {
                        ++var8;
                        this.device[devIndex].model = this.getMID(buffer[var8]);
                        ++var8;
                    }

                    if(var8 < msgLen) {
                        if(buffer[var8] == 68) {
                            ++var8;
                            this.device[devIndex].BatteryPower = buffer[var8];
                            ++var8;
                            int var10 = this.device[devIndex].BatteryPower >> 7;
                            if(var10 == 1) {
                                Log.i("hello", "charging: " + this.device[devIndex].BatteryPower);
                            } else {
                                Log.i("hello", "not charging: " + this.device[devIndex].BatteryPower);
                            }
                        }

                        if(var8 < msgLen) {
                            if(buffer[var8] == 69) {
                                ++var8;
                                this.device[devIndex].LinkedDeviceCount = buffer[var8];
                                ++var8;
                            }

                            if(var8 < msgLen) {
                                if(buffer[var8] == 70) {
                                    ++var8;
                                    this.device[devIndex].ActiveChannel = buffer[var8];
                                    ++var8;
                                }

                                if(var8 < msgLen) {
                                    if(buffer[var8] == 71) {
                                        ++var8;
                                        this.device[devIndex].AudioSource = buffer[var8];
                                        ++var8;
                                    }

                                    if(var8 < msgLen) {
                                        if(buffer[var8] == 72) {
                                            ++var8;
                                            var9 = new byte[6];

                                            for(var11 = 0; var11 < 6; ++var11) {
                                                var9[var11] = buffer[var8 + var11];
                                            }

                                            this.device[devIndex].Mac = HexHelper.encodeHexStr(var9);
                                        }

                                        Log.i("hello", "name : " + this.device[devIndex].DeviceName);
                                        Log.i("hello", "PID : " + this.device[devIndex].product);
                                        Log.i("hello", "MID : " + this.device[devIndex].model);
                                        Log.i("hello", "battery: " + this.device[devIndex].BatteryPower);
                                        Log.i("hello", "get linked device count: " + this.device[devIndex].LinkedDeviceCount);
                                        Log.i("hello", "get active channel : " + this.device[devIndex].ActiveChannel);
                                        Log.i("hello", "get audio source : " + this.device[devIndex].AudioSource);
                                        Log.i("hello", "mac : " + this.device[devIndex].Mac);
                                        this.bGetDeviceInfo = Boolean.valueOf(true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private void getDeviceInfo() {
            this.lock.lock();

            try {
                try {
                    SppCmdHelper.reqLedPatternInfo();
                    SppCmdHelper.reqDevInfo();
                    this.conditionA.awaitNanos(5000L);
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " getDeviceInfo");
            } finally {
                this.lock.unlock();
            }

        }

        private void retGetDeviceInfo() {
            this.lock.lock();

            try {
                System.out.println(Thread.currentThread().getName() + " retGetDeviceInfo");
                this.conditionA.signal();
            } finally {
                this.lock.unlock();
            }

        }

        private void setDeviceInfo() {
            this.lockSetDev.lock();

            try {
                try {
                    this.condSetDev.awaitNanos(5000L);
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " setDeviceInfo");
            } finally {
                this.lockSetDev.unlock();
            }

        }

        private void retSetDeviceInfo() {
            this.lockSetDev.lock();

            try {
                System.out.println(Thread.currentThread().getName() + " retSetDeviceInfo");
                this.condSetDev.signal();
            } finally {
                this.lockSetDev.unlock();
            }

        }

        private String getPID(byte[] pid) {
            return pid[0] == 0 && pid[1] == 38?"JBL Pulse 2":"";
        }

        private String getMID(byte mid) {
            return mid == 1?"black":(mid == 2?"white":"");
        }

        private class MyMessageThread extends Thread {
            private MyMessageThread() {
            }


        }
    }


