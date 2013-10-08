package com.refresh.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProductCatalogController productCatalogController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ProductDao productDao = new ProductDaoAndroid(this);
		productCatalogController = new ProductCatalogController(productDao);
		
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
				Intent newActivity = new Intent(MainActivity.this,AddActivity.class);
				startActivity(newActivity);
			}
		});
		
		final Button selectAllButton = (Button) findViewById(R.id.selectAllButton);

		selectAllButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(MainActivity.this,ShowlistActivity.class);
				startActivity(newActivity);
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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
