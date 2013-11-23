package com.refresh.pos.ui;

import com.refresh.pos.R;
import com.refresh.pos.database.AndroidDatabase;
import com.refresh.pos.database.Database;
import com.refresh.pos.database.InventoryDao;
import com.refresh.pos.database.InventoryDaoAndroid;
import com.refresh.pos.database.SaleDao;
import com.refresh.pos.database.SaleDaoAndroid;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.Inventory;
import com.refresh.pos.domain.Register;
import com.refresh.pos.domain.SaleLedger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private Announcer announcer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initiateCoreApp();
        
        announcer = new Announcer();
        viewPager= (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(fragmentManager, announcer));
        viewPager.setCurrentItem(1);
    }
    
    public ViewPager getViewPager() {
    	return viewPager;
    }
    
    public Announcer getAnnouncer() {
    	return announcer;
    }
    
	/**
	 * Loads database and DAO.
	 */
	private void initiateCoreApp() {
		Database database = new AndroidDatabase(this);
		InventoryDao inventoryDao = new InventoryDaoAndroid(database);
		SaleDao saleDao = new SaleDaoAndroid(database);
		
		Inventory.setInventoryDao(inventoryDao);
		Register.setSaleDao(saleDao);
		SaleLedger.setSaleDao(saleDao);
		
		DateTimeStrategy.setLocale("th", "TH");
		
		Log.d("Core App", "INITIATE");
	}

}

class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter
{

	private Announcer announcer;
    public MyFragmentStatePagerAdapter(FragmentManager fragmentManager, Announcer announcer) {
        super(fragmentManager);
        this.announcer = announcer;
    }

    @Override
    public Fragment getItem(int i) {
    	switch(i) {
    	case 0:
    		ProductDetailFragment fragment = new ProductDetailFragment();
    		announcer.addObserver(fragment);
    		return fragment;
    	case 1:
    		return new InventoryFragment();
    	case 2:
    		return new SaleFragment();
    	case 3:
    		return new ReportFragment();
    	default:
    		return new ProductDetailFragment();
    	}
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int i) {
    	switch(i) {
    	case 0:
    		return "Product's Detail";
    	case 1:
    		return "Inventory";
    	case 2:
    		return "Sale";
    	case 3:
    		return "Report";
    	default:
    		return "";
    	}
    }
}
