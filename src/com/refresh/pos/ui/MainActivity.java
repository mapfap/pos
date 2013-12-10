package com.refresh.pos.ui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.refresh.pos.R;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.inventory.Inventory;
import com.refresh.pos.domain.sale.Register;
import com.refresh.pos.domain.sale.SaleLedger;
import com.refresh.pos.techicalservices.AndroidDatabase;
import com.refresh.pos.techicalservices.Database;
import com.refresh.pos.techicalservices.inventory.InventoryDao;
import com.refresh.pos.techicalservices.inventory.InventoryDaoAndroid;
import com.refresh.pos.techicalservices.sale.SaleDao;
import com.refresh.pos.techicalservices.sale.SaleDaoAndroid;
import com.refresh.pos.ui.component.UpdatableFragment;
import com.refresh.pos.ui.inventory.InventoryFragment;
import com.refresh.pos.ui.inventory.ProductDetailActivity;
import com.refresh.pos.ui.sale.ReportFragment;
import com.refresh.pos.ui.sale.SaleFragment;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.pager);
        
        ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				
			}

        };
        
        
        actionBar.addTab(
                actionBar.newTab()
                        .setText("Inventory")
                        .setTabListener(tabListener),0,false);
        actionBar.addTab(
                actionBar.newTab()
                        .setText("Sale")
                        .setTabListener(tabListener),1,true);
        actionBar.addTab(
                actionBar.newTab()
                        .setText("Report")
                        .setTabListener(tabListener),2,false);
        
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#73bde5")));
        
        super.onCreate(savedInstanceState);
        initiateCoreApp();
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(fragmentManager));
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
        viewPager.setCurrentItem(1);
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			openQuitDialog();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	private void openQuitDialog(){
		  AlertDialog.Builder quitDialog 
		   = new AlertDialog.Builder(MainActivity.this);
		  quitDialog.setTitle("Are you sure you want to quit?");
		  
		  quitDialog.setPositiveButton("QUIT", new OnClickListener(){

		   @Override
		   public void onClick(DialogInterface dialog, int which) {
		    // TODO Auto-generated method stub
		    finish();
		   }});
		  
		  quitDialog.setNegativeButton("NO", new OnClickListener(){

		   @Override
		   public void onClick(DialogInterface dialog, int which) {
		    // TODO Auto-generated method stub
		    
		   }});
		  
		  quitDialog.show();
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
    }

    public ViewPager getViewPager() {
    	return viewPager;
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
	
	private UpdatableFragment[] fragments;
	private String[] fragmentNames;

    public MyFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        
        UpdatableFragment reportFragment = new ReportFragment();
        UpdatableFragment saleFragment = new SaleFragment(reportFragment);
        UpdatableFragment inventoryFragment = new InventoryFragment(saleFragment);
        
        fragments = new UpdatableFragment[]{ inventoryFragment, saleFragment, reportFragment};
        fragmentNames = new String[]{"Inventory", "Sale", "Report"};
        
    }

    @Override
    public Fragment getItem(int i) {
    	return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return fragmentNames[i];
    }
    
}