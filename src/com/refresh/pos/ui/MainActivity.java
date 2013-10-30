package com.refresh.pos.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Sale;
import com.refresh.pos.database.AndroidDatabase;
import com.refresh.pos.database.Database;
import com.refresh.pos.database.InventoryDao;
import com.refresh.pos.database.InventoryDaoAndroid;
import com.refresh.pos.database.SaleDao;
import com.refresh.pos.database.SaleDaoAndroid;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Database database = new AndroidDatabase(this);
		InventoryDao inventoryDao = new InventoryDaoAndroid(database);
		SaleDao saleDao = new SaleDaoAndroid(database);
		
		Inventory.setInventoryDao(inventoryDao);
		Sale.setSaleDao(saleDao);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		createButton();
	}

	private void createButton() {
		final Button productCatalogButton = (Button) findViewById(R.id.productCatalog);

		productCatalogButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
//				Intent newActivity = new Intent(MainActivity.this,ShowListActivity.class);
				Intent newActivity = new Intent(MainActivity.this,ShowInventoryActivity.class);
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
				Intent newActivity = new Intent(MainActivity.this,SaleActivity.class);
				startActivity(newActivity);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
