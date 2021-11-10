package com.example.carsalesystem.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.FragmentCustomerListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListFragment extends Fragment {

    private FragmentCustomerListBinding mBinding;

    public CustomerListFragment() {
        // Required empty public constructor
    }


    public static CustomerListFragment newInstance(String param1, String param2) {
        CustomerListFragment fragment = new CustomerListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCustomerListBinding.inflate(LayoutInflater.from(this.getContext()));
        return inflater.inflate(R.layout.fragment_customer_list, container, false);
    }
}