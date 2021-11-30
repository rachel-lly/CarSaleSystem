package com.example.carsalesystem.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.ActivityEditMineBinding;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.model.User;
import com.example.carsalesystem.retrofit.DataManager;
import com.example.carsalesystem.util.CheckUtils;
import com.example.carsalesystem.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditMineActivity extends AppCompatActivity {

    private ActivityEditMineBinding mBinding;
    private User user;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityEditMineBinding.inflate(LayoutInflater.from(this));

        mBinding.editMineToolbar.setNavigationIcon(R.mipmap.back);
        mBinding.editMineToolbar.setNavigationOnClickListener(v->finish());

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        DataManager.getInstance().getUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    this.user = user;
                   initUser(user);
                });


        mBinding.sexLayout.setOnClickListener(v->{
            showMenu(v,R.menu.choose_sex_menu);
        });


        mBinding.addButton.setOnClickListener(v->{


            if(TextUtils.isEmpty(mBinding.nameEv.getText())){
                ToastUtil.toastShort(this,"姓名不能为空");
            }else{
                String name = mBinding.nameEv.getText().toString();
                String sex = mBinding.sex.getText().toString();

                if(TextUtils.isEmpty(mBinding.ageEv.getText())){
                    ToastUtil.toastShort(this,"年龄不能为空");
                }else{
                    String age = mBinding.ageEv.getText().toString();
                    int age_now = Integer.parseInt(age);
                    if(age_now>0&&age_now<150){
                        if(!TextUtils.isEmpty(mBinding.phoneEv.getText())) {
                            String phone = mBinding.phoneEv.getText().toString();
                            if(CheckUtils.isPhone(phone)){
                                changeMineMsg(name, sex, age_now, phone);
                                finish();
                            }else{
                                ToastUtil.toastShort(this,"手机号不合法");
                            }

                        }else{
                            ToastUtil.toastShort(this,"手机号不能为空");
                        }
                    }else{
                        ToastUtil.toastShort(this,"年龄不合法");
                    }
                }
            }
        });

        setContentView(mBinding.getRoot());
    }

    private void initUser(User user) {
        mBinding.mineId.setText(user.getId());
        mBinding.mineAgency.setText(user.getAgency_name());
        mBinding.ageEv.setText(String.valueOf(user.getAge()));
        mBinding.nameEv.setText(user.getUsername());
        mBinding.phoneEv.setText(user.getPhone());
        mBinding.sex.setText(user.getSex());
    }

    private void changeMineMsg(String name, String sex, int age, String phone) {
        DataManager.getInstance().updateUser(user.getId(),name,sex,age,user.getAgency_name(),phone,user.getAgency_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody ->{
                    EventBus.getDefault().post(new MessageEvent("editUser"));
                    ToastUtil.toastShort(this,responseBody.string());

                },throwable -> {
                    throwable.printStackTrace();
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showMenu(View v, int choose_sex_menu) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.getMenuInflater().inflate(choose_sex_menu,popupMenu.getMenu());
        popupMenu.setGravity(Gravity.END);


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