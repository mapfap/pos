package com.refresh.pos.ui;

import com.refresh.pos.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SaleActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
		
		final EditText totalPrice = (EditText) findViewById(R.id.totalPrice);
		totalPrice.setEnabled(false);
		
		final Button addButton = (Button) findViewById(R.id.addSaleItem);
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showAddDialog();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	public void showAddDialog(){
		final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
		final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

		final View Viewlayout = inflater.inflate(R.layout.dialog_additemsale,
		(ViewGroup) findViewById(R.id.dialog_addItem));
		
//		popDialog.setIcon(android.R.drawable.btn_star_big_on);
		popDialog.setTitle("Add Item");
		popDialog.setView(Viewlayout);
		
		final Button cancelButton = (Button) findViewById(R.id.cancel);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		popDialog.create();
		popDialog.show();
	}

}
