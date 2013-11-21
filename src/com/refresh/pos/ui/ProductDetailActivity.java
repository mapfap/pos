package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.Inventory;
import com.refresh.pos.domain.Product;
import com.refresh.pos.domain.ProductCatalog;
import com.refresh.pos.domain.ProductLot;
import com.refresh.pos.domain.Stock;

public class ProductDetailActivity extends Activity {

	private ProductCatalog productCatalog;
	private Stock stock;
	private Product product;
	private List<Map<String, String>> stockList;
	private TextView nameBox;
	private TextView barcodeBox;
	private TextView priceBox;
	private ImageButton addProductLotButton;
	private TabHost mTabHost;
	private ListView stockListView;
	private String id;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		try {
			stock = Inventory.getInstance().getStock();
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		Log.d("Product Detail", "id = " + getIntent().getStringExtra("id").toString());
		id = getIntent().getStringExtra("id");
		product = productCatalog.getProductById(Integer.parseInt(id));
		
		
		initUI(savedInstanceState);
		
		
		nameBox.setText(product.getName());
		priceBox.setText(product.getUnitPrice()+"");
		barcodeBox.setText(product.getBarcode());
		
	}
	
	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productdetail);
		stockListView = (ListView) findViewById(R.id.stockListView);
		nameBox = (TextView) findViewById(R.id.nameBox);
		priceBox = (TextView) findViewById(R.id.priceBox);
		barcodeBox = (TextView) findViewById(R.id.barcodeBox);
		addProductLotButton = (ImageButton) findViewById(R.id.addProductLotButton);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Detail").setContent(R.id.tab1));
		mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Stock").setContent(R.id.tab2));
		mTabHost.setCurrentTab(0);
		
		addProductLotButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(ProductDetailActivity.this, AddProductLotActivity.class);
				newActivity.putExtra("id", product.getId()+"");
				startActivity(newActivity);
			}
		});
	}
	
	private void showList(List<ProductLot> list) {

		stockList = new ArrayList<Map<String, String>>();
		for(ProductLot productLot : list) {
			stockList.add(productLot.toMap());
		}

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ProductDetailActivity.this, stockList,
				R.layout.listview_productstock, new String[]{"dateAdded","cost","quantity"}, new int[] {R.id.dateAdded,R.id.cost,R.id.quantity});
		stockListView.setAdapter(sAdap);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		showList(stock.getProductLotByProductId(Integer.parseInt(id)));
	}

}
