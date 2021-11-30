package com.example.carsalesystem.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.ActivityAddCustomerBinding;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.retrofit.DataManager;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddCustomerActivity extends AppCompatActivity{

    private ActivityAddCustomerBinding mBinding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityAddCustomerBinding.inflate(LayoutInflater.from(this));

        mBinding.addCustomerToolbar.setNavigationIcon(R.mipmap.back);
        mBinding.addCustomerToolbar.setNavigationOnClickListener(v->finish());



        mBinding.sexLayout.setOnClickListener(v->{
            showMenu(v,R.menu.choose_sex_menu);
        });

        mBinding.addButton.setOnClickListener(v->{


            if(TextUtils.isEmpty(mBinding.nameEv.getText())){
                Toast.makeText(this,"姓名不能为空",Toast.LENGTH_SHORT).show();
            }else{
                String name = mBinding.nameEv.getText().toString();
                String sex = mBinding.sex.getText().toString();

                if(TextUtils.isEmpty(mBinding.ageEv.getText())){
                    Toast.makeText(this,"年龄不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    String age = mBinding.ageEv.getText().toString();
                    int age_now = Integer.parseInt(age);
                    if(age_now>0&&age_now<150){
                        if(!TextUtils.isEmpty(mBinding.phoneEv.getText())) {
                            String phone = mBinding.phoneEv.getText().toString();
                            addCustomer(name, sex, age, phone);
                            finish();
                        }else{
                            Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this,"年龄不合法",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        setContentView(mBinding.getRoot());
    }

    private void addCustomer(String name, String sex, String age, String phone) {
        DataManager.getInstance().addCustomer(name, sex, age, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody ->{
                    EventBus.getDefault().post(new MessageEvent("addCustomer"));
                    Toast.makeText(this,responseBody.string(),Toast.LENGTH_LONG).show();
                },throwable -> {
                    throwable.printStackTrace();
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showMenu(View v, int choose_sex_menu) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.getMenuInflater().inflate(choose_sex_menu,popupMenu.getMenu());
//        popupMenu.setGravity(Gravity.BOTTOM);

        popupMenu.setOnMenuItemClickListener(item ->{
            switch (item.getItemId()) {
                case R.id.boy: {
                    mBinding.sex.setText("男");
                    return true;
                }
                case R.id.girl: {
                    mBinding.sex.setText("女");
                    return true;
                }
            }
            return true;
        });


        popupMenu.show();
    }


}