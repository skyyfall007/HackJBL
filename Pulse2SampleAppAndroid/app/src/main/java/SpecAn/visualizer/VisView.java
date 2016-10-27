package SpecAn.visualizer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.audiofx.Visualizer;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.harman.pulsesdk.PulseColor;

import ctg.pulse.PulseDemo;
import ctg.pulse.R;

public class VisView extends SurfaceView {
	PulseDemo pulseDemo;
    /**����������� ��������*/
    private Bitmap bmp;
    
    /**���� ���� ���������*/
    private SurfaceHolder holder;

    /**������ ������ GameManager*/
    private VisManager gameLoopThread;

    /** ���������� �������� �� �=0*/
    private int x = 0; 
   
    /**�������� ����������� = 1*/
    private int xSpeed = 1;

    private Visualizer viz;
    private byte[] mFft;
	private byte[] mWave;
	
	private double[] data = new double[64];
	
    public VisView(Context context) 
    {
          super(context);
          gameLoopThread = new VisManager(this);
          holder = getHolder();
          holder.addCallback(new SurfaceHolder.Callback() 
          {

                 /** ����������� ������� ��������� */
                 public void surfaceDestroyed(SurfaceHolder holder) 
                 {
                        boolean retry = true;
                        gameLoopThread.setRunning(false);
                        while (retry) {
                               try {
                                     gameLoopThread.join();
                                     retry = false;
                               } catch (InterruptedException e) {
                               }
                        }
                 }

                 /** �������� ������� ��������� */
                 public void surfaceCreated(SurfaceHolder holder) 
                 {
                        gameLoopThread.setRunning(true);
                        gameLoopThread.start();
                 }

                 /** ��������� ������� ��������� */
                 public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
                 {
                 }
          });

          bmp = BitmapFactory.decodeResource(getResources(), 0);
          
          viz = new Visualizer(0);
  		  viz.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

  		  viz.setDataCaptureListener(new OnDataCaptureListener() {

	  			@Override
	  			public void onFftDataCapture(Visualizer visualizer, byte[] fft,
	  					int samplingRate) {
	  				// FFT procesing here!

					PulseColor[] pixes = new PulseColor[99];
					for (int i = 0; i < 99; i++) {
						//set image by yourself
						pixes[i] = new PulseColor((byte)0, (byte)0, (byte) 0);
					}
	  				double[] data2 = new double[64];
	  				String str = "";
	  				for(int i = 0; i < fft.length; i = i + 16 ) {
	  					//if(fft[i] > 0)
	  					double sum = 0;
	  					for(int j = i;j < i + 16; j++ ) {
	  						sum +=fft[j];
	  					}
	  					sum = sum / 16;
	  					
	  					data2[i/16] = sum;
	  					str += data2[i/16] + " ";
	  				}
	  				Log.v("FFT", str);
	  				data = data2.clone();
	  				mFft = fft.clone();
	  				invalidate();
	  			}

  			@Override
  			public void onWaveFormDataCapture(Visualizer visualizer,
  					byte[] waveform, int samplingRate) {
  				// Wave procesing here
  				double[] data2 = new double[32];
  				String str = "";
  				for(int i = 0; i < waveform.length; i = i + 32 ) {
  					//if(fft[i] > 0)
  					double sum = 0;
  					for(int j = i;j < i + 32; j++ ) {
  						sum +=waveform[j];
  					}
  					sum = sum / 32;
  					
  					data2[i/32] = sum;
  					str += data2[i/32] + " ";
  				}
  				Log.v("FFT", str);
  			//	data = data2.clone();
  				mWave = waveform.clone();
  				//Log.v("WAVE", str);
  				mWave = waveform.clone();
  			}

  		}, Visualizer.getMaxCaptureRate(), true, true);
    }

    @Override
    protected void onDraw(Canvas canvas) 
    {
    	canvas.drawColor(Color.BLACK);
       //   canvas.drawBitmap(bmp, x , 10, null);
        
    	Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		
		paint.setColor(Color.RED);
		paint.setStrokeWidth(5);
		
    	float x = 25;
		float y = 600;
		float[] pts = new float[data.length];
		for(int i = 0; i < data.length; i = i + 2) {

			PulseColor[] pixes = new PulseColor[99];
			pixes[i] = new PulseColor((byte)0, (byte)255, (byte) 0);

			pulseDemo.pulseHandler.SetColorImage(pixes);
			pts[i] = x;
			pts[i+1] = (float) (y + data[i/2] * 100);
			
			canvas.drawLine(x, y, x, (float) (y + data[i/2] * 100), paint);
			x += 25;
		}
		paint.setColor(Color.YELLOW);
		paint.setStrokeWidth(5);
		canvas.drawPoints(pts, paint);
		
		//canvas.drawLines(pts, paint);
		
		paint.setColor(Color.GRAY);
		paint.setStrokeWidth(2);
		canvas.drawLine(0, y, getWidth(), y, paint);
    	
          
    }

    public void starViz() {
		viz.setEnabled(true);
	}
	
	public void stopViz() {
		viz.setEnabled(false);
	}
}