package com.eyesfree;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.eyesfree.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	
	// Variable Declaration
	// The following are used for the shake detection
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	private long lastUpdate;
	private boolean color = false;
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// view = findViewById(R.id.textView);
	    // view.setBackgroundColor(Color.GREEN);
	    
	    
		// ShakeDetector initialization
				mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
				mAccelerometer = mSensorManager
						.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
				mShakeDetector = new ShakeDetector();
				mShakeDetector.setOnShakeListener(new OnShakeListener() {

					@Override
					public void onShake(int count) {
						/*
						 * The following method, "handleShakeEvent(count):" is a stub //
						 * method you would use to setup whatever you want done once the
						 * device has been shook.
						 */
						handleShakeEvent(count);
					}

					private void handleShakeEvent(int count) {
						long actualTime = System.currentTimeMillis();
						if (actualTime - lastUpdate < 200) {
					        return;
					      }
					      lastUpdate = actualTime;
					      
					      // Placeholder Notification of Activation
					      Toast.makeText(MainActivity.this, "Shake Detected" +'\n' +count, Toast.LENGTH_SHORT)
					      .show();
					}
				});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// Add the following line to register the Session Manager Listener onResume
		mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onPause() {
		// Add the following line to unregister the Sensor Manager onPause
		mSensorManager.unregisterListener(mShakeDetector);
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
