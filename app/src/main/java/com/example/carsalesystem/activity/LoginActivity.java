package com.example.carsalesystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.ActivityLoginBinding;
import com.example.carsalesystem.model.User;
import com.example.carsalesystem.retrofit.DataManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));


        mBinding.loginButton.setOnClickListener(v -> {
            String id = mBinding.idEv.getText().toString();
            String password = mBinding.passwordEv.getText().toString();

            DataManager.getInstance().getUser(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user ->{
                        login(id,password);
                    },throwable -> {
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
                        getUserMessage(id);
                        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
                    }

                },throwable ->{
                    throwable.printStackTrace();
                });
    }

    private void getUserMessage(String id) {
        DataManager.getInstance().getUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user ->{
                    UserController.getsInstance().setUser(
                            user.getId(),
                            user.getUsername(),
                            user.getSex(),
                            user.getAge(),
                            user.getAgency_name(),
                            user.getPhone()
                    );
                });
    }

}