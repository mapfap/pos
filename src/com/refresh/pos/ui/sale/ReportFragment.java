package com.refresh.pos.ui.sale;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.refresh.pos.R;
import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.sale.Sale;
import com.refresh.pos.domain.sale.SaleLedger;
import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.ui.component.DatePickerFragment;
import com.refresh.pos.ui.component.UpdatableFragment;


public class ReportFragment extends UpdatableFragment {
	
	private SaleLedger saleLedger;
	List<Map<String, String>> saleList;
	private ListView saleLedgerListView;
//	private EditText searchBox;
	private TextView totalBox;
	private Spinner spinner;
	private Button previousButton;
	private Button nextButton;
	private TextView currentBox;
	private Calendar currentTime;
	private DatePickerFragment datePicker;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		try {
			saleLedger = SaleLedger.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		View view = inflater.inflate(R.layout.layout_report, container, false);
		
		previousButton = (Button) view.findViewById(R.id.previousButton);
		nextButton = (Button) view.findViewById(R.id.nextButton);
		currentBox = (TextView) view.findViewById(R.id.currentBox);
		saleLedgerListView = (ListView) view.findViewById(R.id.saleListView);
//		searchBox = (EditText) view.findViewById(R.id.searchBox);
		totalBox = (TextView) view.findViewById(R.id.totalBox);
		spinner = (Spinner) view.findViewById(R.id.spinner1);
		
		initUI();
		return view;
	}

	private void initUI() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
		        R.array.period, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner.setAdapter(adapter);
		spinner.setSelection(0);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {	
				update();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
			
		});
		
		currentBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				datePicker.show(getActivity().getSupportFragmentManager(), "Select Date");
			}
		});
		
		
		currentTime = Calendar.getInstance();
		datePicker = new DatePickerFragment(currentTime, ReportFragment.this);
		
		previousButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addDate(-1);
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addDate(1);
			}
		});
		
		saleLedgerListView.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
		    	  String id = saleList.get(position).get("id").toString();
		    	  Intent newActivity = new Intent(getActivity().getBaseContext(), SaleDetailActivity.class);
		          newActivity.putExtra("id", id);
		          startActivity(newActivity);  
		      }     
		});
		
//		searchBox.addTextChangedListener(new TextWatcher() {
//			public void afterTextChanged(Editable s) {
//				if (s.length() >= SEARCH_LIMIT) {
//					search();
//				}
//			}
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//			public void onTextChanged(CharSequence s, int start, int before,int count) {}
//		});
	}
	
	private void showList(List<Sale> list) {

		saleList = new ArrayList<Map<String, String>>();
		for (Sale sale : list) {
			saleList.add(sale.toMap());
		}
		
		SimpleAdapter sAdap = new SimpleAdapter(getActivity().getBaseContext() , saleList,
				R.layout.listview_saleledger, new String[] { "id", "startTime", "total"},
				new int[] { R.id.sid, R.id.startTime , R.id.total});
		saleLedgerListView.setAdapter(sAdap);
	}

	@Override
	public void update() {
		String period = spinner.getSelectedItem().toString();
		List<Sale> list = null;
		Calendar cTime = (Calendar) currentTime.clone();
		Calendar eTime = (Calendar) currentTime.clone();
		if(period.equals("Daily")){
			currentBox.setText(" [" + DateTimeStrategy.getSQLDateFormat(currentTime) +  "] ");
			currentBox.setTextSize(16);
		} else if (period.equals("Weekly")){
			while(cTime.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				cTime.add(Calendar.DATE, -1);
			}
			
			String toShow = " [" + DateTimeStrategy.getSQLDateFormat(cTime) +  "] ~ [";
			eTime = (Calendar) cTime.clone();
			eTime.add(Calendar.DATE, 7);
			toShow += DateTimeStrategy.getSQLDateFormat(eTime) +  "] ";
			currentBox.setTextSize(16);
			currentBox.setText(toShow);
		} else if (period.equals("Monthly")){
			cTime.set(Calendar.DATE, 1);
			eTime = (Calendar) cTime.clone();
			eTime.add(Calendar.MONTH, 1);
			eTime.add(Calendar.DATE, -1);
			currentBox.setTextSize(18);
			currentBox.setText(" [" + currentTime.get(Calendar.YEAR) + "-" + (currentTime.get(Calendar.MONTH)+1) + "] ");
		} else if (period.equals("Yearly")){
			cTime.set(Calendar.DATE, 1);
			cTime.set(Calendar.MONTH, 0);
			eTime = (Calendar) cTime.clone();
			eTime.add(Calendar.YEAR, 1);
			eTime.add(Calendar.DATE, -1);
			currentBox.setTextSize(20);
			currentBox.setText(" [" + currentTime.get(Calendar.YEAR) +  "] ");
		}
		currentTime = cTime;
		list = saleLedger.getAllSaleDuring(cTime, eTime);
		double total = 0;
		for (Sale sale : list)
			total += sale.getTotal();
		
		totalBox.setText(total + "");
		showList(list);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		update();
	}
	
	private void addDate(int increment) {
		String period = spinner.getSelectedItem().toString();
		if (period.equals("Daily")){
			currentTime.add(Calendar.DATE, 1 * increment);
		} else if (period.equals("Weekly")){
			currentTime.add(Calendar.DATE, 7 * increment);
		} else if (period.equals("Monthly")){
			currentTime.add(Calendar.MONTH, 1 * increment);
		} else if (period.equals("Yearly")){
			currentTime.add(Calendar.YEAR, 1 * increment);
		}
		update();
	}

}
