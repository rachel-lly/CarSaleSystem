package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.carsalesystem.R;
import com.example.carsalesystem.adapter.OrderListAdapter;
import com.example.carsalesystem.databinding.ActivityCustomerDetailBinding;
import com.example.carsalesystem.model.Customer;
import com.example.carsalesystem.model.Order;
import com.example.carsalesystem.retrofit.DataManager;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CustomerDetailActivity extends AppCompatActivity {

    private ActivityCustomerDetailBinding mBinding;

    private Customer customer;

    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCustomerDetailBinding.inflate(LayoutInflater.from(this));

        mBinding.customerDetailToolbar.setNavigationIcon(R.mipmap.back);
        mBinding.customerDetailToolbar.setNavigationOnClickListener(v-> finish());


        Intent intent = getIntent();
        String customer_id = intent.getStringExtra("Customer_id");

        DataManager.getInstance().getCustomer(customer_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(customer -> {
                    initCustomer(customer);
                    getCustomerOrderList(customer_id);
                });


        setContentView(mBinding.getRoot());
    }

    private void getCustomerOrderList(String customer_id) {
        OrderListAdapter adapter = new OrderListAdapter(this,orders);
        mBinding.recycleview.setAdapter(adapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this,1));

        DataManager.getInstance().getOrders(customer_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> {
                    adapter.setOrders(orders);
                    adapter.notifyDataSetChanged();
                });
    }

    private void initCustomer(Customer customer) {
        this.customer = customer;
        mBinding.customerName.setText(customer.getName());
        mBinding.customerAge.setText(String.valueOf(customer.getAge()));
        mBinding.customerSex.setText(customer.getSex());
        mBinding.customerPhone.setText(customer.getPhone());
    }
}