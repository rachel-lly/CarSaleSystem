package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.carsalesystem.R;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.ActivityOrderBinding;
import com.example.carsalesystem.model.Car;
import com.example.carsalesystem.model.User;
import com.example.carsalesystem.retrofit.DataManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding mBinding;

    private Car car;

    private int nowCnt = 1;
    private int max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mBinding = ActivityOrderBinding.inflate(LayoutInflater.from(this));

        mBinding.orderToolbar.setNavigationIcon(R.mipmap.back);
        mBinding.orderToolbar.setNavigationOnClickListener(v->finish());

        Intent intent = getIntent();
        String car_id = intent.getStringExtra("car_id");

        DataManager.getInstance().getCar(car_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(car -> {
                    initCar(car);
                });

        mBinding.add.setOnClickListener(v->{
            nowCnt++;
            if(nowCnt>max){
                nowCnt = max;
                Toast.makeText(this,"可选择的最大数量为"+max,Toast.LENGTH_SHORT).show();
            }

            mBinding.buyCarCnt.setText(String.valueOf(nowCnt));
        });

        mBinding.minus.setOnClickListener(v->{
            nowCnt--;
            if(nowCnt<=1) nowCnt = 1;
            mBinding.buyCarCnt.setText(String.valueOf(nowCnt));

        });

        mBinding.chooseCustomerLayout.setOnClickListener(v->{
            Intent intent1 = new Intent(this,ChooseCustomerActivity.class);
            startActivity(intent1);
        });

        setContentView(mBinding.getRoot());
    }

    private void initCar(Car car) {
        this.car = car;
        this.max = car.getCount() - car.getSell_number();

        mBinding.carName.setText(car.getCar_name());
        mBinding.carMoney.setText(car.getPrice());

        User user = UserController.getsInstance().getUser();
        mBinding.soldmanName.setText(user.getUsername());
        mBinding.soldmanAgency.setText(user.getAgency_name());
        mBinding.soldmanPhone.setText(user.getPhone());
    }
}