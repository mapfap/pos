package com.refresh.pos.ui.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import com.refresh.pos.R;
import com.refresh.pos.domain.inventory.Inventory;
import com.refresh.pos.domain.inventory.Product;
import com.refresh.pos.domain.inventory.ProductCatalog;
import com.refresh.pos.domain.inventory.ProductLot;
import com.refresh.pos.domain.inventory.Stock;
import com.refresh.pos.techicalservices.NoDaoSetException;

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
	private Button cancelEditButton;
	private TabHost mTabHost;
	private ListView stockListView;
	private String id;
	private String[] remember;
	private int[] count;


	@Override
	public void onCreate(Bundle savedInstanceState) {
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setTitle("Product's Detail");
//		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
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
		remember = new String[3];
		count = new int[3];
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
		cancelEditButton = (Button) findViewById(R.id.cancelEditButton);
		cancelEditButton.setVisibility(View.INVISIBLE);
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
				cancelEditButton.setVisibility(View.VISIBLE);
				if(count[0] == 0){
					remember[0] = nameBox.getText().toString();
					if(count[1] == 0){
						remember[1] = priceBox.getText().toString();
					}
					if(count[2] == 0){
						remember[2] = barcodeBox.getText().toString();
					}
					count[0]++;
				}
			}
		});

		priceBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				priceBox.setFocusable(true);
				priceBox.setFocusableInTouchMode(true);
				priceBox.setBackgroundColor(Color.parseColor("#C0FF3E"));
				submitEditButton.setVisibility(View.VISIBLE);
				cancelEditButton.setVisibility(View.VISIBLE);
				if(count[1] == 0){
					remember[1] = priceBox.getText().toString();
					if(count[0] == 0){
						remember[0] = nameBox.getText().toString();
					}
					if(count[2] == 0){
						remember[2] = barcodeBox.getText().toString();
					}
					count[1]++;
				}
			}
		});

		barcodeBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				barcodeBox.setFocusable(true);
				barcodeBox.setFocusableInTouchMode(true);
				barcodeBox.setBackgroundColor(Color.parseColor("#C0FF3E"));
				submitEditButton.setVisibility(View.VISIBLE);
				cancelEditButton.setVisibility(View.VISIBLE);
				if(count[2] == 0){
					remember[2] = barcodeBox.getText().toString();
					if(count[0] == 0){
						remember[0] = nameBox.getText().toString();
					}
					if(count[1] == 0){
						remember[1] = priceBox.getText().toString();
					}
					count[2]++;
				}
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
				cancelEditButton.setVisibility(View.INVISIBLE);
				count[0] = 0;
				count[1] = 0;
				count[2] = 0;
			}
		});
		
		cancelEditButton.setOnClickListener(new View.OnClickListener() {
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
				submitEditButton.setVisibility(View.INVISIBLE);
				cancelEditButton.setVisibility(View.INVISIBLE);
				nameBox.setText(remember[0]);
				priceBox.setText(remember[1]);
				barcodeBox.setText(remember[2]);
				count[0] = 0;
				count[1] = 0;
				count[2] = 0;
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
				"cost", "quantity" }, new int[] {
				R.id.dateAdded, R.id.cost, R.id.quantity, });
		stockListView.setAdapter(sAdap);
	}

	@Override
	protected void onResume() {
		super.onResume();
		int productId = Integer.parseInt(id);
		stockSumBox.setText(stock.getStockSumById(productId)+"");
		showList(stock.getProductLotByProductId(productId));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}