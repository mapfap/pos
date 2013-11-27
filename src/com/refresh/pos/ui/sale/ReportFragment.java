package com.refresh.pos.ui.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.achartengine.GraphicalView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.refresh.pos.R;
import com.refresh.pos.domain.sale.Sale;
import com.refresh.pos.domain.sale.SaleLedger;
import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.ui.Chart;


public class ReportFragment extends Fragment {
	
	private SaleLedger saleLedger;
	List<Map<String, String>> saleList;
	private ListView saleLedgerListView;
	private EditText searchBox;
	private GraphicalView mChartView;
	
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
		

		if (mChartView == null) {
			LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart);
			mChartView = (new Chart()).execute(getActivity().getBaseContext());
			layout.addView(mChartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		} else {
			mChartView.repaint();
		}
		
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
	
	private void showList(List<Sale> list) {

		saleList = new ArrayList<Map<String, String>>();
		for (Sale sale : list) {
			saleList.add(sale.toMap());
//			Log.d("ledger", sale.getStartTime());
//			Log.d("ledger", sale.getEndTime());
		}
		
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(getActivity().getBaseContext() , saleList,
				R.layout.listview_saleledger, new String[] { "id", "startTime", "status", "total", "orders"},
				new int[] { R.id.sid, R.id.startTime, R.id.status , R.id.total, R.id.orders});
		saleLedgerListView.setAdapter(sAdap);
	}

	@Override
	public void onResume() {
		Log.d("ledger",saleLedger.getAllSale().size()+"");
		showList(saleLedger.getAllSale());
		super.onResume();
	}

}
