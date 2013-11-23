package com.refresh.pos.ui;

import java.util.List;
import java.util.Map;

import com.refresh.pos.R;
import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.domain.Inventory;
import com.refresh.pos.domain.SaleLedger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class ReportFragment extends Fragment {
	
	private SaleLedger saleLedger;
	List<Map<String, String>> saleList;
	private ListView saleLedgerListView;
	private EditText searchBox;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		try {
			saleLedger = SaleLedger.getInstance();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		View view = inflater.inflate(R.layout.layout_report, container, false);
		
		saleLedgerListView = (ListView) view.findViewById(R.id.saleListView);
		searchBox = (EditText) view.findViewById(R.id.searchBox);
		
		initUI();
		return view;
	}

	private void initUI() {
		searchBox.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
//				if (s.length() >= SEARCH_LIMIT) {
//					search();
//				}
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void onTextChanged(CharSequence s, int start, int before,int count) {}
		});
		
	}
}
