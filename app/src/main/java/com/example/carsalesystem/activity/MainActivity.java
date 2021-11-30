package com.example.carsalesystem.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.carsalesystem.R;
import com.example.carsalesystem.adapter.PagerAdapter;
import com.example.carsalesystem.databinding.ActivityMainBinding;
import com.example.carsalesystem.fragment.CarListFragment;
import com.example.carsalesystem.fragment.CustomerListFragment;
import com.example.carsalesystem.fragment.MineFragment;
import com.example.carsalesystem.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this));


        setSupportActionBar(mBinding.homeToolbar);



        initFragment();

        setContentView(mBinding.getRoot());
    }

    private void initFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(CarListFragment.newInstance());
        fragments.add(CustomerListFragment.newInstance());
        fragments.add(OrderListFragment.newInstance());
        fragments.add(MineFragment.newInstance());

        PagerAdapter pagerAdapter = new PagerAdapter(this,fragments);

        mBinding.homeViewPager.setAdapter(pagerAdapter);

        mBinding.homeViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        mBinding.homeToolbar.setTitle("汽车列表");
                        mBinding.homeNavigationView.setSelectedItemId(R.id.item_car);
                        break;
                    case 1:
                        mBinding.homeToolbar.setTitle("顾客列表");
                        mBinding.homeNavigationView.setSelectedItemId(R.id.item_customer);
                        break;
                    case 2:
                        mBinding.homeToolbar.setTitle("订单列表");
                        mBinding.homeNavigationView.setSelectedItemId(R.id.item_order_list);
                        break;
                    case 3:
                        mBinding.homeToolbar.setTitle("我的");
                        mBinding.homeNavigationView.setSelectedItemId(R.id.item_mine);
                        break;
                }
            }
        });

        mBinding.homeNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.item_car:
                    mBinding.homeViewPager.setCurrentItem(0);
                    break;
                case R.id.item_customer:
                    mBinding.homeViewPager.setCurrentItem(1);
                    break;
                case R.id.item_order_list:
                    mBinding.homeViewPager.setCurrentItem(2);
                    break;
                case R.id.item_mine:
                    mBinding.homeViewPager.setCurrentItem(3);
                    break;
            }
            return true;
        });
    }
}