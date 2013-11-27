package com.refresh.pos.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateCoreApp();
        
        viewPager= (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(fragmentManager));
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