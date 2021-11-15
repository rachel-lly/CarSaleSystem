package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.carsalesystem.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mBinding = ActivityOrderBinding.inflate(LayoutInflater.from(this));

        setContentView(mBinding.getRoot());
    }
}