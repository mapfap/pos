package com.refresh.pos.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.database.NoDaoSetException;

public class ShowStockActivity extends Activity {

	private Inventory inventory;
	private ListView lisView1;
	private List<HashMap<String, String>> stockList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showstock);

		try {
			
			inventory = Inventory.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}

//		List<Product> productList = inventory.getProductCatalog().getAllProduct();
//		Toast.makeText(ShowListActivity.this,productList.get(0).getName()+productList.get(0).getBarcode()+
//				productList.get(0).getId()+productList.get(0).getSalePrice(),
//				Toast.LENGTH_SHORT).show();
		
		stockList = inventory.getStock().getAllProductLotAsMap();
		
//		for (Product product : productList) {
//			Map<String, String> map = new HashMap<String, String>();
//	        map.put("_id", product.getId()+"");
// 	        map.put("name", product.getName());
// 	        map.put("barcode", product.getBarcode());
// 	        map.put("sale_price", product.getSalePrice()+"");
// 	        stockList.add(map);
//		}

		// listView1
		lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ShowStockActivity.this, stockList,
				R.layout.activity_columnstock, new String[] { "name",
						"amount","cost","date_added" }, new int[] { R.id.ColName,
						R.id.ColAmount, R.id.ColCost, R.id.ColDate });
		lisView1.setAdapter(sAdap);

		final Button addStockButton = (Button) findViewById(R.id.addStock);

		addStockButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(ShowStockActivity.this,StockAddActivity.class);
				startActivity(newActivity);
			}
		});
		final Button refreshButton = (Button) findViewById(R.id.refreshButton);
		refreshButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				stockList = inventory.getStock().getAllProductLotAsMap();
				SimpleAdapter sAdap;
				sAdap = new SimpleAdapter(ShowStockActivity.this, stockList,
						R.layout.activity_columnstock, new String[] { "name",
								"amount","cost","date_added" }, new int[] { R.id.ColName,
								R.id.ColAmount, R.id.ColCost, R.id.ColDate });
				lisView1.setAdapter(sAdap);
			}
		});

	}

}
