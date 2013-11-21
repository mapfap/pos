package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.Sale;
import com.refresh.pos.domain.SaleLedger;



public class SaleLedgerActivity extends Activity {

	private SaleLedger saleLedger;
	List<Map<String, String>> saleList;
	private ListView saleLedgerListView;
	private EditText searchBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		try {
			saleLedger = SaleLedger.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}

		initUI(savedInstanceState);

	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saleledger);

		saleLedgerListView = (ListView) findViewById(R.id.saleListView);
		searchBox = (EditText) findViewById(R.id.searchBox);

		searchBox.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
//				if (s.length() >= SEARCH_LIMIT) {
//					search();
//				}
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void onTextChanged(CharSequence s, int start, int before,int count) {}
		});
		
//		stockListView.setOnItemClickListener(new OnItemClickListener() {
//		      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
//		    	  
//		      }     
//		});

	}

	private void showList(List<Sale> list) {

		saleList = new ArrayList<Map<String, String>>();
		for (Sale sale : list) {
			saleList.add(sale.toMap());
//			Log.d("ledger", sale.getStartTime());
//			Log.d("ledger", sale.getEndTime());
		}
		

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(SaleLedgerActivity.this, saleList,
				R.layout.listview_saleledger, new String[] { "id", "startTime", "endTime", "status"},
				new int[] { R.id.sid, R.id.startTime, R.id.endTime, R.id.status });
		saleLedgerListView.setAdapter(sAdap);
	}

	@Override
	protected void onResume() {
		Log.d("ledger",saleLedger.getAllSale().size()+"");
		showList(saleLedger.getAllSale());
		
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
}
