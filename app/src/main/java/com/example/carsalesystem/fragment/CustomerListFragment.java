package com.example.carsalesystem.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carsalesystem.adapter.CustomerListAdapter;
import com.example.carsalesystem.databinding.FragmentCustomerListBinding;
import com.example.carsalesystem.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListFragment extends Fragment {

    private FragmentCustomerListBinding mBinding;

    private List<Customer> customers = new ArrayList<>();

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

        mBinding = FragmentCustomerListBinding.inflate(LayoutInflater.from(this.getContext()));

        customers.add(new Customer("111","林利莹","女","23","3415425235"));
        customers.add(new Customer("111","林利莹","女","23","3415425235"));
        customers.add(new Customer("111","林利莹","女","23","3415425235"));
        customers.add(new Customer("111","林利莹","女","23","3415425235"));
        customers.add(new Customer("111","林利莹","女","23","3415425235"));
        CustomerListAdapter customerListAdapter = new CustomerListAdapter(this.getContext(),customers);
        mBinding.recycleview.setAdapter(customerListAdapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this.getContext(),1));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return mBinding.getRoot();
    }
}