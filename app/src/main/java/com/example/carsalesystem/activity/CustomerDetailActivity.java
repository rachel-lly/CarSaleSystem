package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.ActivityCustomerDetailBinding;
import com.example.carsalesystem.model.Customer;
import com.example.carsalesystem.retrofit.DataManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CustomerDetailActivity extends AppCompatActivity {

    private ActivityCustomerDetailBinding mBinding;

    private Customer customer;

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
                    getCustomerOrderList();
                });


        setContentView(mBinding.getRoot());
    }

    private void getCustomerOrderList() {

    }

    private void initCustomer(Customer customer) {
        this.customer = customer;
        mBinding.customerName.setText(customer.getName());
        mBinding.customerAge.setText(String.valueOf(customer.getAge()));
        mBinding.customerSex.setText(customer.getSex());
        mBinding.customerPhone.setText(customer.getPhone());
    }
}