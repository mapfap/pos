package com.refresh.pos.ui;

import com.refresh.pos.R;
import com.refresh.pos.core.ProductCatalogController;
import com.refresh.pos.database.ProductDao;
import com.refresh.pos.database.ProductDaoAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class StockAddActivity extends Activity{
	private EditText itemBarcode;
	private EditText itemName;
	private ProductCatalogController productCatalogController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stockadd);

		ProductDao productDao = new ProductDaoAndroid(this);
		productCatalogController = new ProductCatalogController(productDao);
	}
	
}
