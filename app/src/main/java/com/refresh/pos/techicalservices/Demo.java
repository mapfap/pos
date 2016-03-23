package com.refresh.pos.techicalservices;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

import com.refresh.pos.R;
import com.refresh.pos.domain.inventory.Inventory;
import com.refresh.pos.domain.inventory.ProductCatalog;

/**
 * Reads a demo products from CSV in res/raw/
 * 
 * @author Refresh Team
 *
 */
public class Demo {

	/**
	 * Adds the demo product to inventory.
	 * @param context The current stage of the application.
	 */
	public static void testProduct(Context context) {
        InputStream instream = context.getResources().openRawResource(R.raw.products);
		BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
		String line;
		try {
			ProductCatalog catalog = Inventory.getInstance().getProductCatalog();
			while (( line = reader.readLine()) != null ) {
				String[] contents = line.split(",");
				Log.d("Demo", contents[0] + ":" + contents[1] + ": " + contents[2]);
				catalog.addProduct(contents[1], contents[0], Double.parseDouble(contents[2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
