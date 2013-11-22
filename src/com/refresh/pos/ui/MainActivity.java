package com.refresh.pos.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.refresh.pos.R;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Fragment01 f1 = new Fragment01();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.add(R.id.fragment_place, f1);
		transaction.commit();
	}

	public void onSelectFragment(View view) {

		Fragment newFragment;

		if (view == findViewById(R.id.button1)) {
			newFragment = new Fragment01();
		} else if (view == findViewById(R.id.button2)) {
			newFragment = new Fragment02();
		} else {
			newFragment = new Fragment01();
		}

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fragment_place, newFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
}
