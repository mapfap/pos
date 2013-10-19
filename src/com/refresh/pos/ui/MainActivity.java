package com.refresh.pos.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.database.Dao;
import com.refresh.pos.database.InventoryDaoAndroid;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Dao dao = new InventoryDaoAndroid(this);
		Inventory.setProductDao(dao);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		final Button updateButton = (Button) findViewById(R.id.updateButton);
//		final TextView dbSizeText = (TextView) findViewById(R.id.dbSize);
//		updateButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				long size = productCatalogController.getSize();
//				dbSizeText.setText(size + " ");
//			}
//		});

		
		
		final Button productCatalogButton = (Button) findViewById(R.id.productCatalog);

		productCatalogButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(MainActivity.this,ShowListActivity.class);
				startActivity(newActivity);
			}
		});
		final Button stockButton = (Button) findViewById(R.id.stockButton);
		stockButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent newActivity = new Intent(MainActivity.this,ShowStockActivity.class);
				startActivity(newActivity);
			}
		});
		
		final Button saleButton = (Button) findViewById(R.id.saleButton);
		saleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
