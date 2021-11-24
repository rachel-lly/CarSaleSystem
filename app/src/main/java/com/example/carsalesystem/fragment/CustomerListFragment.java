package com.example.carsalesystem.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carsalesystem.adapter.CustomerListAdapter;
import com.example.carsalesystem.databinding.FragmentCustomerListBinding;
import com.example.carsalesystem.model.Customer;
import com.example.carsalesystem.retrofit.DataManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListFragment extends Fragment {

    private FragmentCustomerListBinding mBinding;

    private List<Customer> customers = new ArrayList<>();

    private static CustomerListFragment fragment;

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public CustomerListFragment() {
        // Required empty public constructor
    }


    public static CustomerListFragment newInstance() {
        if(fragment==null){
            fragment = new CustomerListFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mBinding = FragmentCustomerListBinding.inflate(LayoutInflater.from(this.getContext()));
        CustomerListAdapter adapter = new CustomerListAdapter(this.getContext(),customers);
        mBinding.recycleview.setAdapter(adapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this.getContext(),1));




        DataManager.getInstance().getCustomer()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(customers ->{
                    mainHandler.post(()->{
                        adapter.setCustomers(customers);
                        adapter.notifyDataSetChanged();
                            });
                });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return mBinding.getRoot();
    }
}