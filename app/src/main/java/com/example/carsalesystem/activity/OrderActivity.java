package com.example.carsalesystem.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.carsalesystem.R;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.ActivityOrderBinding;
import com.example.carsalesystem.model.Car;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.model.User;
import com.example.carsalesystem.retrofit.DataManager;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding mBinding;

    private Car car;

    private int nowCnt = 1;
    private int max;

    public final static int CHOOSE_CUSTOMER_RESULT_CODE = 111;

    private String lastChoose = null;
    private String chooseCustomerId = null;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data!=null){
            String customerId = data.getStringExtra("customerId");
            String customerName = data.getStringExtra("customerName");
            if(customerName==null){
                customerName = lastChoose;
            }else{
                lastChoose = customerName;
                chooseCustomerId = customerId;
            }
            mBinding.customerName.setText(customerName);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


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
            if(nowCnt<=0) nowCnt = 0;
            mBinding.buyCarCnt.setText(String.valueOf(nowCnt));

        });

        mBinding.chooseCustomerLayout.setOnClickListener(v->{
            Intent intent1 = new Intent(this,ChooseCustomerActivity.class);
            startActivityForResult(intent1, CHOOSE_CUSTOMER_RESULT_CODE);
        });


        mBinding.sure.setOnClickListener(v->{
//             "customer_id":   String,       //顾客id
//    "car_id"  :   String,       //车辆id
// "count"   :      int,       //购买数量
//    "order_time": String,       //订单时间
//    "sellman_id": String,     //销售人员id

            if(nowCnt==0){
                if(max==0) Toast.makeText(this,"该车辆暂不支持售卖",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"请选择数量",Toast.LENGTH_SHORT).show();
            }else{
                if(chooseCustomerId!=null){
                    String customer_id = chooseCustomerId;
                    String carId = car.getCar_id();
                    int count = nowCnt;
                    String order_time = format.format(System.currentTimeMillis());
                    String sellman_id = UserController.getsInstance().getUserId();
                    addOrder(customer_id,carId,count,order_time,sellman_id);
                    finish();
                }else{
                    Toast.makeText(this,"请选择顾客",Toast.LENGTH_SHORT).show();
                }
            }





        });

        setContentView(mBinding.getRoot());
    }

    private void addOrder(String customer_id, String car_id, int count, String order_time, String sellman_id) {
        DataManager.getInstance().addOrder(customer_id,car_id,count,order_time,sellman_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody ->{
                    EventBus.getDefault().post(new MessageEvent("addOrder"));
                    Toast.makeText(this,responseBody.string(),Toast.LENGTH_LONG).show();
                },throwable -> {
                    throwable.printStackTrace();
                });
    }

    private void initCar(Car car) {
        this.car = car;
        this.max = car.getCount() - car.getSell_number();
        if(max==0){
            nowCnt = 0;
            Toast.makeText(this,"该车辆暂不支持售卖",Toast.LENGTH_SHORT).show();
        }


        mBinding.buyCarCnt.setText(String.valueOf(nowCnt));
        mBinding.carName.setText(car.getCar_name());
        mBinding.carMoney.setText(car.getPrice());

        User user = UserController.getsInstance().getUser();
        mBinding.soldmanName.setText(user.getUsername());
        mBinding.soldmanAgency.setText(user.getAgency_name());
        mBinding.soldmanPhone.setText(user.getPhone());
    }
}