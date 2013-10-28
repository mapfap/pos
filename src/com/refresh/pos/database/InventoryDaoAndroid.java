package com.refresh.pos.database;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductLot;

public class InventoryDaoAndroid implements InventoryDao {


	public InventoryDaoAndroid(AndroidDatabase androidDatabase) {
		
	}

	@Override
	public long addProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long addProductLot(ProductLot productLot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductByBarcode(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductLot> getAllProductLot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}


}
