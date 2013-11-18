package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.Inventory;
import com.refresh.pos.domain.LineItem;
import com.refresh.pos.domain.Product;
import com.refresh.pos.domain.Register;
import com.refresh.pos.domain.Sale;

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

	private ListView lineItemListView;
	private Sale currentSale;
	private Register register;
	private ArrayList<Map<String, String>> saleList;
	private ListView saleListView;
	private ImageButton addButton;
	private TextView totalPrice;
	private ImageButton payButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("SALE", "I'm created!!");
		
		try {
			register = Register.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		currentSale = register.initiateSale((new Date()).toString());
		
		initUI(savedInstanceState);
	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
		saleListView = (ListView) findViewById(R.id.sale_List);
		totalPrice = (TextView) findViewById(R.id.totalPrice);
		addButton = (ImageButton) findViewById(R.id.sale_addButton);
		payButton = (ImageButton) findViewById(R.id.payButton);
		
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent newActivity = new Intent(SaleActivity.this, SaleProductCatalog.class);
				startActivity(newActivity);
			}
		});
		
		payButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				register.endSale((new Date()).toString());
				SaleActivity.this.finish();
				
				Intent intent = new Intent(SaleActivity.this, HomeActivity.class);
			    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    startActivity(intent);
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("SALE", "I'm back!!!");
		showList(currentSale.getAllLineItem());
		totalPrice.setText(currentSale.getTotal()+"");
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
