package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.Inventory;
import com.refresh.pos.domain.Product;
import com.refresh.pos.domain.ProductLot;
import com.refresh.pos.domain.Stock;

public class StockActivity extends Activity {

	protected static final int SEARCH_LIMIT = 0;
	private Stock stock;
	private EditText searchBox;
	private Button scanButton;
	private ListView stockListView;
	List<Map<String, String>> stockList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		try {
			stock = Inventory.getInstance().getStock();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}

		initUI(savedInstanceState);

	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock);

		stockListView = (ListView) findViewById(R.id.stockListView);
		scanButton = (Button) findViewById(R.id.scanButton);
		searchBox = (EditText) findViewById(R.id.searchBox);

		searchBox.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (s.length() >= SEARCH_LIMIT) {
					search();
				}
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void onTextChanged(CharSequence s, int start, int before,int count) {}
		});
		
		showList(stock.getAllProductLot());
		
//		stockListView.setOnItemClickListener(new OnItemClickListener() {
//		      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
//		    	  
//		      }     
//		});

		scanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(StockActivity.this);
				scanIntegrator.initiateScan();
			}
		});
	}

	private void showList(List<ProductLot> list) {

		stockList = new ArrayList<Map<String, String>>();
		for (ProductLot productLot : list) {
			stockList.add(productLot.toMap());
		}

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(StockActivity.this, stockList,
				R.layout.listview_stock, new String[] { "productName", "dateAdded", "cost", "quantity", "left" },
				new int[] { R.id.name, R.id.date, R.id.cost, R.id.total, R.id.left });
		stockListView.setAdapter(sAdap);
	}

	@Override
	protected void onResume() {
		showList(stock.getAllProductLot());
		super.onResume();
	}

	private void search() {
		// String search = searchBox.getText().toString();
		// if (search.equals("")) {
		// showList(productCatalog.getAllProduct());
		// } else {
		// List<Product> result = productCatalog.searchProduct(search);
		// showList(result);
		// if (result.isEmpty()) {
		// Toast.makeText(InventoryActivity.this, "No results matched.",
		// Toast.LENGTH_SHORT).show();
		// }
		// }
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("BARCODE", "retrive the result.");

		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);

		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			searchBox.setText(scanContent);
		} else {
			Toast.makeText(StockActivity.this,
					"Failed to retrieve barcode." + resultCode,
					Toast.LENGTH_SHORT).show();
		}
	}

}
