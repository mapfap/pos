package com.refresh.pos.ui;

import java.util.List;
import java.util.Map;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.database.NoDaoSetException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SaleActivity extends Activity  {
//
//	private int amount = 0;
//	private EditText itemBarcode;
//	private EditText itemName;
//	private ListView lisView1;
//	List<Map<String, String>> inventoryList;
//	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		Log.d("BARCODE", "BARCODE 'onActivityResult' Successfully.");
//
//		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
//				requestCode, resultCode, intent);
////			Log.d("BARCODE",scanningResult.toString());
//		if (scanningResult != null) {
//			String scanContent = scanningResult.getContents();
//			String name = "";
//			try {
//				name = Inventory.getInstance().getProductCatalog().getProductByBarcode(scanContent).getName();
//			} catch (NoDaoSetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(name.equals("UNDEFINED")){
//				Log.d("Barcode","Can't find");
//			}
//			else{
//				itemBarcode.setText(scanContent);
//				itemName.setText(name);
//			}
//		} else {
//			Toast.makeText(SaleActivity.this,
//					"Failed to retrieve barcode." + resultCode,
//					Toast.LENGTH_SHORT).show();
//		}
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
//		
////		lisView1 = (listView1) findViewById(R.id);
//		
//		final TextView totalPrice = (TextView) findViewById(R.id.total);
//		totalPrice.setEnabled(false);
//		
//		final Button addButton = (Button) findViewById(R.id.addSaleItem);
//		addButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				showAddDialog();
//				
//			}
//		});
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//		
//	}
//	public void showAddDialog(){
//		
//		final Dialog dialog = new Dialog(this);
//		dialog.setContentView(R.layout.dialog_additemsale);
//		
//		dialog.setTitle("Add Item");
//
//		
//		itemName = (EditText) dialog.findViewById(R.id.nameTxt);
//		itemBarcode = (EditText) dialog.findViewById(R.id.barcodeTxt);
//		final EditText amountTxt = (EditText) dialog.findViewById(R.id.quantityTxt);
//		amount = Integer.parseInt(amountTxt.getText().toString());
//		final Button scanButton = (Button) dialog.findViewById(R.id.scanButton);
//		scanButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				IntentIntegrator scanIntegrator = new IntentIntegrator(
//						SaleActivity.this);
//				scanIntegrator.initiateScan();
//			}
//		});
//		
//		final Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
//		cancelButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
//		
//		final Button plusButton = (Button) dialog.findViewById(R.id.plus);
//		plusButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				amount = Integer.parseInt(amountTxt.getText().toString());
//				amount++;
//				amountTxt.setText(amount+"");
//			}
//		});
//		final Button minButton = (Button) dialog.findViewById(R.id.min);
//		minButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if(amount==0){
//
//				}
//				else{
//					amount = Integer.parseInt(amountTxt.getText().toString());
//					amount--;
//					amountTxt.setText(amount+"");
//				}
//
//			}
//		});
//		dialog.show();
	}

}
