package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
	private EditText nameBox;
	private EditText barcodeBox;
	private TextView stockSumBox;
	private EditText priceBox;
	private Button addProductLotButton;
	private Button submitEditButton;
	private TabHost mTabHost;
	private ListView stockListView;
	private String id;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		try {
			stock = Inventory.getInstance().getStock();
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}

		Log.d("Product Detail", "id = "
				+ getIntent().getStringExtra("id").toString());
		id = getIntent().getStringExtra("id");
		product = productCatalog.getProductById(Integer.parseInt(id));

		initUI(savedInstanceState);

		nameBox.setText(product.getName());
		priceBox.setText(product.getUnitPrice() + "");
		barcodeBox.setText(product.getBarcode());

	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_productdetail);
		stockListView = (ListView) findViewById(R.id.stockListView);
		nameBox = (EditText) findViewById(R.id.nameBox);
		priceBox = (EditText) findViewById(R.id.priceBox);
		barcodeBox = (EditText) findViewById(R.id.barcodeBox);
		stockSumBox = (TextView) findViewById(R.id.stockSumBox);
		submitEditButton = (Button) findViewById(R.id.submitEditButton);
		submitEditButton.setVisibility(View.INVISIBLE);
		addProductLotButton = (Button) findViewById(R.id.addProductLotButton);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Detail")
				.setContent(R.id.tab1));
		mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Stock")
				.setContent(R.id.tab2));
		mTabHost.setCurrentTab(0);

		addProductLotButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(ProductDetailActivity.this,
						AddProductLotActivity.class);
				newActivity.putExtra("id", product.getId() + "");
				startActivity(newActivity);
			}
		});

		nameBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nameBox.setFocusable(true);
				nameBox.setFocusableInTouchMode(true);
				nameBox.setBackgroundColor(Color.parseColor("#C0FF3E"));
				submitEditButton.setVisibility(View.VISIBLE);
			}
		});

		priceBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				priceBox.setFocusable(true);
				priceBox.setFocusableInTouchMode(true);
				priceBox.setBackgroundColor(Color.parseColor("#C0FF3E"));
				submitEditButton.setVisibility(View.VISIBLE);
			}
		});

		barcodeBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				barcodeBox.setFocusable(true);
				barcodeBox.setFocusableInTouchMode(true);
				barcodeBox.setBackgroundColor(Color.parseColor("#C0FF3E"));
				submitEditButton.setVisibility(View.VISIBLE);
			}
		});

		submitEditButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				nameBox.setFocusable(false);
				nameBox.setFocusableInTouchMode(false);
				nameBox.setBackgroundColor(Color.parseColor("#FFFFFF"));
				priceBox.setFocusable(false);
				priceBox.setFocusableInTouchMode(false);
				priceBox.setBackgroundColor(Color.parseColor("#87CEEB"));
				barcodeBox.setFocusable(false);
				barcodeBox.setFocusableInTouchMode(false);
				barcodeBox.setBackgroundColor(Color.parseColor("#87CEEB"));
				product.setName(nameBox.getText().toString());
				if(priceBox.getText().toString().equals(""))
					priceBox.setText("0.0");
				product.setUnitPrice(Double.parseDouble(priceBox.getText().toString()));
				product.setBarcode(barcodeBox.getText().toString());
				productCatalog.editProduct(product);
				submitEditButton.setVisibility(View.INVISIBLE);
			}
		});
	}

	private void showList(List<ProductLot> list) {

		stockList = new ArrayList<Map<String, String>>();
		for (ProductLot productLot : list) {
			stockList.add(productLot.toMap());
		}

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ProductDetailActivity.this, stockList,
				R.layout.listview_productstock, new String[] { "dateAdded",
						"cost", "quantity", "left" }, new int[] {
						R.id.dateAdded, R.id.cost, R.id.quantity, R.id.left });
		stockListView.setAdapter(sAdap);
	}

	@Override
	protected void onResume() {
		super.onResume();
		int productId = Integer.parseInt(id);
		stockSumBox.setText(stock.getStockSumById(productId)+"");
		showList(stock.getProductLotByProductId(productId));
	}

}