package com.refresh.pos;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ShowlistActivity extends Activity {

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
		sAdap = new SimpleAdapter(ShowlistActivity.this, productList,
				R.layout.activity_column, new String[] { "_id", "name",
						"barcode" }, new int[] { R.id.ColProductID,
						R.id.ColName, R.id.ColBarcode });
		lisView1.setAdapter(sAdap);

		

	}

}
