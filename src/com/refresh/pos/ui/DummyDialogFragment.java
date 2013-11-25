package com.refresh.pos.ui;

import com.refresh.pos.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DummyDialogFragment extends DialogFragment {
	static DummyDialogFragment newInstance(){
		return new DummyDialogFragment();
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_addproduct, container, false);
//        View tv = v.findViewById(R.id.text);
      
        return v;
    }
}
