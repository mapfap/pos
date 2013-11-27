package com.refresh.pos.ui;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class ButtonAdapter extends SimpleAdapter {

	private List<? extends Map<String, ?>> data;
	private int buttonId;
	private String tag;
	public ButtonAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to, int buttonId, String tag) {
		super(context, data, resource, from, to);
		this.data = data;
		this.buttonId = buttonId;
		this.tag = tag;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		((View) view.findViewById(buttonId)).setTag(data.get(position).get(tag));
		return view;
	}

}
