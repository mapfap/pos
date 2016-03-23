package com.refresh.pos.ui;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.refresh.pos.R;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.LanguageController;
import com.refresh.pos.domain.inventory.Inventory;
import com.refresh.pos.domain.sale.Register;
import com.refresh.pos.domain.sale.SaleLedger;
import com.refresh.pos.techicalservices.AndroidDatabase;
import com.refresh.pos.techicalservices.Database;
import com.refresh.pos.techicalservices.DatabaseExecutor;
import com.refresh.pos.techicalservices.inventory.InventoryDao;
import com.refresh.pos.techicalservices.inventory.InventoryDaoAndroid;
import com.refresh.pos.techicalservices.sale.SaleDao;
import com.refresh.pos.techicalservices.sale.SaleDaoAndroid;

/**
 * This is the first activity page, core-app and database created here.
 * Dependency injection happens here.
 * 
 * @author Refresh Team
 * 
 */
public class SplashScreenActivity extends Activity {

	public static final String POS_VERSION = "Mobile POS 0.8";
	private static final long SPLASH_TIMEOUT = 2000;
	private Button goButton;
	private boolean gone;
	
	/**
	 * Loads database and DAO.
	 */
	private void initiateCoreApp() {
		Database database = new AndroidDatabase(this);
		InventoryDao inventoryDao = new InventoryDaoAndroid(database);
		SaleDao saleDao = new SaleDaoAndroid(database);
		DatabaseExecutor.setDatabase(database);
		LanguageController.setDatabase(database);

		Inventory.setInventoryDao(inventoryDao);
		Register.setSaleDao(saleDao);
		SaleLedger.setSaleDao(saleDao);
		
		DateTimeStrategy.setLocale("th", "TH");
		setLanguage(LanguageController.getInstance().getLanguage());

		Log.d("Core App", "INITIATE");
	}
	
	/**
	 * Set language.
	 * @param localeString
	 */
	private void setLanguage(String localeString) {
		Locale locale = new Locale(localeString);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		initiateUI(savedInstanceState);
		initiateCoreApp();
	}
	
	/**
	 * Go.
	 */
	private void go() {
		gone = true;
		Intent newActivity = new Intent(SplashScreenActivity.this,
				MainActivity.class);
		startActivity(newActivity);
		SplashScreenActivity.this.finish();	
	}

	/**
	 * Initiate this UI.
	 * @param savedInstanceState
	 */
	private void initiateUI(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splashscreen);
		goButton = (Button) findViewById(R.id.goButton);
		goButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				go();
			}

		});
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (!gone) go();
			}
		}, SPLASH_TIMEOUT);
	}

}