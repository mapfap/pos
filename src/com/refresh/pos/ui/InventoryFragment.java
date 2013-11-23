package com.refresh.pos.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import com.refresh.pos.domain.ProductCatalog;

public class InventoryFragment extends Fragment {

	protected static final int SEARCH_LIMIT = 0;
	private ListView inventoryListView;
	private ProductCatalog productCatalog;
	List<Map<String, String>> inventoryList;
	private Button addProductButton;
	private EditText searchBox;
	private Button scanButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		try {
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		View view = inflater.inflate(R.layout.layout_inventory, container, false);
		
		inventoryListView = (ListView) view.findViewById(R.id.inventoryListView);
		addProductButton = (Button) view.findViewById(R.id.addProductButton);
		scanButton = (Button) view.findViewById(R.id.scanButton);
		searchBox = (EditText) view.findViewById(R.id.searchBox);
		initUI();
		return view;
	}
	
	private void initUI() {
		
		addProductButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(getActivity().getBaseContext() ,AddProductActivity.class);
				startActivity(newActivity);
			}
		});
		
		searchBox.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	if (s.length() >= SEARCH_LIMIT) {
	        		search();
	        	}
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    });
		
		inventoryListView.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
		    	  String id = inventoryList.get(position).get("id").toString();
//		    	  Intent newActivity = new Intent(getActivity().getBaseContext() , ProductDetailActivity.class);
		    	  ViewPager viewPager = ((MainActivity) getActivity()).getViewPager();
		    	  viewPager.setCurrentItem(0);
//		    	  newActivity.putExtra("id", id);
//		    	  startActivity(newActivity);  	    	  
		      }     
		});

		scanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
				scanIntegrator.initiateScan();
			}
		});
		
	}

	private void showList(List<Product> list) {
		
		inventoryList = new ArrayList<Map<String, String>>();
		for(Product product : list) {
			inventoryList.add(product.toMap());
		}
		
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(getActivity().getBaseContext(), inventoryList,
				R.layout.listview_inventory, new String[]{"name"}, new int[] {R.id.name});
		inventoryListView.setAdapter(sAdap);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		search();
	}

	private void search() {
		String search = searchBox.getText().toString();
		if (search.equals("")) {
			showList(productCatalog.getAllProduct());
		} else {
			List<Product> result = productCatalog.searchProduct(search);
			showList(result);
			if (result.isEmpty()) {
				Toast.makeText(getActivity().getBaseContext() , "No results matched.", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("BARCODE", "retrive the result.");

		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);

		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			searchBox.setText(scanContent);
		} else {
			Toast.makeText(getActivity().getBaseContext() ,
					"Failed to retrieve barcode." + resultCode,
					Toast.LENGTH_SHORT).show();
		}
	}

}
