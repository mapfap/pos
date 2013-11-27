package com.refresh.pos.ui.sale;

import com.refresh.pos.R;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.sale.Register;
import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.ui.Announcer;
import com.refresh.pos.ui.MainActivity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EndPaymentFragmentDialog extends DialogFragment  {

	private Button doneButton;
	private TextView chg;
	private Register regis;
	private MainActivity main;
	private Announcer announcer;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		try {
			regis = Register.getInstance();
		} catch (NoDaoSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		main = (MainActivity) getActivity();
		announcer = main.getAnnouncers().get("Sale");
		View v = inflater.inflate(R.layout.dialog_paymentsuccession, container,false);
		String strtext=getArguments().getString("edttext");
		chg = (TextView) v.findViewById(R.id.changeTxt);
		chg.setText(strtext);
		doneButton = (Button) v.findViewById(R.id.doneButton);
		doneButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				end();
				
			}
		});
		
		return v;
	}
	private void end(){
		regis.endSale(DateTimeStrategy.getCurrentTime());
		announcer.announce("");
		this.dismiss();
	}

}
