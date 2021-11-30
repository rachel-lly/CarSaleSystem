package com.example.carsalesystem.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.carsalesystem.R;
import com.example.carsalesystem.activity.AddCustomerActivity;
import com.example.carsalesystem.adapter.CustomerListAdapter;
import com.example.carsalesystem.databinding.FragmentCustomerListBinding;
import com.example.carsalesystem.model.Customer;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.retrofit.DataManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
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

    private  CustomerListAdapter adapter;

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


        setHasOptionsMenu(true);


        adapter = new CustomerListAdapter(this.getContext(),customers);
        mBinding.recycleview.setAdapter(adapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this.getContext(),1));
        refreshCustomers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.customer_list_toolbar,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.add_customer :{
                Intent intent = new Intent(this.getContext(), AddCustomerActivity.class);
                startActivity(intent);
            }
        }
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent){
        String msg = messageEvent.getMsg();
        switch (msg){
            case "login":{
                refreshCustomers();
                break;
            }
            case "addCustomer":{
                refreshCustomers();
                break;
            }
        }
    }

    private void refreshCustomers() {

        DataManager.getInstance().getCustomer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(customers ->{
                    adapter.setCustomers(customers);
                    adapter.notifyDataSetChanged();
                });

    }


}