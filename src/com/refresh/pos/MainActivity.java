package com.refresh.pos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView formatTxt;
	private TextView contentTxt;
	private Activity activity;
	private ProductCatalogController productCatalogController;

	// public void onActivityResult(int requestCode, int resultCode, Intent
	// intent) {
	// IntentResult scanningResult =
	// IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	//
	// if (scanningResult != null) {
	// String scanContent = scanningResult.getContents();
	// String scanFormat = scanningResult.getFormatName();
	// formatTxt.setText("FORMAT: " + scanFormat);
	// contentTxt.setText("CONTENT: " + scanContent);
	//
	// } else{
	// Toast toast = Toast.makeText(getApplicationContext(),
	// "No scan data received!", Toast.LENGTH_SHORT);
	// toast.show();
	// }
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ProductDao productDao = new ProductDaoAndroid(this);
		productCatalogController = new ProductCatalogController(productDao);
		activity = this;

		formatTxt = (TextView) findViewById(R.id.scanFormat);
		contentTxt = (TextView) findViewById(R.id.scanContent);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button updateButton = (Button) findViewById(R.id.updateButton);
		final TextView dbSizeText = (TextView) findViewById(R.id.dbSize);
		updateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				long size = productCatalogController.getSize();
				dbSizeText.setText(size + " ");

			}

		});

		final Button insertButton = (Button) findViewById(R.id.insertButton);

		insertButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				boolean success = productCatalogController.add();
				if (success) {
					Toast.makeText(MainActivity.this,
							"Insert data successfullyl", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(MainActivity.this, "Failed to insert data",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		// final Button selectAllButton = (Button)
		// findViewById(R.id.selectAllButton);
		// selectAllButton.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		//
		// List<Item> itemList = inventory.getAllItem();
		// if(itemList == null) {
		// Toast.makeText(MainActivity.this,"Not found Data!",
		// Toast.LENGTH_SHORT).show();
		// }
		// else {
		// String all= "";
		// for (Item item : itemList) {
		// all+="id : " + item.name + " || " + item.price + "$, " + item.barcode
		// + "\n";
		// }
		// Toast.makeText(MainActivity.this, all, Toast.LENGTH_SHORT).show();
		// }
		//
		// }
		// });

		// final Button scanButton = (Button) findViewById(R.id.scanButton);
		// scanButton.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		// IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
		// scanIntegrator.initiateScan();
		// }
		// });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
