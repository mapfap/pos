package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.Inventory;
import com.refresh.pos.domain.Product;
import com.refresh.pos.domain.ProductCatalog;
import com.refresh.pos.domain.Register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SaleProductCatalog extends Activity {
	private Register register;
	private ProductCatalog productCatalog;
	private ListView saleListView;
	private ArrayList<Map<String, String>> inventoryList;
	protected void onCreate(Bundle savedInstanceState) {
		
		try {
			productCatalog = Inventory.getInstance().getProductCatalog();
			register = Register.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		initUI(savedInstanceState);
	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productsalecatalog);
		saleListView = (ListView) findViewById(R.id.saleListView);
		
		saleListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
				String id = inventoryList.get(position).get("id").toString();
				Product product = productCatalog.getProductById(Integer.parseInt(id));
				register.addItem(product, 1);
				
				Intent newActivity = new Intent(SaleProductCatalog.this, SaleActivity.class);
				startActivity(newActivity);
			}
			
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		showList(productCatalog.getAllProduct());
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void showList(List<Product> list) {
		
		inventoryList = new ArrayList<Map<String, String>>();
		for(Product product : list) {
			inventoryList.add(product.toMap());
		}
		
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(SaleProductCatalog.this, inventoryList,
				R.layout.listview_productsalecatalog, new String[]{"name",""}, new int[] {R.id.name});
		saleListView.setAdapter(sAdap);
	}

}
