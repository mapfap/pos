package com.refresh.pos.ui.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.refresh.pos.R;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.inventory.Inventory;
import com.refresh.pos.domain.inventory.LineItem;
import com.refresh.pos.domain.inventory.ProductCatalog;
import com.refresh.pos.domain.sale.Register;
import com.refresh.pos.domain.sale.Sale;
import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.ui.MainActivity;

public class SaleFragment extends Fragment implements Observer {
    
	private ListView lineItemListView;
	private Sale currentSale;
	private Register register;
	private ArrayList<Map<String, String>> saleList;
	private ListView saleListView;
	private Button clearButton;
	private TextView totalPrice;
	private Button endButton;
	private ProductCatalog productCatalog;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		try {
			register = Register.getInstance();
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		currentSale = register.initiateSale(DateTimeStrategy.getCurrentTime());

		View view = inflater.inflate(R.layout.layout_sale, container, false);
		
		saleListView = (ListView) view.findViewById(R.id.sale_List);
		totalPrice = (TextView) view.findViewById(R.id.totalPrice);
		clearButton = (Button) view.findViewById(R.id.clearButton);
		endButton = (Button) view.findViewById(R.id.endButton);
		
		initUI();
		return view;
	}

	private void initUI() {
		
//		final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
//		final LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
//		final AlertDialog.Builder adb = new AlertDialog.Builder(this);

		clearButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewPager viewPager = ((MainActivity) getActivity()).getViewPager();
				viewPager.setCurrentItem(1);
			}
		});

		endButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				final View Viewlayout = inflater.inflate(R.layout.dialog_payment,
//						(ViewGroup) findViewById(R.id.layout_paymentDialog)); 
//				
//				popDialog.setView(Viewlayout);
//				TextView total =  (TextView) Viewlayout.findViewById(R.id.payment_total);	
//				final EditText inputPayment = (EditText)Viewlayout.findViewById(R.id.dialog_saleInput);	
//				total.setText(totalPrice.getText());
//				popDialog.setNegativeButton("Cancel", null);
//				popDialog.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
//					public void onClick(DialogInterface dialog, int arg1) {
//						if(inputPayment.getText().equals("")){
//							Log.v("Payment ","empty");
//							Toast.makeText(getActivity().getBaseContext(),"HAVE SOME BLANK PLZ FILL UP",Toast.LENGTH_SHORT).show();
//						}
//						else if(tryParseDouble(inputPayment.getText().toString())){
//							
//							double input = Double.parseDouble(inputPayment.getText().toString());
//							double totalP = Double.parseDouble(totalPrice.getText().toString());
//							if(input>=totalP){
//								Log.v("Payment ","pass");
//								register.endSale(DateTimeStrategy.getCurrentTime());
//								
//							}
//							else{
//								Log.v("Payment ",">");
//								Toast.makeText(getActivity().getBaseContext(),"Not Enough Money!",Toast.LENGTH_SHORT).show();
//							}
//						}
//						else{
//							
//						}
//						adb.setTitle("Payment Success");
//						adb.setMessage("Plese Confirm");
//					}
//				});
				
				

//				popDialog.create();
//				popDialog.show();
				register.endSale(DateTimeStrategy.getCurrentTime());
				updateData();
				
			}
		});
		
		clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				register.cancleSale();
				updateData();
			}
		});
		
		
	}

	
	private void showList(List<LineItem> list) {
		
		saleList = new ArrayList<Map<String, String>>();
		for(LineItem line : list) {
			saleList.add(line.toMap());
		}
		
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(getActivity().getBaseContext(), saleList,
				R.layout.listview_sale, new String[]{"name","quantity","price"}, new int[] {R.id.name,R.id.quantity,R.id.price});
		saleListView.setAdapter(sAdap);
	}

	public boolean tryParseDouble(String value)  
	{  
	     try  
	     {  
	         Double.parseDouble(value);  
	         return true;  
	      } catch(NumberFormatException nfe)  
	      {  
	          return false;  
	      }  
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			int id = (Integer) arg1;
//			register.addItem(productCatalog.getProductById(id), 1);
			Log.d("sale", "data = " + arg1);
			updateData();
		} catch (Exception e) {			
//			e.printStackTrace();
			Log.d("sale", "invalid data = " + arg1);
		}
	}
	
	
	
	private void updateData() {
		showList(register.getCurrentSale().getAllLineItem());
		totalPrice.setText(register.getTotal() + "");
	}

	@Override
	public void onResume() {
		super.onResume();
		updateData();
	}

}
