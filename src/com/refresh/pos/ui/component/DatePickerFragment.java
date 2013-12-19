package com.refresh.pos.ui.component;

import java.util.Calendar;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	private Calendar calendar;
	private UpdatableFragment fragment;

	public DatePickerFragment(Calendar calendar, UpdatableFragment fragment) {
		super();
		this.calendar = calendar;
		this.fragment = fragment;
	}

	@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH);
	        int day = calendar.get(Calendar.DAY_OF_MONTH);

	        return new DatePickerDialog(getActivity(), this, year, month, day);
	    }

		@Override
		public void onDateSet(DatePicker datepicker, int year, int month, int day) {
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			Log.d("cal", calendar + "");
			fragment.update();
		}
		
		
		
}
