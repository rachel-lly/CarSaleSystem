package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.carsalesystem.R;
import com.example.carsalesystem.adapter.ChooseCustomerListAdapter;
import com.example.carsalesystem.databinding.ActivityChooseCustomerBinding;
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

import static com.example.carsalesystem.activity.OrderActivity.CHOOSE_CUSTOMER_RESULT_CODE;

public class ChooseCustomerActivity extends AppCompatActivity {

    private ActivityChooseCustomerBinding mBinding;

    private ChooseCustomerListAdapter adapter;

    private List<Customer> customers = new ArrayList<>();
    private ChooseCustomerListAdapter.ClickListener onClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mBinding = ActivityChooseCustomerBinding.inflate(LayoutInflater.from(this));

        mBinding.chooseCustomerToolbar.setNavigationIcon(R.mipmap.back);
        mBinding.chooseCustomerToolbar.setNavigationOnClickListener(v-> finish());


        onClickListener = (customerId, customerName) -> {
            Intent intent = new Intent();
            intent.putExtra("customerId",customerId);
            intent.putExtra("customerName",customerName);
            setResult(CHOOSE_CUSTOMER_RESULT_CODE,intent);
            finish();
        };



        adapter = new ChooseCustomerListAdapter(this,customers,onClickListener);
        mBinding.recycleview.setAdapter(adapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this,1));
        refreshCustomers();

        setContentView(mBinding.getRoot());
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent){
        String msg = messageEvent.getMsg();
        switch (msg){
            case "login":
            case "addCustomer": {
                refreshCustomers();
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}