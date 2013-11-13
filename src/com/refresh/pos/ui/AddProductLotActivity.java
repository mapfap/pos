package com.refresh.pos.ui;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.Stock;
import com.refresh.pos.database.NoDaoSetException;

public class AddProductLotActivity extends Activity{
//	private EditText itemBarcode;
	private EditText costBox;
	private EditText quantityBox;
	private Stock stock;
	private ImageButton confirmButton;
	private ImageButton clearButton;
	private String id;
	
//	private Inventory inventory;
//	private double quantity;
//
//	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		//		Log.d("BARCODE", "BARCODE 'onActivityResult' Successfully.");
//
//		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
//				requestCode, resultCode, intent);
//		//
//		if (scanningResult != null) {
//			String scanContent = scanningResult.getContents();
//			String name = inventory.getProductCatalog().getProductByBarcode(scanContent).getName();
//			if(name.equals("UNDEFINED")){
//				Toast.makeText(StockAddActivity.this,
//						"Can not find " + scanContent +" in database", Toast.LENGTH_SHORT)
//						.show();
//			}
//			else{
//				itemBarcode.setText(scanContent);
//				itemName.setText(name);
//			}
//			//
//		} else {
//			Toast.makeText(StockAddActivity.this,
//					"Failed to retrieve barcode." + resultCode, Toast.LENGTH_SHORT)
//					.show();
//		}
//	}
//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			stock = Inventory.getInstance().getStock();
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
						boolean success = stock.addProductLot((new Date()).toString(), Double.parseDouble(quantityBox.getText().toString()), Integer.parseInt(id), Double.parseDouble(costBox.getText().toString()));

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
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_addproductlot);
//		final EditText quantityTxt = (EditText) findViewById(R.id.quantityTxt);
//		quantity = Double.parseDouble(quantityTxt.getText().toString());
//		itemName = (EditText) findViewById(R.id.nameTxt);
//		itemBarcode = (EditText) findViewById(R.id.barcodeTxt);
//				itemBarcode.setEnabled(false);
//				itemName.setEnabled(false);
//		itemPrice = (EditText) findViewById(R.id.costTxt);
//		final Button scanButton = (Button) findViewById(R.id.scanButton);
//		scanButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				IntentIntegrator scanIntegrator = new IntentIntegrator(StockAddActivity.this);
//				scanIntegrator.initiateScan();
//
//
//			}
//		}
//				);
//		final Button plusButton = (Button) findViewById(R.id.plus);
//		plusButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				quantity = Integer.parseInt(quantityTxt.getText().toString());
//				quantity++;
//				quantityTxt.setText(quantity+"");
//			}
//		});
//		final Button minButton = (Button) findViewById(R.id.min);
//		minButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if(quantity==0){
//
//				}
//				else{
//					quantity = Double.parseDouble(quantityTxt.getText().toString());
//					quantity--;
//					quantityTxt.setText(quantity+"");
//				}
//
//			}
//		});
//		final Button addButton = (Button) findViewById(R.id.addButton);
//		addButton.setOnClickListener(new View.OnClickListener() {
//
//			@SuppressLint("NewApi")
//			@Override
//			public void onClick(View v) {
//				boolean checkPrice = itemPrice.getText().toString().isEmpty();
//				boolean checkBarcode = itemBarcode.getText().toString().isEmpty();
//				if(checkPrice||checkBarcode){
//					Toast.makeText(StockAddActivity.this,
//							"Still have some Blank Field!", Toast.LENGTH_SHORT)
//							.show();
//				}
//				else{
//					Date now = new Date();
//					String time = new SimpleDateFormat("dd MMM yyyy").format(now);
//					int id = inventory.getProductCatalog().getProductByBarcode(itemBarcode.getText().toString()).getId();
//					boolean success = inventory.getStock().addProductLot(time, quantity, id,Double.parseDouble(itemPrice.getText().toString()));
//					if(success){
//						Toast.makeText(StockAddActivity.this,
//								"Successfully Add : "+itemName.getText().toString(), Toast.LENGTH_SHORT)
//								.show();
//					}
//					else{
//						Toast.makeText(StockAddActivity.this,
//								"FAIL to Add : "+itemName.getText().toString(), Toast.LENGTH_SHORT)
//								.show();
//					}
//				}
//			}
//		});
//
//		final Button clearButton = (Button) findViewById(R.id.clearButton);
//		clearButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				quantity=0;
//				itemBarcode.setText("");
//				itemName.setText("");
//				itemPrice.setText("");
//				quantityTxt.setText(quantity+"");
//
//			}
//		});
//	}
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
