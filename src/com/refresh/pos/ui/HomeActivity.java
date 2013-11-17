package com.refresh.pos.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Register;
import com.refresh.pos.database.AndroidDatabase;
import com.refresh.pos.database.Database;
import com.refresh.pos.database.InventoryDao;
import com.refresh.pos.database.InventoryDaoAndroid;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.database.SaleDao;
import com.refresh.pos.database.SaleDaoAndroid;

public class HomeActivity extends Activity {

	private ImageButton inventoryButton;
	private ImageButton stockButton;
	private ImageButton saleButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initUI(savedInstanceState);
	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		inventoryButton = (ImageButton) findViewById(R.id.inventoryButton);
		stockButton = (ImageButton) findViewById(R.id.stockButton);
		saleButton = (ImageButton) findViewById(R.id.saleButton);
		
		inventoryButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(HomeActivity.this, InventoryActivity.class);
				startActivity(newActivity);
			}
		});
		
		stockButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent newActivity = new Intent(HomeActivity.this,StockActivity.class);
				startActivity(newActivity);
//				testAddProduct();
			}
		});
		
		saleButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent newActivity = new Intent(HomeActivity.this,SaleActivity.class);
				startActivity(newActivity);
			}
		});
		
	}

	protected void testAddProduct() {
		try {
			
			Inventory.getInstance().getProductCatalog().addProduct("Apple iPhone 4s", "65005", 20900 );
			Inventory.getInstance().getProductCatalog().addProduct("Apple iPhone 5", "65004", 25900 );
			Inventory.getInstance().getProductCatalog().addProduct("Apple Macbook Air (mid-2012)", "68701", 32900 );
			Inventory.getInstance().getProductCatalog().addProduct("Television ", "20004", 21000);
			Inventory.getInstance().getProductCatalog().addProduct("Lettuce" , "80775", 10.75);
			Inventory.getInstance().getProductCatalog().addProduct("Carrot", "10089", 8.50);
			Inventory.getInstance().getProductCatalog().addProduct("Applying UML and Pattern", "05667", 1.50);
			Inventory.getInstance().getProductCatalog().addProduct("Code Complete 2nd Edition", "99887", 2.50);		
			Toast.makeText(HomeActivity.this, "products added.", Toast.LENGTH_SHORT).show();
			
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
