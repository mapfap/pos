package com.refresh.pos;

import java.util.List;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private POSDatabase database;
	private ItemFactory itemFactory;
	private TextView formatTxt;
	private TextView contentTxt;
	private Activity activity;

	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();
			formatTxt.setText("FORMAT: " + scanFormat);
			contentTxt.setText("CONTENT: " + scanContent);
			
		} else{
		    Toast toast = Toast.makeText(getApplicationContext(),
		            "No scan data received!", Toast.LENGTH_SHORT);
		        toast.show();
		    }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		activity = this;
		formatTxt = (TextView)findViewById(R.id.scanFormat);
		contentTxt = (TextView)findViewById(R.id.scanContent);
		
		itemFactory = ItemFactory.getInstance();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		database = new POSDatabase(this);
		database.getWritableDatabase();
		
		
        final Button insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
	//          Item item = itemFactory.createItem(database.nextID(),"Apple", "885100422323222", 10.50);
	            	Item item = itemFactory.createItem("Apple", "885100422323222", 10.50);
	            	long flg1 = database.addItem(item);
	            	
	            	if(flg1 > 0) {
	            	 Toast.makeText(MainActivity.this,"Insert data successfully", Toast.LENGTH_LONG).show(); 
	            	} else {
	               	 Toast.makeText(MainActivity.this,"Failed to insert data", Toast.LENGTH_LONG).show(); 
	            	}
	          
            }
        });
        

       final Button selectAllButton = (Button) findViewById(R.id.selectAllButton);
       selectAllButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
           	
           	List<Item> itemList = database.SelectAllData();   
           	if(itemList == null) {
           		Toast.makeText(MainActivity.this,"Not found Data!", Toast.LENGTH_LONG).show(); 
           	}
           	else {
           		for (Item item : itemList) {
                	 Toast.makeText(MainActivity.this,"id : " + item.name + " - "  + item.price + "$, "  + item.barcode, Toast.LENGTH_LONG).show(); 
           		}
           	}
           
           }
       }); 
       
       final Button scanButton = (Button) findViewById(R.id.scanButton);
       scanButton.setOnClickListener(new View.OnClickListener() {
	    	   public void onClick(View v) {
	    		   IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
	    		   scanIntegrator.initiateScan();
	    	   }
       }); 

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
