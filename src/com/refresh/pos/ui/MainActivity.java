package com.refresh.pos.ui;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
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

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    Map<String, Announcer> announcers;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initiateCoreApp();
        
        announcers = new HashMap<String, Announcer>();
        announcers.put("Product Detail", new Announcer());
        announcers.put("Sale", new Announcer());
        viewPager= (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(fragmentManager, announcers));
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
            case 0:
                    return new InventoryFragment();
            case 1:
                    SaleFragment saleFragment = new SaleFragment();
                    announcers.get("Sale").addObserver(saleFragment);
                    return saleFragment;
            case 2:
                    return new ReportFragment();
            default:
                    return new InventoryFragment();
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