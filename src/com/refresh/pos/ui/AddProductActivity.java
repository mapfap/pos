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

	private EditText barcodeBox;
	private ProductCatalog productCatalog;
	private ImageButton scanButton;
	private EditText priceBox;
	private EditText nameBox;
	private ImageButton confirmButton;
	private ImageButton clearButton;
	private ImageButton backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		try {
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		initUI(savedInstanceState);
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("BARCODE", "retrive the result.");

		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);

		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			barcodeBox.setText(scanContent);
		} else {
			Toast.makeText(AddProductActivity.this,
					"Failed to retrieve barcode." + resultCode,
					Toast.LENGTH_SHORT).show();
		}
	}


	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addproduct);
		barcodeBox = (EditText) findViewById(R.id.barcodeBox);
		scanButton = (ImageButton) findViewById(R.id.scanButton);
		priceBox = (EditText) findViewById(R.id.priceBox);
		nameBox = (EditText) findViewById(R.id.nameBox);
		confirmButton = (ImageButton) findViewById(R.id.confirmButton);
//		backButton = (ImageButton) findViewById(R.id.backButton);
		clearButton = (ImageButton) findViewById(R.id.clearButton);
		
//		backButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				AddProductActivity.this.finish();
//			}
//		});

		scanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(AddProductActivity.this);
				scanIntegrator.initiateScan();
			}
		});

		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (nameBox.getText().toString().equals("")) {
					Toast.makeText(AddProductActivity.this,
							"Please input product's name.", Toast.LENGTH_SHORT)
							.show();
				} else if (barcodeBox.getText().toString().equals("")) {
					Toast.makeText(AddProductActivity.this,
							"Please input product's barcode.", Toast.LENGTH_SHORT)
							.show();
				} else if (priceBox.getText().toString().equals("")) {
					Toast.makeText(AddProductActivity.this,
							"Please input product's price.", Toast.LENGTH_SHORT)
							.show();
				} else {
					boolean success = productCatalog.addProduct(nameBox
							.getText().toString(), barcodeBox.getText()
							.toString(), Double.parseDouble(priceBox.getText()
							.toString()));

					if (success) {
						Toast.makeText(AddProductActivity.this,
								"Successfully Add : "
										+ nameBox.getText().toString(),
								Toast.LENGTH_SHORT).show();
						
						clearAllBox();
//						AddProductActivity.this.finish();
						
						
					} else {
						Toast.makeText(AddProductActivity.this,
								"Failed to Add " + nameBox.getText().toString(),
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		clearButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(barcodeBox.getText().toString().equals("") && nameBox.getText().toString().equals("") && priceBox.getText().toString().equals("")){
					AddProductActivity.this.finish();
				}
				else
					clearAllBox();
			}
		});

	}
	
	private void clearAllBox() {
		barcodeBox.setText("");
		nameBox.setText("");
		priceBox.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}