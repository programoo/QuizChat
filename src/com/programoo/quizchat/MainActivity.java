package com.programoo.quizchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity implements Runnable {
	private String tag = getClass().getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Thread thread = new Thread(this);
		thread.start();
		
	}

	public void onClickNext(View view) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivityForResult(intent, 500);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void run() {
		Log.d(tag, "run");
		try {
			Thread.sleep(1000);

			Intent intent = new Intent(this, SecondActivity.class);
			startActivityForResult(intent, 500);
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}
