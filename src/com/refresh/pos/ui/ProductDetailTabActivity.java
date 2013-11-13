package com.refresh.pos.ui;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductCatalog;
import com.refresh.pos.core.Stock;
import com.refresh.pos.database.NoDaoSetException;

public class ProductDetailTabActivity extends Activity {

	private ProductCatalog productCatalog;
	private Stock stock;
	private Product product;
	private TextView nameBox;
	private TextView barcodeBox;
	private TextView priceBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			stock = Inventory.getInstance().getStock();
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}

		initUI(savedInstanceState);

	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_detail);
//		nameBox = (TextView) findViewById(R.id.nameBox);
//		priceBox = (TextView) findViewById(R.id.priceBox);
//		barcodeBox = (TextView) findViewById(R.id.barcodeBox);
//
//		String id = getIntent().getStringExtra("id");
//		product = productCatalog.getProductById(Integer.parseInt(id));
//		nameBox.setText(product.getName());
//		priceBox.setText(product.getSalePrice()+"");
//		barcodeBox.setText(product.getBarcode());

	}

}
