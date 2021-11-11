package com.example.carsalesystem.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.carsalesystem.R;
import com.example.carsalesystem.adapter.PagerAdapter;
import com.example.carsalesystem.databinding.ActivityMainBinding;
import com.example.carsalesystem.fragment.CarListFragment;
import com.example.carsalesystem.fragment.CustomerListFragment;
import com.example.carsalesystem.fragment.MineFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this));

        initFragment();

        setContentView(mBinding.getRoot());
    }

    private void initFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CarListFragment());
        fragments.add(new CustomerListFragment());
        fragments.add(new MineFragment());

        PagerAdapter pagerAdapter = new PagerAdapter(this,fragments);

        mBinding.homeViewPager.setAdapter(pagerAdapter);

        mBinding.homeViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        mBinding.homeToolbar.setTitle("汽车列表");
                        mBinding.homeNavigationView.setId(R.id.item_car);
                        break;
                    case 1:
                        mBinding.homeToolbar.setTitle("顾客列表");
                        mBinding.homeNavigationView.setId(R.id.item_customer);
                        break;
                    case 2:
                        mBinding.homeToolbar.setTitle("我的");
                        mBinding.homeNavigationView.setId(R.id.item_mine);
                        break;
                }
            }
        });

        mBinding.homeNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_car:
                        mBinding.homeViewPager.setCurrentItem(0);
                        break;
                    case R.id.item_customer:
                        mBinding.homeViewPager.setCurrentItem(1);
                        break;
                    case R.id.item_mine:
                        mBinding.homeViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }
}