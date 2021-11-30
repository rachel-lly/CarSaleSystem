package com.example.carsalesystem.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.carsalesystem.R;
import com.example.carsalesystem.adapter.UserListAdapter;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.ActivityShowUserListBinding;
import com.example.carsalesystem.model.User;
import com.example.carsalesystem.retrofit.DataManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShowUserListActivity extends AppCompatActivity {

    private ActivityShowUserListBinding mBinding;

    private UserListAdapter adapter;

    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityShowUserListBinding.inflate(LayoutInflater.from(this));

        mBinding.userListToolbar.setNavigationIcon(R.mipmap.back);
        mBinding.userListToolbar.setNavigationOnClickListener(v-> finish());

        adapter = new UserListAdapter(this,users);
        mBinding.recycleview.setAdapter(adapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this,1));

        DataManager.getInstance().getAgencyUser(UserController.getsInstance().getAgencyId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users ->{
                    adapter.setUsers(users);
                    adapter.notifyDataSetChanged();
                });

        setContentView(mBinding.getRoot());
    }
}