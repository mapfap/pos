package com.refresh.pos.ui;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaleActivity extends Activity  {

	private EditText itemBarcode;
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("BARCODE", "BARCODE 'onActivityResult' Successfully.");

		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);

		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			// String scanFormat = scanningResult.getFormatName();
			// Toast.makeText(AddActivity.this,"got >> " + scanContent ,
			// Toast.LENGTH_SHORT).show();
			itemBarcode.setText(scanContent);
		} else {
			Toast.makeText(SaleActivity.this,
					"Failed to retrieve barcode." + resultCode,
					Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
		
		final TextView totalPrice = (TextView) findViewById(R.id.total);
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
		
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_additemsale);
		
		dialog.setTitle("Add Item");

		
		itemBarcode = (EditText) dialog.findViewById(R.id.barcodeTxt);
		final Button scanButton = (Button) dialog.findViewById(R.id.scanButton);
		scanButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(
						SaleActivity.this);
				scanIntegrator.initiateScan();
			}
		});
		
		final Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

}
