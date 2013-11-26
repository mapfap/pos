package com.refresh.pos.ui;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;

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

public class MainActivity extends FragmentActivity implements TabListener{

    private ViewPager viewPager;
    private ActionBar actionBar;
//    private Announcer productDetailAnnouncer;
//    private Announcer saleAnnouncer;
    Map<String, Announcer> announcers;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText("Inventory");
        tab1.setTabListener(this);
        ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText("Sale");
        tab2.setTabListener(this);
        ActionBar.Tab tab3 = actionBar.newTab();
        tab3.setText("Report");
        tab3.setTabListener(this);
        
        initiateCoreApp();
        
        announcers = new HashMap<String, Announcer>();
        announcers.put("Product Detail", new Announcer());
        announcers.put("Sale", new Announcer());
        viewPager= (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        //viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(), announcers));
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        viewPager.setCurrentItem(1);
    }
    
    public void suspendSaleOnClickHandler(View view) {
    	Log.d("main + ledger","remove button clicked!");
    }
    
	public void optionOnClickHandler(View view) {
		viewPager.setCurrentItem(0);
		String id = view.getTag().toString();
		Intent newActivity = new Intent(MainActivity.this, ProductDetailActivity.class);
        newActivity.putExtra("id", id);
        startActivity(newActivity);  
//		ContentManager.put("id", id);
//		announcers.get("Product Detail").announce(Integer.parseInt(id));
//		Log.d("inventory", "tag = " + view.getTag());
	}
    
    public ViewPager getViewPager() {
    	return viewPager;
    }
    
    public Map<String, Announcer> getAnnouncers() {
    	return announcers;
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

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}

class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter
{

	private Map<String, Announcer> announcers;
    public MyFragmentStatePagerAdapter(FragmentManager fragmentManager, Map<String, Announcer> announcers) {
        super(fragmentManager);
        this.announcers = announcers;
    }

    @Override
    public Fragment getItem(int i) {
    	switch(i) {
//    	case 0:
//    		ProductDetailFragment productDetailFragment = new ProductDetailFragment();
//    		announcers.get("Product Detail").addObserver(productDetailFragment);
//    		return productDetailFragment;
    	case 0:
    		return new InventoryFragment();
    	case 1:
    		SaleFragment saleFragment = new SaleFragment();
    		announcers.get("Sale").addObserver(saleFragment);
    		return saleFragment;
    	case 2:
    		return new ReportFragment();
    	default:
    		return new ProductDetailFragment();
    	}
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int i) {
    	switch(i) {
    	case 0:
    		return "Inventory";
    	case 1:
    		return "Sale";
    	case 2:
    		return "Report";
    	default:
    		return "";
    	}
    }
    
}