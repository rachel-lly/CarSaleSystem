package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));

        setContentView(mBinding.getRoot());
    }
}