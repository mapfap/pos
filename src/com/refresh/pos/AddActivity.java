package com.refresh.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		super.onCreate(savedInstanceState);
		
		final Button insertButton = (Button) findViewById(R.id.addButton);
		final TextView itemName = (TextView) findViewById(R.id.nameTxt);
		insertButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			Log.v(">>>",itemName.getText().toString());	

			}
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
