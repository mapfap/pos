package com.refresh.pos.ui.inventory;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.refresh.pos.R;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.inventory.Inventory;
import com.refresh.pos.domain.inventory.Product;
import com.refresh.pos.domain.inventory.ProductCatalog;
import com.refresh.pos.domain.inventory.Stock;
import com.refresh.pos.techicalservices.NoDaoSetException;

public class AddProductLotActivity extends Activity{
	private EditText costBox;
	private EditText quantityBox;
	private Stock stock;
	private ImageButton confirmButton;
	private ImageButton clearButton;
	private String id;
	private ProductCatalog productCatalog;
	private Product product;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
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
			setContentView(R.layout.activity_addproductlot);
			
			costBox = (EditText) findViewById(R.id.costBox);
			quantityBox = (EditText) findViewById(R.id.quantityBox);
			confirmButton = (ImageButton) findViewById(R.id.confirmButton);
			clearButton = (ImageButton) findViewById(R.id.clearButton);
			
			id = getIntent().getStringExtra("id");
			product = productCatalog.getProductById(Integer.parseInt(id));
			
			confirmButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					if (costBox.getText().toString().equals("")) {
						Toast.makeText(AddProductLotActivity.this,
								"Please input product's cost.", Toast.LENGTH_SHORT)
								.show();
					} else if (quantityBox.getText().toString().equals("")) {
						Toast.makeText(AddProductLotActivity.this,
								"Please input product's quantity.", Toast.LENGTH_SHORT)
								.show();
					} else {
						boolean success = stock.addProductLot(
								DateTimeStrategy.getCurrentTime(), 
								Integer.parseInt(quantityBox.getText().toString()), 
								product, 
								Double.parseDouble(costBox.getText().toString()));

						if (success) {
							Toast.makeText(AddProductLotActivity.this,"Successfully Add Stock: ",Toast.LENGTH_SHORT).show();
							clearAllBox();
//							AddProductActivity.this.finish();
							
							
						} else {
							Toast.makeText(AddProductLotActivity.this,"Failed to Add Stock" ,Toast.LENGTH_SHORT).show();
						}
					}
				}
			});
			
			clearButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(quantityBox.getText().toString().equals("") && costBox.getText().toString().equals("")){
						AddProductLotActivity.this.finish();
					}
					else
						clearAllBox();
				}
			});
	
		}
		
		private void clearAllBox() {
			costBox.setText("");
			quantityBox.setText("");
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

}
