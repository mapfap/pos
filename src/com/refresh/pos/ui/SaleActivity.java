package com.refresh.pos.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.Inventory;
import com.refresh.pos.domain.LineItem;
import com.refresh.pos.domain.Register;
import com.refresh.pos.domain.Sale;

public class SaleActivity extends Activity  {

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
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Log.d("SALE", "I'm created!!");
		
		try {
			register = Register.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		currentSale = register.initiateSale(DateTimeStrategy.getCurrentTime());
		
		initUI(savedInstanceState);
	}

	private void initUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_sale);
		saleListView = (ListView) findViewById(R.id.sale_List);
		totalPrice = (TextView) findViewById(R.id.totalPrice);
		addButton = (ImageButton) findViewById(R.id.sale_addButton);
		payButton = (ImageButton) findViewById(R.id.payButton);
		clearButton = (ImageButton) findViewById(R.id.sale_clearButton);
		
		final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
		final LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		
		totalPrice.setText("0.00");
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
				final View Viewlayout = inflater.inflate(R.layout.dialog_payment,
						(ViewGroup) findViewById(R.id.layout_paymentDialog)); 
				
				popDialog.setView(Viewlayout);
				TextView total =  (TextView) Viewlayout.findViewById(R.id.payment_total);	
				final EditText inputPayment = (EditText)Viewlayout.findViewById(R.id.dialog_saleInput);	
				total.setText(totalPrice.getText());
//				total.setText("1235");
				popDialog.setNegativeButton("Cancel", null);
				popDialog.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int arg1) {
						if(inputPayment.getText().equals("")){
							Log.v("Payment ","empty");
							Toast.makeText(SaleActivity.this,"HAVE SOME BLANK PLZ FILL UP",Toast.LENGTH_SHORT).show();
						}
						else if(tryParseDouble(inputPayment.getText().toString())){
							
							double input = Double.parseDouble(inputPayment.getText().toString());
							double totalP = Double.parseDouble(totalPrice.getText().toString());
							if(input>=totalP){
								Log.v("Payment ","pass");
								register.endSale(DateTimeStrategy.getCurrentTime());
								
							}
							else{
								Log.v("Payment ",">");
								Toast.makeText(SaleActivity.this,"Not Enough Money!",Toast.LENGTH_SHORT).show();
							}
						}
						else{
							
						}
						adb.setTitle("Payment Success");
						adb.setMessage("Plese Confirm");
					}
				});
				
				

				popDialog.create();
				popDialog.show();
//				register.endSale(DateTimeStrategy.getCurrentTime()).toString());
//				SaleActivity.this.finish();
//				
//				Intent intent = new Intent(SaleActivity.this, HomeActivity.class);
//			    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			    startActivity(intent);
				
			}
		});
		
		clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				register.clear();
				showList(currentSale.getAllLineItem());
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
