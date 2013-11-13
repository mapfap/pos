package com.refresh.pos.ui;

import android.app.Activity;
import android.os.Bundle;

import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Stock;
import com.refresh.pos.database.NoDaoSetException;

public class StockActivity extends Activity{

	private Stock stock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		try {
			stock = Inventory.getInstance().getStock();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		initUI(savedInstanceState);
		
	
	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected void onResume() {
		
		super.onResume();
	}

}
