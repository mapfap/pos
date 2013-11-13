package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;
import com.refresh.pos.core.Inventory;
import com.refresh.pos.core.LineItem;
import com.refresh.pos.core.Product;
import com.refresh.pos.core.Register;
import com.refresh.pos.core.Sale;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SaleActivity extends Activity  {

	private ListView inventoryListView;
	private Sale nowSale;
	private ArrayList<Map<String, String>> saleList;
	private ListView saleListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initUI(savedInstanceState);
	}

	private void initUI(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
		try {
			nowSale = Register.getInstance().initiateSale((new Date()).toString());
		} catch (NoDaoSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		saleListView = (ListView) findViewById(R.id.sale_List);
		showList(nowSale.getAllLineItem());
		final ImageButton addButton = (ImageButton) findViewById(R.id.sale_addButton);
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent newActivity = new Intent(SaleActivity.this, SaleProductCatalog.class);
				startActivity(newActivity);
			}
		});
		final TextView totalPrice = (TextView) findViewById(R.id.totalPrice);
		totalPrice.setText(nowSale.getTotal()+"");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void showList(List<LineItem> list) {
		
		saleList = new ArrayList<Map<String, String>>();
		for(LineItem line : list) {
			saleList.add(line.toMap());
		}
		
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(SaleActivity.this, saleList,
				R.layout.listview_sale, new String[]{"name","quantity","price"}, new int[] {R.id.name,R.id.quantity,R.id.price});
		saleListView.setAdapter(sAdap);
	}


}
