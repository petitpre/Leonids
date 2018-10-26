package com.plattysoft.leonids.examples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.ParticleField;
import com.plattysoft.leonids.ParticleSystem;

public class OneShotSimpleExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		ParticleSystem system = new ParticleSystem(this, 10, R.drawable.star, 10_000)
		.setSpeedRange(0.01f, 0.1f);

		system.oneShot(arg0, 2);

		system.getDrawingView().setParticleClickListener(new ParticleField.ParticleClickListener() {
			@Override
			public boolean onTouch(Particle particle, MotionEvent event) {

				Log.i("","click on particle " + particle);
				return true;
			}
		});

	}

}
