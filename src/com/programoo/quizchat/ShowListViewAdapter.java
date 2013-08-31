package com.programoo.quizchat;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ShowListViewAdapter extends BaseAdapter {
	private LayoutInflater mInflater;

	public ShowListViewAdapter(Context context, ArrayList<String> listData) {
		// TODO Auto-generated constructor stub
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Info.hostMsg.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Info.hostMsg.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = (View) mInflater.inflate(R.layout.show_listview, null);
		TextView tv = (TextView) v.findViewById(R.id.showText);
		tv.setText(Info.hostMsg.get(position));
		return v;
	}

}
