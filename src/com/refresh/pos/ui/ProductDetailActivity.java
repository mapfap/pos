package com.refresh.pos.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.refresh.pos.R;

public class ProductDetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productdetail);
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabs);
		TabSpec tab1 = tabHost.newTabSpec("");
		TabSpec tab2 = tabHost.newTabSpec("");
		tab1.setIndicator("Detail");
		Intent ProductCatAc = new Intent(this,ProductDetailTabActivity.class);
		tab1.setContent( ProductCatAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		tab1.setContent( ProductCatAc );
		tab2.setIndicator("Stock");
		Intent StockAc =  new Intent(this,ProductStockTabActivity.class);
		tab2.setContent( StockAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		tab2.setContent( StockAc );
		tabHost.addTab( tab1 );
		tabHost.addTab( tab2 );
	}
}
