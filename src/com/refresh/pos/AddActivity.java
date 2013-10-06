package com.refresh.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		super.onCreate(savedInstanceState);
		final EditText itemName = (EditText) findViewById(R.id.nameTxt);
		final EditText itemCost = (EditText) findViewById(R.id.costTxt);
		final EditText itemPrice = (EditText) findViewById(R.id.priceTxt);
		final EditText itemAmount = (EditText) findViewById(R.id.amountTxt);
		final EditText itemDetail = (EditText) findViewById(R.id.detailTxt);
		
		final Button addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			Log.v(">>>",itemName.getText().toString());
			Log.v(">>>",itemCost.getText().toString());	
			Log.v(">>>",itemPrice.getText().toString());	
			Log.v(">>>",itemAmount.getText().toString());	
			Log.v(">>>",itemDetail.getText().toString());	

			}
		});
		final Button clearButton = (Button) findViewById(R.id.clearButton);
		clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				itemName.setText("");
				itemCost.setText("");
				itemPrice.setText("");
				itemAmount.setText("");
				itemDetail.setText("");
				
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
