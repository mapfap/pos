package com.refresh.pos.ui.inventory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentIntegratorSupportV4;
import com.google.zxing.integration.android.IntentResult;
import com.refresh.pos.R;
import com.refresh.pos.domain.inventory.Inventory;
import com.refresh.pos.domain.inventory.ProductCatalog;
import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.ui.component.UpdatableFragment;

@SuppressLint("ValidFragment")
public class AddProductDialogFragment extends DialogFragment {

	private EditText barcodeBox;
	private ProductCatalog productCatalog;
	private Button scanButton;
	private EditText priceBox;
	private EditText nameBox;
	private Button confirmButton;
	private Button clearButton;
//	private ImageButton backButton;
	private UpdatableFragment fragment;

	public AddProductDialogFragment(UpdatableFragment fragment) {
		
		super();
		this.fragment = fragment;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		try {
			productCatalog = Inventory.getInstance().getProductCatalog();
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		
		View v = inflater.inflate(R.layout.activity_addproduct, container,
				false);
		
		
		barcodeBox = (EditText) v.findViewById(R.id.barcodeBox);
		scanButton = (Button) v.findViewById(R.id.scanButton);
		priceBox = (EditText) v.findViewById(R.id.priceBox);
		nameBox = (EditText) v.findViewById(R.id.nameBox);
		confirmButton = (Button) v.findViewById(R.id.confirmButton);
		clearButton = (Button) v.findViewById(R.id.clearButton);

		initUI();
		return v;
	}

	private void initUI() {
		scanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentIntegratorSupportV4 scanIntegrator = new IntentIntegratorSupportV4(AddProductDialogFragment.this);
				scanIntegrator.initiateScan();
			}
		});

		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (nameBox.getText().toString().equals("")) {
					Toast.makeText(getActivity().getBaseContext(),
							"Please input product's name.", Toast.LENGTH_SHORT)
							.show();
				} else if (barcodeBox.getText().toString().equals("")) {
					Toast.makeText(getActivity().getBaseContext(),
							"Please input product's barcode.", Toast.LENGTH_SHORT)
							.show();
				} else if (priceBox.getText().toString().equals("")) {
					Toast.makeText(getActivity().getBaseContext(),
							"Please input product's price.", Toast.LENGTH_SHORT)
							.show();
				} else {
					boolean success = productCatalog.addProduct(nameBox
							.getText().toString(), barcodeBox.getText()
							.toString(), Double.parseDouble(priceBox.getText()
							.toString()));

					if (success) {
						Toast.makeText(getActivity().getBaseContext(),
								"Successfully Add : "
										+ nameBox.getText().toString(),
								Toast.LENGTH_SHORT).show();
						
						
						fragment.update();
						clearAllBox();
//						AddProductActivity.this.finish();
						
						
					} else {
						Toast.makeText(getActivity().getBaseContext(),
								"Failed to Add " + nameBox.getText().toString(),
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		clearButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				if(barcodeBox.getText().toString().equals("") && nameBox.getText().toString().equals("") && priceBox.getText().toString().equals("")){
//
//					final AlertDialog.Builder adb = new AlertDialog.Builder(
//							getActivity().getBaseContext());
//					adb.setTitle("Back to inventory?");
//					adb.setMessage("Are you sure to go back?");
//					adb.setNegativeButton("Stay here.", null);
//					adb.setPositiveButton("Back to inventory.", new DialogInterface.OnClickListener() {
//						
//			            @Override
//			            public void onClick(DialogInterface dialog, int which) {
////			            	getActivity().getBaseContext().finish();  
//			            }
//
//			        });
//					adb.show();
//
//				}
//				else {
					clearAllBox();
//				}
			}
		});
	}

	private void clearAllBox() {
		barcodeBox.setText("");
		nameBox.setText("");
		priceBox.setText("");
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("BARCODE", "retrive the result.");

		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);

		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			barcodeBox.setText(scanContent);
		} else {
			Toast.makeText(getActivity().getBaseContext(),
					"Failed to retrieve barcode." + resultCode,
					Toast.LENGTH_SHORT).show();
		}
	}
}
