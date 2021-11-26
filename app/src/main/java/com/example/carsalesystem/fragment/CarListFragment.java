package com.example.carsalesystem.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carsalesystem.adapter.CarListAdapter;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.databinding.FragmentCarListBinding;
import com.example.carsalesystem.model.Car;
import com.example.carsalesystem.retrofit.DataManager;

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
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        mBinding = FragmentCarListBinding.inflate(LayoutInflater.from(this.getContext()));

        CarListAdapter carListAdapter = new CarListAdapter(this.getContext(),cars);
        mBinding.recycleview.setAdapter(carListAdapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this.getContext(),1));


        String agency_id = UserController.getsInstance().getUser().getAgency_id();
        DataManager.getInstance().getCar(agency_id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(cars -> mainHandler.post(()->{
                    carListAdapter.setCarList(cars);
                   carListAdapter.notifyDataSetChanged();
                }));




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return mBinding.getRoot();
    }
}