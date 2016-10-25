package com.bazanski.myvisualizer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {

	Draw2D d;
	
	VisView vs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		/*
		d = new Draw2D(this);
		setContentView(d);
		*/
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        vs = new VisView(this);
        setContentView(vs);
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		vs.starViz();
		/*
		Visualizer viz = new Visualizer(0);
        viz.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        viz.setEnabled(true);
        //to get data use viz.getFft(fftdata);
        viz.getFft(fftdata);
        */
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		vs.stopViz();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
