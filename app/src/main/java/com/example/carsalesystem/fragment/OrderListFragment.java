package com.example.carsalesystem.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carsalesystem.R;
import com.example.carsalesystem.adapter.OrderListAdapter;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.FragmentOrderListBinding;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.model.Order;
import com.example.carsalesystem.retrofit.DataManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderListFragment extends Fragment {


    private FragmentOrderListBinding mBinding;

    private ArrayList<Order> orders = new ArrayList<>();
    private OrderListAdapter adapter;

    public OrderListFragment() {
        // Required empty public constructor
    }


    public static OrderListFragment newInstance() {
        OrderListFragment fragment = new OrderListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = FragmentOrderListBinding.inflate(LayoutInflater.from(this.getContext()));

        getCustomerOrderList();
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
        refreshOrders();
    }

    private void getCustomerOrderList() {
        adapter = new OrderListAdapter(this.getContext(),orders);
        mBinding.recycleview.setAdapter(adapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this.getContext(),1));

        refreshOrders();
    }

    private void refreshOrders() {
        boolean isUser = UserController.getsInstance().isUser();
        String agency_id;
        if(isUser){
            agency_id = UserController.getsInstance().getUser().getAgency_id();
        }else{
            agency_id = UserController.getsInstance().getAgency().getId();
        }
        DataManager.getInstance().getOrderList(agency_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> {
                    adapter.setOrders(orders);
                    adapter.notifyDataSetChanged();
                });
    }
}