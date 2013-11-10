package com.refresh.pos.ui;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductCatalog;
import com.refresh.pos.core.Stock;
import com.refresh.pos.database.NoDaoSetException;

public class ProductDetailActivity extends Activity {

	private ProductCatalog productCatalog;
	private Stock stock;
	private Product product;
	private List<Map<String, String>> stockList;
	private TextView nameBox;
	private TextView barcodeBox;
	private TextView priceBox;
	private ImageButton addProductLotButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			stock = Inventory.getInstance().getStock();
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		initUI(savedInstanceState);
		
	}
	
	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productdetail);
		nameBox = (TextView) findViewById(R.id.nameBox);
		priceBox = (TextView) findViewById(R.id.priceBox);
		barcodeBox = (TextView) findViewById(R.id.barcodeBox);
		addProductLotButton = (ImageButton) findViewById(R.id.addProductLotButton);

		String id = getIntent().getStringExtra("id");
		product = productCatalog.getProductById(Integer.parseInt(id));
		nameBox.setText(product.getName());
		priceBox.setText(product.getSalePrice()+"");
		barcodeBox.setText(product.getBarcode());
		
		addProductLotButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(ProductDetailActivity.this, AddProductLotActivity.class);
				startActivity(newActivity);
			}
		});
		

}

	private void showList() {
		
//		stockList = new ArrayList<Map<String, String>>();
//		List<ProductLot> stck = stock.getAllProductLot();
//		for(ProductLot productLot : stck) {
//			if(productLot.getProduct().getId() == this.id)
//				stockList.add(productLot.toMap());
//		}
//
//		SimpleAdapter sAdap;
//		sAdap = new SimpleAdapter(ProductDetailActivity.this, stockList,
//				R.layout.activity_columnstock, new String[] { "productName",
//						"amount","cost","dateAdded" }, new int[] { R.id.ColName,
//						R.id.ColAmount, R.id.ColCost, R.id.ColDate });
//		lisView1.setAdapter(sAdap);
	}

}
