package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductCatalog;
import com.refresh.pos.core.ProductLot;
import com.refresh.pos.core.Stock;
import com.refresh.pos.database.NoDaoSetException;

public class ProductStockTabActivity extends Activity {

	private ProductCatalog productCatalog;
	private Stock stock;
	private List<Map<String, String>> stockList;
	private ImageButton addProductLotButton;
	private ListView stockListView;

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
		setContentView(R.layout.tab_stock);
//		stockListView = (ListView) findViewById(R.id.stockListView);
//		addProductLotButton = (ImageButton) findViewById(R.id.addProductLotButton);
//
//		addProductLotButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				// TODO: FIX IT , 
////				Intent newActivity = new Intent(ProductStockTabActivity.this, AddProductLotActivity.class);
////				newActivity.putExtra("id", product.getId()+"");
////				startActivity(newActivity);
//			}
//		});


	}

	private void showList(List<ProductLot> list) {

		stockList = new ArrayList<Map<String, String>>();
		for(ProductLot productLot : list) {
			stockList.add(productLot.toMap());
		}

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ProductStockTabActivity.this, stockList,
				R.layout.listview_productstock, new String[]{"dateAdded","cost","quantity"}, new int[] {R.id.date,R.id.cost,R.id.quantity});
		stockListView.setAdapter(sAdap);
	}
}
