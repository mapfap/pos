package com.refresh.pos.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.ProductCatalog;
import com.refresh.pos.database.NoDaoSetException;

public class AddProductActivity extends Activity {

	private EditText itemBarcode;
	private ProductCatalog productCatalog;

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("BARCODE", "retrive the result.");

		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);

		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			itemBarcode.setText(scanContent);
		} else {
			Toast.makeText(AddProductActivity.this,
					"Failed to retrieve barcode." + resultCode,
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initUI(savedInstanceState);
		
		try {
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}

	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addproduct);
		itemBarcode = (EditText) findViewById(R.id.barcodeBox);
		final ImageButton scanButton = (ImageButton) findViewById(R.id.scanButton);
		final EditText itemPrice = (EditText) findViewById(R.id.priceBox);
		final EditText itemName = (EditText) findViewById(R.id.nameBox);
		final ImageButton addButton = (ImageButton) findViewById(R.id.confirmButton);
//		final ImageButton clearButton = (ImageButton) findViewById(R.id.clearButton);

		scanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(AddProductActivity.this);
				scanIntegrator.initiateScan();
			}
		});

		addButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (itemName.getText().toString().equals("")) {
					Toast.makeText(AddProductActivity.this,
							"Please input product's name.", Toast.LENGTH_SHORT)
							.show();
				} else if (itemBarcode.getText().toString().equals("")) {
					Toast.makeText(AddProductActivity.this,
							"Please input product's barcode.", Toast.LENGTH_SHORT)
							.show();
				} else if (itemPrice.getText().toString().equals("")) {
					Toast.makeText(AddProductActivity.this,
							"Please input product's price.", Toast.LENGTH_SHORT)
							.show();
				} else {
					boolean success = productCatalog.addProduct(itemName
							.getText().toString(), itemBarcode.getText()
							.toString(), Double.parseDouble(itemPrice.getText()
							.toString()));

					if (success) {
						Toast.makeText(
								AddProductActivity.this,
								"Successfully Add : "
										+ itemName.getText().toString(),
								Toast.LENGTH_SHORT).show();
						
						itemName.setText("");
						itemBarcode.setText("");
						itemPrice.setText("");
						
					} else {
						Toast.makeText(AddProductActivity.this,
								"Failed to insert data", Toast.LENGTH_SHORT)
								.show();
					}
				}
			}
		});
		
//		clearButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				itemName.setText("");
//				itemBarcode.setText("");
//				itemPrice.setText("");
//
//			}
//		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
