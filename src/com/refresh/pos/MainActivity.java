package com.refresh.pos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TextView formatTxt;
	private TextView contentTxt;
	private Activity activity;
	private InventoryController inventoryController;
	
//	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//
//		if (scanningResult != null) {
//			String scanContent = scanningResult.getContents();
//			String scanFormat = scanningResult.getFormatName();
//			formatTxt.setText("FORMAT: " + scanFormat);
//			contentTxt.setText("CONTENT: " + scanContent);
//			
//		} else{
//		    Toast toast = Toast.makeText(getApplicationContext(),
//		            "No scan data received!", Toast.LENGTH_SHORT);
//		        toast.show();
//		    }
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Database database = new POSSQLiteDatabase(this);
		inventoryController = new InventoryController(database);
		//inventory = new Inventory(database);
		
		activity = this;
		formatTxt = (TextView)findViewById(R.id.scanFormat);
		contentTxt = (TextView)findViewById(R.id.scanContent);
		
		//itemFactory = ItemFactory.getInstance();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
        final Button insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
	          //	Item item = itemFactory.createItem("Apple", "885100422323222", 10.50);
	          //	boolean success = inventory.addNewItem(item);
            	
            		boolean success = inventoryController.add();
	            	
	            	if(success) {
	            	 Toast.makeText(MainActivity.this,"Insert data successfully", Toast.LENGTH_SHORT).show(); 
	            	} else {
	               	 Toast.makeText(MainActivity.this,"Failed to insert data", Toast.LENGTH_SHORT).show(); 
	            	}
	          
            }
        });
        

//       final Button selectAllButton = (Button) findViewById(R.id.selectAllButton);
//       selectAllButton.setOnClickListener(new View.OnClickListener() {
//           public void onClick(View v) {
//           	
//           	List<Item> itemList = inventory.getAllItem();   
//           	if(itemList == null) {
//           		Toast.makeText(MainActivity.this,"Not found Data!", Toast.LENGTH_SHORT).show(); 
//           	}
//           	else {
//           		String all= "";
//           		for (Item item : itemList) {
//                	 all+="id : " + item.name + " || "  + item.price + "$, "  + item.barcode + "\n";
//           		}
//           		Toast.makeText(MainActivity.this, all, Toast.LENGTH_SHORT).show(); 
//           	}
//           
//           }
//       }); 
       
//       final Button scanButton = (Button) findViewById(R.id.scanButton);
//       scanButton.setOnClickListener(new View.OnClickListener() {
//	    	   public void onClick(View v) {
//	    		   IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
//	    		   scanIntegrator.initiateScan();
//	    	   }
//       }); 

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
