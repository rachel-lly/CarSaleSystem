package com.example.carsalesystem.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.carsalesystem.R;
import com.example.carsalesystem.activity.AddCustomerActivity;
import com.example.carsalesystem.activity.EditMineActivity;
import com.example.carsalesystem.activity.LoginActivity;
import com.example.carsalesystem.activity.MainActivity;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.FragmentMineBinding;
import com.example.carsalesystem.model.Agency;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.model.User;
import com.example.carsalesystem.retrofit.DataManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {


    private FragmentMineBinding mBinding;
    private static MineFragment fragment;


    public MineFragment() {

    }


    public static MineFragment newInstance() {
        if(fragment==null){
            fragment = new MineFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = FragmentMineBinding.inflate(LayoutInflater.from(this.getContext()));


        initUserMsg();


        mBinding.loginoutLayout.setOnClickListener(v->{
            Intent intent = new Intent(this.getContext(),LoginActivity.class);
            intent.putExtra("login","again");
            startActivity(intent);
        });

        mBinding.editLayout.setOnClickListener(v->{
            Intent intent = new Intent(this.getContext(),EditMineActivity.class);
            intent.putExtra("id",UserController.getsInstance().getUserId());
            startActivity(intent);
        });

    }

    private void initUserMsg() {

        boolean isUser = UserController.getsInstance().isUser();
        Toast.makeText(this.getContext(),UserController.getsInstance().isUser()+"--",Toast.LENGTH_SHORT).show();

        if(isUser){


            mBinding.mineIdText.setText("工号");


            mBinding.mineNameText.setText("姓名");

            mBinding.sexLayout.setVisibility(View.VISIBLE);
            mBinding.ageLayout.setVisibility(View.VISIBLE);
            mBinding.agencyLayout.setVisibility(View.VISIBLE);

            mBinding.editLayout.setVisibility(View.VISIBLE);

            User user = UserController.getsInstance().getUser();
            mBinding.mineAge.setText(String.valueOf(user.getAge()));
            mBinding.mineId.setText(user.getId());
            mBinding.mineName.setText(user.getUsername());
            mBinding.mineAgency.setText(user.getAgency_name());
            mBinding.minePhone.setText(user.getPhone());
            mBinding.mineSex.setText(user.getSex());

            if(user.getSex().equals("女")){
                Glide.with(this.getContext()).load(R.drawable.customer_avatar_girl).into(mBinding.mineAvater);
            }else{
                Glide.with(this.getContext()).load(R.drawable.customer_avatar_boy).into(mBinding.mineAvater);
            }
        }else{
            Agency agency = UserController.getsInstance().getAgency();
            mBinding.mineIdText.setText("经销商编号");
            mBinding.mineId.setText(agency.getId());

            mBinding.mineNameText.setText("经销商名称");
            mBinding.mineName.setText(agency.getName());

            mBinding.minePhone.setText(agency.getPhone());

            mBinding.sexLayout.setVisibility(View.GONE);
            mBinding.ageLayout.setVisibility(View.GONE);
            mBinding.agencyLayout.setVisibility(View.GONE);

            mBinding.editLayout.setVisibility(View.GONE);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent){
        String msg = messageEvent.getMsg();
        switch (msg){
            case "login":{
                initUserMsg();
                break;
            }
            case "editUser":{

                DataManager.getInstance().getUser(UserController.getsInstance().getUserId())
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
                            initUserMsg();
                        });
                break;
            }
        }
    }

}