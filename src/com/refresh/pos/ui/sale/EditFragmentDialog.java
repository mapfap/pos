package com.refresh.pos.ui.sale;

import com.refresh.pos.R;
import com.refresh.pos.domain.sale.Register;
import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.ui.component.UpdatableFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class EditFragmentDialog extends DialogFragment {
	private Register register;
	private UpdatableFragment saleFragment;
	private UpdatableFragment reportFragment;
	private EditText quantity;
	private EditText price;
	private Button comfirm;
	private String sale_id;
	private String product_id;
	private String position;
	public EditFragmentDialog(UpdatableFragment saleFragment, UpdatableFragment reportFragment) {
		super();
		this.saleFragment = saleFragment;
		this.reportFragment = reportFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dialog_saleedit, container,false);
		try {
			register = Register.getInstance();
		} catch (NoDaoSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sale_id = getArguments().getString("sale_id");
		product_id = getArguments().getString("product_id");
		position = getArguments().getString("position");
		
		quantity = (EditText)v.findViewById(R.id.quantityBox);
		quantity.setText(register.getCurrentSale().getLineItem(Integer.parseInt(position)).getQuantity()+"");
		
		price = (EditText)v.findViewById(R.id.priceBox);
		price.setText(register.getCurrentSale().getLineItem(Integer.parseInt(position)).getProduct().getUnitPrice()+"");
		
		comfirm = (Button)v.findViewById(R.id.confirmButton);
		comfirm.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				register.edit(Integer.parseInt(sale_id),Integer.parseInt(product_id), Integer.parseInt(quantity.getText().toString()), Double.parseDouble(price.getText().toString()));
				end();
			}
			
		});
		
		
		return v;
	}
	private void end(){
		saleFragment.update();
		reportFragment.update();
		this.dismiss();
	}
	
	
}
