package com.depromeet.finddepro.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.depromeet.finddepro.R;
import com.depromeet.finddepro.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
}
