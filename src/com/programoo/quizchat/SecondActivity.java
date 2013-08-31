package com.programoo.quizchat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class SecondActivity extends Activity {
	public static Context mainContext;
	public static ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		lv = (ListView) findViewById(R.id.listViewData);
		ShowListViewAdapter ad = new ShowListViewAdapter(this, null);
		lv.setAdapter(ad);
		mainContext = this;
		new RequestTask("type");
	}

	static Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String text = (String) msg.obj;
			// call setText here
			ShowListViewAdapter ad = new ShowListViewAdapter(mainContext, Info.hostMsg);
			lv.setAdapter(ad);
			scrollMyListViewToBottom();
		}
	};

	private static void scrollMyListViewToBottom() {
	    lv.post(new Runnable() {
	        @Override
	        public void run() {
	            // Select the last row so it will scroll into view...
	            lv.setSelection(lv.getCount() - 1);
	        }
	    });
	}
	
	public static void updateUIThread(){
		Message msg = new Message();
		String textTochange = "text";
		msg.obj = textTochange;
		mHandler.sendMessage(msg);
	}
	
	public void onClickNext(View view) {
		Intent intent = new Intent(this, ThirdActivity.class);
		startActivityForResult(intent, 600);
		overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
	}

	private class RequestTask extends AsyncTask<String, String, String> {
		private String tag = getClass().getSimpleName();
		private String requestType;
		private Socket clientSocket;

		public RequestTask(String requestType) {
			this.requestType = requestType;
			this.execute("url");
		}

		@Override
		protected String doInBackground(String... uri) {
			try {
				clientSocket = new Socket("94.249.185.63", 50000);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "ok";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			new BClientThread(clientSocket).start();

			Log.d(this.getClass().getSimpleName(), "onPostExecute");
		}

	}

}
