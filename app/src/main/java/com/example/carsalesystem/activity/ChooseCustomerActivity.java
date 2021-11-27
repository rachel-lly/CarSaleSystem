package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.ActivityChooseCustomerBinding;

public class ChooseCustomerActivity extends AppCompatActivity {

    private ActivityChooseCustomerBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityChooseCustomerBinding.inflate(LayoutInflater.from(this));




        setContentView(mBinding.getRoot());
    }
}