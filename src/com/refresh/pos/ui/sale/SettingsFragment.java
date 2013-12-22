package com.refresh.pos.ui.sale;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.refresh.pos.R;
import com.refresh.pos.ui.component.UpdatableFragment;

@SuppressLint("ValidFragment")
public class SettingsFragment extends Fragment {
    
	private UpdatableFragment fragment;

	public SettingsFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.layout_sale, container, false);

		
		
		return view;
	}

	private void initUI() {
		
	
	}
	
}
