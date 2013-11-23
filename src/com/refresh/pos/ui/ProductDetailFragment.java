package com.refresh.pos.ui;

import com.refresh.pos.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ProductDetailFragment extends Fragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("VIVZ","onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null)
        {
            Log.d("VIVZ","onCreate FIRST TIME");
        }
        else
        {
            Log.d("VIVZ","onCreate SUBSEQUENT TIME");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        Log.d("VIVZ","onCreateView");
        return inflater.inflate(R.layout.fragment_productdetail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("VIVZ","onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("VIVZ","onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("VIVZ","onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("VIVZ","onPause");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("VIVZ","onSaveInstanceState");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("VIVZ","onStop");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("VIVZ","onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("VIVZ","onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("VIVZ","onDetach");

    }
}
