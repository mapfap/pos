package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		try {
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		initUI(savedInstanceState);
		
		showList();

	}
	
	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);
		inventoryListView = (ListView) findViewById(R.id.inventoryListView);
		final ImageButton addProductButton = (ImageButton) findViewById(R.id.addProductButton);

		addProductButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(InventoryActivity.this,AddProductActivity.class);
				startActivity(newActivity);
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

	private void showList() {
		
		inventoryList = new ArrayList<Map<String, String>>();
		List<Product> catalog = productCatalog.getAllProduct();
		for(Product product : catalog) {
			inventoryList.add(product.toMap());
		}
		
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(InventoryActivity.this, inventoryList,
				R.layout.listview_inventory, new String[]{"name"}, new int[] {R.id.name});
		inventoryListView.setAdapter(sAdap);
	}

}
