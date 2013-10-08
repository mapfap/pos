package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.refresh.pos.R;
import com.refresh.pos.R.id;
import com.refresh.pos.R.layout;
import com.refresh.pos.core.ProductCatalogController;
import com.refresh.pos.database.ProductDao;
import com.refresh.pos.database.ProductDaoAndroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ShowListActivity extends Activity {

	private ProductCatalogController productCatalogController;
	ArrayList<HashMap<String, String>> productList;
	ArrayList<HashMap<String, String>> stockList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showlist);

		ProductDao productDao = new ProductDaoAndroid(this);
		productCatalogController = new ProductCatalogController(productDao);
		productList = productCatalogController.selectAllData();

		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ShowListActivity.this, productList,
				R.layout.activity_column, new String[] { "_id", "name",
						"barcode" }, new int[] { R.id.ColProductID,
						R.id.ColName, R.id.ColBarcode });
		lisView1.setAdapter(sAdap);

//		final Button addProductButton = (Button) findViewById(R.id.addNewProduct);
//
//		addProductButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				Intent newActivity = new Intent(ShowListActivity.this,AddActivity.class);
//				startActivity(newActivity);
//			}
//		});

	}

}
