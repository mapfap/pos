package com.refresh.pos.ui;

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
import com.refresh.pos.core.ProductLot;
import com.refresh.pos.core.Stock;
import com.refresh.pos.database.NoDaoSetException;

public class ShowStockActivity extends Activity {

	private ListView lisView1;
	private Stock stock;
	private List<Map<String, String>> stockList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showstock);

		try {
			stock = Inventory.getInstance().getStock();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}

		
		lisView1 = (ListView) findViewById(R.id.listView1);
		showList();

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
				showList();
			}
		});

	}
	
	private void showList() {
		List<ProductLot> stck = stock.getAllProductLot();
		for(ProductLot productLot : stck) {
			stockList.add(productLot.toMap());
		}

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ShowStockActivity.this, stockList,
				R.layout.activity_columnstock, new String[] { "name",
						"amount","cost","dateAdded" }, new int[] { R.id.ColName,
						R.id.ColAmount, R.id.ColCost, R.id.ColDate });
		lisView1.setAdapter(sAdap);
	}

}
