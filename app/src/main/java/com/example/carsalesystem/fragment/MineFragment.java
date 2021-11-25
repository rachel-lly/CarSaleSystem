package com.example.carsalesystem.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.carsalesystem.R;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.FragmentMineBinding;
import com.example.carsalesystem.model.User;

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

        User user = UserController.getsInstance().getUser();

        mBinding.mineAge.setText(String.valueOf(user.getAge()));
        mBinding.mineId.setText(user.getId());
        mBinding.mineName.setText(user.getUsername());
        mBinding.mineAgency.setText(user.getAgency_name());
        mBinding.minePhone.setText(user.getPhone());

        if(user.getSex().equals("女")){
            Glide.with(this.getContext()).load(R.drawable.customer_avatar_girl).into(mBinding.mineAvater);
        }else{
            Glide.with(this.getContext()).load(R.drawable.customer_avatar_boy).into(mBinding.mineAvater);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return mBinding.getRoot();
    }
}