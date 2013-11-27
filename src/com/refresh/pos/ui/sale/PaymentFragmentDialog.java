package com.refresh.pos.ui.sale;

import com.refresh.pos.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PaymentFragmentDialog extends DialogFragment {
	
	private TextView totalPrice;
	private Button clearButton;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dialog_payment, container,false);
		String strtext=getArguments().getString("edttext");
		totalPrice = (TextView) v.findViewById(R.id.payment_total);
		totalPrice.setText(strtext);
		clearButton = (Button) v.findViewById(R.id.clearButton);
		clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 
				
			}
		});
		return v;
	}
	

}
