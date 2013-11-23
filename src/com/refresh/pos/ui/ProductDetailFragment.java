package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.Inventory;
import com.refresh.pos.domain.Product;
import com.refresh.pos.domain.ProductCatalog;
import com.refresh.pos.domain.ProductLot;
import com.refresh.pos.domain.Stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;


public class ProductDetailFragment extends Fragment {

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
	private TextView quantityBox;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		try {
			stock = Inventory.getInstance().getStock();
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		View view = inflater.inflate(R.layout.layout_productdetail, container, false);
		
		stockListView = (ListView) view.findViewById(R.id.stockListView);
		nameBox = (TextView) view.findViewById(R.id.nameBox);
		priceBox = (TextView) view.findViewById(R.id.priceBox);
		barcodeBox = (TextView) view.findViewById(R.id.barcodeBox);
		quantityBox = (TextView) view.findViewById(R.id.quantityBox);
		
		addProductLotButton = (ImageButton) view.findViewById(R.id.addProductLotButton);
		mTabHost = (TabHost) view.findViewById(android.R.id.tabhost);
		mTabHost.setup();
		mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Detail").setContent(R.id.tab1));
		mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Stock").setContent(R.id.tab2));
		mTabHost.setCurrentTab(0);
		
		initUI();
		
		showProductDetail(2);
		return view;
	}

	private void initUI() {
		addProductLotButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(getActivity().getBaseContext(), AddProductLotActivity.class);
				newActivity.putExtra("id", product.getId()+"");
				startActivity(newActivity);
			}
		});
	}
	
	public void showProductDetail(int id) {
		
		product = productCatalog.getProductById(id);
		
		nameBox.setText(product.getName());
		priceBox.setText(product.getUnitPrice()+"");
		barcodeBox.setText(product.getBarcode());

		showList(stock.getProductLotByProductId(id));
	}
	
	private void showList(List<ProductLot> list) {
		stockList = new ArrayList<Map<String, String>>();
		for(ProductLot productLot : list) {
			stockList.add(productLot.toMap());
		}
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(getActivity().getBaseContext(), stockList,
				R.layout.listview_productstock, new String[]{"dateAdded","cost","quantity"}, new int[] {R.id.dateAdded,R.id.cost,R.id.quantity});
		stockListView.setAdapter(sAdap);
	}
}
