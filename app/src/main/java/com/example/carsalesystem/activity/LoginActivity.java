package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.ActivityLoginBinding;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.model.User;
import com.example.carsalesystem.retrofit.DataManager;
import com.example.carsalesystem.util.SPUtils;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));

        String userId = SPUtils.get("userId","null");
        String userPassword = SPUtils.get("userPassword","null");
        if(!userId.equals("null")){
            if(getIntent().getStringExtra("login")==null){
                DataManager.getInstance().getUser(userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(user ->{
                            UserController.getsInstance().setUser(
                                    user.getAgency_id(),
                                    user.getId(),
                                    user.getUsername(),
                                    user.getSex(),
                                    user.getAge(),
                                    user.getAgency_name(),
                                    user.getPhone()
                            );
                            Intent intent = new Intent(this,MainActivity.class);
                            startActivity(intent);
                        });

            }
            mBinding.idEv.setText(userId);
            mBinding.passwordEv.setText(userPassword);
        }



        mBinding.loginButton.setOnClickListener(v -> {
            String id = mBinding.idEv.getText().toString();
            String password = mBinding.passwordEv.getText().toString();

            DataManager.getInstance().getUser(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user ->{
                        UserController.getsInstance().setUser(
                                user.getAgency_id(),
                                user.getId(),
                                user.getUsername(),
                                user.getSex(),
                                user.getAge(),
                                user.getAgency_name(),
                                user.getPhone()
                        );
                        login(id,password);
                    },throwable -> {
                        throwable.printStackTrace();
                        Toast.makeText(this,"不存在该账号",Toast.LENGTH_SHORT).show();
                    });

        });

        setContentView(mBinding.getRoot());
    }


    private void login(String id,String password){
        DataManager.getInstance().login(id,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {

                    String s = responseBody.string();
                    if(s.equals("登录成功")){
                        SPUtils.put("userId",id);
                        SPUtils.put("userPassword",password);

//                        Toast.makeText(this,s+UserController.getsInstance().getUser().getUsername(),Toast.LENGTH_SHORT).show();
                        if(getIntent().getStringExtra("login")!=null){
                            EventBus.getDefault().post(new MessageEvent("login"));
                            finish();
                        }else{
                            Intent intent = new Intent(this,MainActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
                    }

                },throwable ->{
                    throwable.printStackTrace();
                });
    }


}