package com.refresh.pos.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.Inventory;

public class HomeActivity extends Activity {

	private Button inventoryButton;
	private Button stockButton;
	private Button saleButton;
	private Button demoButton;
	private Button reportButton;
	private Button historyButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		initUI(savedInstanceState);
	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		inventoryButton = (Button) findViewById(R.id.inventoryButton);
		stockButton = (Button) findViewById(R.id.stockButton);
		saleButton = (Button) findViewById(R.id.saleButton);
		demoButton = (Button) findViewById(R.id.demoButton);
		reportButton = (Button) findViewById(R.id.reportButton);
		historyButton = (Button) findViewById(R.id.historyButton);
		
		reportButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(HomeActivity.this, MainActivity.class);
				startActivity(newActivity);
			}
		});
		
		reportButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(HomeActivity.this, SaleLedgerActivity.class);
				startActivity(newActivity);
			}
		});
		
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
		
		demoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				testAddProduct();
				Intent intent = (new Chart()).execute(HomeActivity.this);
				startActivity(intent);
			}
		});
		
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        moveTaskToBack(true);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	protected void testAddProduct() {
		try {
			
			Inventory.getInstance().getProductCatalog().addProduct("Apple iPhone 4s", "65005", 20900 );
			Inventory.getInstance().getProductCatalog().addProduct("Apple Macbook Air (mid-2012)", "68701", 32900 );
			Inventory.getInstance().getProductCatalog().addProduct("Television ", "20004", 21000);
			Inventory.getInstance().getProductCatalog().addProduct("Lettuce" , "80775", 10.75);
			Inventory.getInstance().getProductCatalog().addProduct("Carrot", "10089", 8.50);
			Inventory.getInstance().getProductCatalog().addProduct("Sumsung Television", "899089", 8.50);
			Inventory.getInstance().getProductCatalog().addProduct("Applying UML and Pattern", "05667", 1.50);
			Inventory.getInstance().getProductCatalog().addProduct("Code Complete 2nd Edition", "99887", 2.50);		
			Toast.makeText(HomeActivity.this, "products added.", Toast.LENGTH_SHORT).show();
			
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
