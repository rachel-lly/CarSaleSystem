package com.example.carsalesystem.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.carsalesystem.R;
import com.example.carsalesystem.adapter.CarListAdapter;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.FragmentCarListBinding;
import com.example.carsalesystem.model.Car;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.retrofit.DataManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarListFragment extends Fragment {

    private FragmentCarListBinding mBinding;
    private List<Car> cars = new ArrayList<>();

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    private static CarListFragment fragment;

    private CarListAdapter carListAdapter;

    public CarListFragment() {
        // Required empty public constructor
    }



    public static CarListFragment newInstance() {
        if(fragment==null){
            fragment = new CarListFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mBinding = FragmentCarListBinding.inflate(LayoutInflater.from(this.getContext()));

        carListAdapter = new CarListAdapter(this.getContext(),cars);
        mBinding.recycleview.setAdapter(carListAdapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this.getContext(),1));


        initCarList();

        mBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            initCarList();
            mBinding.swipeRefreshLayout.setRefreshing(false);
        });


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
        initCarList();
    }

    private void initCarList() {
        boolean isUser = UserController.getsInstance().isUser();
        String agency_id;
        if(isUser){
             agency_id = UserController.getsInstance().getUser().getAgency_id();
        }else{
            agency_id = UserController.getsInstance().getAgency().getId();
        }

        DataManager.getInstance().getCarList(agency_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars -> {
                            carListAdapter.setCarList(cars);
                            carListAdapter.notifyDataSetChanged();
                        },
                        throwable -> throwable.printStackTrace()
                );

    }
}