package com.refresh.pos.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.database.NoDaoSetException;

public class StockAddActivity extends Activity{
	private EditText itemBarcode;
	private EditText itemName;
	private Inventory inventory;
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		Log.d("BARCODE", "BARCODE 'onActivityResult' Successfully.");
		
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
//
		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
//			String scanFormat = scanningResult.getFormatName();
//			Toast.makeText(AddActivity.this,"got >> " + scanContent , Toast.LENGTH_SHORT).show();
//			itemBarcode.setText(scanContent);
			String name = inventory.getProductCatalog().getProductByBarcode(scanContent).getName();
			if(name.equals("UNDEFINED")){
				Toast.makeText(StockAddActivity.this,
						"Can not find " + scanContent +" in database", Toast.LENGTH_SHORT)
						.show();
			}
			else{
				itemBarcode.setText(scanContent);
				itemName.setText(name);
			}
//
		} else {
			Toast.makeText(StockAddActivity.this,
					"Failed to retrieve barcode." + resultCode, Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			inventory = Inventory.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stockadd);
		
		itemName = (EditText) findViewById(R.id.nameTxt);
		itemBarcode = (EditText) findViewById(R.id.barcodeTxt);
		itemBarcode.setEnabled(false);
		itemName.setEnabled(false);
		
		final Button scanButton = (Button) findViewById(R.id.scanButton);
		scanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(StockAddActivity.this);
				scanIntegrator.initiateScan();
				
				
				}
			}
		);
	}
	
}
