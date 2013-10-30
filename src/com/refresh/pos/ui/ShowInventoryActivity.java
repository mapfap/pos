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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductCatalog;
import com.refresh.pos.database.NoDaoSetException;

public class ShowInventoryActivity extends Activity {

	private ListView lisView1;
	private ProductCatalog productCatalog;
	List<Map<String, String>> inventoryList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productlist);

		try {
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		

		
		lisView1 = (ListView) findViewById(R.id.listView1);
		showList();
		
		final Button addProductButton = (Button) findViewById(R.id.addNewProduct);

		addProductButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(ShowInventoryActivity.this,AddActivity.class);
				startActivity(newActivity);
			}
		});
//		final Button refreshButton = (Button) findViewById(R.id.refreshButton);
//		refreshButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				showList();
//			}
//		});
		lisView1.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
		    	  String id = inventoryList.get(position).get("id").toString();
		    	  Intent newActivity = new Intent(ShowInventoryActivity.this,ShowItemActivity.class);
		    	  newActivity.putExtra("product_id", id);
				  startActivity(newActivity);  	    	  
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
		sAdap = new SimpleAdapter(ShowInventoryActivity.this, inventoryList,
				R.layout.activity_column, new String[] { "name",
						"barcode"}, new int[] { R.id.ColBarcode,
						R.id.ColName});
		lisView1.setAdapter(sAdap);
	}

}
