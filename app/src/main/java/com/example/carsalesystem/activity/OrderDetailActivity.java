package com.example.carsalesystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carsalesystem.R;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.ActivityOrderDetailBinding;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.model.Order;
import com.example.carsalesystem.retrofit.DataManager;
import com.example.carsalesystem.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderDetailActivity extends AppCompatActivity {

    private ActivityOrderDetailBinding mBinding;
    private Order order;
    private UserController userController = UserController.getsInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityOrderDetailBinding.inflate(LayoutInflater.from(this));

        mBinding.orderDetailToolbar.setNavigationIcon(R.mipmap.back);
        mBinding.orderDetailToolbar.setNavigationOnClickListener(v-> finish());

        Intent intent = getIntent();
        String order_id = intent.getStringExtra("order_id");
        DataManager.getInstance().getOrder(order_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(order -> {
                    initOrder(order);
                },throwable -> throwable.printStackTrace());

        mBinding.delete.setOnClickListener(v->{
            delOrder();
            finish();
        });

        mBinding.sure.setOnClickListener(v->finish());

        setContentView(mBinding.getRoot());
    }

    private void delOrder() {
        DataManager.getInstance().delOrder(order.getOrder_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    EventBus.getDefault().post(new MessageEvent("delOrder"));
                    ToastUtil.toastShort(this,responseBody.string());
                },throwable -> throwable.printStackTrace());
    }

    private void initOrder(Order order) {
        this.order = order;

        mBinding.orderId.setText(order.getOrder_id());
        mBinding.buyCarName.setText(order.getCar_name());
        mBinding.buyPrice.setText(order.getPrice());
        mBinding.buyCount.setText(String.valueOf(order.getCount()));
        mBinding.customerName.setText(order.getCustomer_name());
        mBinding.buyTime.setText(order.getOrder_time());
        mBinding.sellMan.setText(order.getSellman_name());
        mBinding.agencyName.setText(order.getAgency_name());

        if(userController.isUser()&&userController.getUserId().equals(order.getSellman_id())){
            mBinding.delete.setVisibility(View.VISIBLE);
        }else{
            mBinding.delete.setVisibility(View.GONE);
        }
    }
}