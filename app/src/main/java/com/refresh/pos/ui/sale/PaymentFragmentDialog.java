package com.refresh.pos.ui.sale;

import com.refresh.pos.R;
import com.refresh.pos.ui.component.UpdatableFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A dialog for input a money for sale.
 * @author Refresh Team
 *
 */
@SuppressLint("ValidFragment")
public class PaymentFragmentDialog extends DialogFragment {
	
	private TextView totalPrice;
	private EditText input;
	private Button clearButton;
	private Button confirmButton;
	private String strtext;
	private UpdatableFragment saleFragment;
	private UpdatableFragment reportFragment;
	
	/**
	 * Construct a new PaymentFragmentDialog.
	 * @param saleFragment
	 * @param reportFragment
	 */
	public PaymentFragmentDialog(UpdatableFragment saleFragment, UpdatableFragment reportFragment) {
		super();
		this.saleFragment = saleFragment;
		this.reportFragment = reportFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dialog_payment, container,false);
		strtext=getArguments().getString("edttext");
		input = (EditText) v.findViewById(R.id.dialog_saleInput);
		totalPrice = (TextView) v.findViewById(R.id.payment_total);
		totalPrice.setText(strtext);
		clearButton = (Button) v.findViewById(R.id.clearButton);
		clearButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				end();
			}
		});
		
		confirmButton = (Button) v.findViewById(R.id.confirmButton);
		confirmButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String inputString = input.getText().toString();
				
				if (inputString.equals("")) {
					Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.please_input_all), Toast.LENGTH_SHORT).show();
					return;
				}
				double a = Double.parseDouble(strtext);
				double b = Double.parseDouble(inputString);
				if (b < a) {
					Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.need_money) + " " + (b - a), Toast.LENGTH_SHORT).show();
				} else {
					Bundle bundle = new Bundle();
					bundle.putString("edttext", b - a + "");
					EndPaymentFragmentDialog newFragment = new EndPaymentFragmentDialog(
							saleFragment, reportFragment);
					newFragment.setArguments(bundle);
					newFragment.show(getFragmentManager(), "");
					end();
				}

			}
		});
		return v;
	}

	/**
	 * End.
	 */
	private void end() {
		this.dismiss();
		
	}
	

}
