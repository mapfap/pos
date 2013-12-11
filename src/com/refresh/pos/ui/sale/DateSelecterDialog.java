//package com.refresh.pos.ui.sale;
//
//import java.util.Calendar;
//
//import android.annotation.SuppressLint;
//import android.support.v4.app.DialogFragment;
//import android.widget.Button;
//
//public class DateSelecterDialog extends DialogFragment 
//{
//
//	private Button btnSelectDate,btnSelectTime;
//
//	static final int DATE_DIALOG_ID = 0;
//	static final int TIME_DIALOG_ID=1;
//
//	// declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
//	private int mYear, mMonth, mDay,mHour,mMinute; 
//
//	// constructor
//	public DateSelecterDialog(Calendar c)
//	{
//		c = Calendar.getInstance();
//		mYear = c.get(Calendar.YEAR);
//		mMonth = c.get(Calendar.MONTH);
//		mDay = c.get(Calendar.DAY_OF_MONTH);
//		mHour = c.get(Calendar.HOUR_OF_DAY);
//		mMinute = c.get(Calendar.MINUTE);
//	}
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) 
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.);
//
//		// get the references of buttons
//		btnSelectDate=(Button)findViewById(R.id.buttonSelectDate);
//		btnSelectTime=(Button)findViewById(R.id.buttonSelectTime);
//
//		// Set ClickListener on btnSelectDate 
//		btnSelectDate.setOnCl ickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//				// Show the DatePickerDialog
//				showDialog(DATE_DIALOG_ID);
//			}
//		});
//
//		// Set ClickListener on btnSelectTime
//		btnSelectTime.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//				// Show the TimePickerDialog
//				showDialog(TIME_DIALOG_ID);
//			}
//		});
//
//	}
//
//
//	// Register  DatePickerDialog listener
//	private DatePickerDialog.OnDateSetListener mDateSetListener =
//			new DatePickerDialog.OnDateSetListener() {
//		// the callback received when the user "sets" the Date in the DatePickerDialog
//		public void onDateSet(DatePicker view, int yearSelected,
//				int monthOfYear, int dayOfMonth) {
//			year = yearSelected;
//			month = monthOfYear;
//			day = dayOfMonth;
//			// Set the Selected Date in Select date Button
//			btnSelectDate.setText("Date selected : "+day+"-"+month+"-"+year);
//		}
//	};
//
//	// Register  TimePickerDialog listener                 
//	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
//			new TimePickerDialog.OnTimeSetListener() {
//		// the callback received when the user "sets" the TimePickerDialog in the dialog
//		public void onTimeSet(TimePicker view, int hourOfDay, int min) {
//			hour = hourOfDay;
//			minute = min;
//			// Set the Selected Date in Select date Button
//			btnSelectTime.setText("Time selected :"+hour+"-"+minute);
//		}
//	};
//
//
//	// Method automatically gets Called when you call showDialog()  method
//	@Override
//	protected Dialog onCreateDialog(int id) {
//		switch (id) {
//		case DATE_DIALOG_ID:
//			// create a new DatePickerDialog with values you want to show 
//			return new DatePickerDialog(this,
//					mDateSetListener,
//					mYear, mMonth, mDay);
//			// create a new TimePickerDialog with values you want to show 
//		case TIME_DIALOG_ID:
//			return new TimePickerDialog(this,
//					mTimeSetListener, mHour, mMinute, false);
//
//		}
//		return null;
//	}
//
//}