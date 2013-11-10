package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductCatalog;
import com.refresh.pos.database.NoDaoSetException;

public class InventoryActivity extends Activity {

	private ListView inventoryListView;
	private ProductCatalog productCatalog;
	List<Map<String, String>> inventoryList;
	private ImageButton addProductButton;
	private ImageButton searchButton;
	private EditText searchBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		try {
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		initUI(savedInstanceState);
		
	}
	
	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);
		inventoryListView = (ListView) findViewById(R.id.inventoryListView);
		addProductButton = (ImageButton) findViewById(R.id.addProductButton);
		searchButton = (ImageButton) findViewById(R.id.searchButton);
		searchBox = (EditText) findViewById(R.id.searchBox);
		
		addProductButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(InventoryActivity.this,AddProductActivity.class);
				startActivity(newActivity);
			}
		});
		
		searchBox.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	if (s.length() >= 2) {
	        		showList(productCatalog.getProductByName(s.toString()));
	        	}
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				search();
			}
		});
		
		inventoryListView.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
		    	  String id = inventoryList.get(position).get("id").toString();
//		    	  Intent newActivity = new Intent(ShowInventoryActivity.this,ShowItemActivity.class);
//		    	  newActivity.putExtra("product_id", id);
//				  startActivity(newActivity);  	    	  
		      }     
      });
		
	}

	private void showList(List<Product> list) {
		
		inventoryList = new ArrayList<Map<String, String>>();
		for(Product product : list) {
			inventoryList.add(product.toMap());
		}
		
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(InventoryActivity.this, inventoryList,
				R.layout.listview_inventory, new String[]{"name"}, new int[] {R.id.name});
		inventoryListView.setAdapter(sAdap);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		search();
	}

	private void search() {
		String name = searchBox.getText().toString();
		if (name.equals("")) {
			showList(productCatalog.getAllProduct());
		} else {
			showList(productCatalog.getProductByName(name));
		}
	}

}
