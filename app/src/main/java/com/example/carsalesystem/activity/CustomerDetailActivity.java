package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.carsalesystem.databinding.ActivityCustomerDetailBinding;

public class CustomerDetailActivity extends AppCompatActivity {

    private ActivityCustomerDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCustomerDetailBinding.inflate(LayoutInflater.from(this));




        setContentView(mBinding.getRoot());
    }
}