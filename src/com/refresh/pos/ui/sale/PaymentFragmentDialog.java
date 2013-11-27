package com.refresh.pos.ui.sale;

import com.refresh.pos.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PaymentFragmentDialog extends DialogFragment {

	int totalPrice;
//	public PaymentFragmentDialog(int totalPrice){
//		this.totalPrice = totalPrice;
//	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dialog_payment, container,false);
		
		return v;
	}
	

}
