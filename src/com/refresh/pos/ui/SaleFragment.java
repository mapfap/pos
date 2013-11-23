package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.LineItem;
import com.refresh.pos.domain.Register;
import com.refresh.pos.domain.Sale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SaleFragment extends Fragment {
    
	private ListView lineItemListView;
	private Sale currentSale;
	private Register register;
	private ArrayList<Map<String, String>> saleList;
	private ListView saleListView;
	private ImageButton addButton;
	private TextView totalPrice;
	private ImageButton payButton;
	private ImageButton clearButton;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		try {
			register = Register.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		currentSale = register.initiateSale(DateTimeStrategy.getCurrentTime());

		View view = inflater.inflate(R.layout.layout_sale, container, false);
		
		saleListView = (ListView) view.findViewById(R.id.sale_List);
		totalPrice = (TextView) view.findViewById(R.id.totalPrice);
		addButton = (ImageButton) view.findViewById(R.id.sale_addButton);
		payButton = (ImageButton) view.findViewById(R.id.payButton);
		clearButton = (ImageButton) view.findViewById(R.id.sale_clearButton);
		
		initUI();
		return view;
	}

	private void initUI() {
		
//		final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
//		final LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
//		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
//		
//		totalPrice.setText("0.00");
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewPager viewPager = ((MainActivity) getActivity()).getViewPager();
				viewPager.setCurrentItem(1);
			}
		});
//		
//		payButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
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
//				
//				
//
//				popDialog.create();
//				popDialog.show();
//				register.endSale(DateTimeStrategy.getCurrentTime()).toString());
//				SaleActivity.this.finish();
//				
//				Intent intent = new Intent(SaleActivity.this, HomeActivity.class);
//			    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			    startActivity(intent);
				
//			}
//		});
		
//		clearButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				register.clear();
//				showList(currentSale.getAllLineItem());
//			}
//		});
		
		
	}
	
//	@Override
//	public void onResume() {
//		super.onResume();
//		Log.d("SALE", "I'm back!!!");
//		showList(currentSale.getAllLineItem());
//		totalPrice.setText(currentSale.getTotal()+"");
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
	
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

}
