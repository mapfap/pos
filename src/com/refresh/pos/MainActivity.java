package com.refresh.pos;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private POSDatabase database;
	private ItemFactory itemFactory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		itemFactory = ItemFactory.getInstance();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		database = new POSDatabase(this);
		database.getWritableDatabase();
		
		
        final Button insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//            	Item item = itemFactory.createItem(database.nextID(),"Apple", "885100422323222", 10.50);
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
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
