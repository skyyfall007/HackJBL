package com.bazanski.myvisualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.audiofx.Visualizer;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.util.Log;
import android.view.View;

public class Draw2D extends View {
	
	private Visualizer viz;
	
	private byte[] mFft;
	private byte[] mWave;
	
	private double[] data = new double[8];
	
	public Draw2D(Context context) {
		super(context);
		
		viz = new Visualizer(0);
		viz.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

		viz.setDataCaptureListener(new OnDataCaptureListener() {

			@Override
			public void onFftDataCapture(Visualizer visualizer, byte[] fft,
					int samplingRate) {
				// FFT procesing here!
				double[] data2 = new double[8];
				String str = "";
				for(int i = 0; i < fft.length; i = i + 128 ) {
					//if(fft[i] > 0)
					double sum = 0;
					for(int j = i;j < i + 128; j++ ) {
						sum +=fft[j];
					}
					sum = sum / 128;
					
					data2[i/128] = sum;
					str += data2[i/128] + " ";
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
				//Log.v("WAVE", String.valueOf(waveform.length));
				String str = "";
				for(int i = 0; i < waveform.length; i++) {
					//if(fft[i] > 0)
					str += String.valueOf(waveform[i]) + " ";
				}
				//Log.v("WAVE", str);
				mFft = waveform.clone();
			}

		}, Visualizer.getMaxCaptureRate(), true, true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.v("GETffT", String.valueOf(viz.getFft(mFft)));
		
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);

		// закрашиваем холст белым цветом
		paint.setColor(Color.WHITE);
		canvas.drawPaint(paint);
		
		float x = 100;
		float y = 350;
		float[] pts = new float[data.length * 2];
		for(int i = 0; i < pts.length; i = i + 2) {
			pts[i] = x;
			pts[i+1] = (float) (y + data[i/2] * 100);
			x += 100;
		}
		
		paint.setColor(Color.RED);
		paint.setStrokeWidth(10);
		canvas.drawPoints(pts, paint);
		
		paint.setColor(Color.GRAY);
		paint.setStrokeWidth(5);
		canvas.drawLine(0, y, getWidth(), y, paint);
		
	}

	public void starViz() {
		viz.setEnabled(true);
	}
	
	public void stopViz() {
		viz.setEnabled(false);
	}
}
