package com.refresh.pos.ui;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;
import com.refresh.pos.core.ProductCatalogController;
import com.refresh.pos.database.ProductDao;
import com.refresh.pos.database.ProductDaoAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StockAddActivity extends Activity{
	private EditText itemBarcode;
	private EditText itemName;
	private ProductCatalogController productCatalogController;
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		Log.d("BARCODE", "BARCODE 'onActivityResult' Successfully.");
		
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
//
		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();
//			Toast.makeText(AddActivity.this,"got >> " + scanContent , Toast.LENGTH_SHORT).show();
			itemBarcode.setText(scanContent);
			
//
		} else {
			Toast.makeText(StockAddActivity.this,
					"Failed to retrieve barcode." + resultCode, Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stockadd);

		ProductDao productDao = new ProductDaoAndroid(this);
		productCatalogController = new ProductCatalogController(productDao);
		

		itemName = (EditText) findViewById(R.id.nameTxt);
		itemBarcode = (EditText) findViewById(R.id.barcodeTxt);
		itemBarcode.setEnabled(false);
		itemName.setEnabled(false);
		
		final Button scanButton = (Button) findViewById(R.id.scanButton);
		scanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				IntentIntegrator scanIntegrator = new IntentIntegrator(StockAddActivity.this);
//				scanIntegrator.initiateScan();
				Object get = productCatalogController.getProductNamebyBarcode("8851959139707");
				if(get == null){
					Log.v(">>>","null");
				}
				else{
					String[] a = (String[]) get;
					itemBarcode.setText(a[0]);
					itemName.setText(a[1]);
				}
			}
		});
	}
	
}
