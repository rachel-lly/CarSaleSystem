package com.example.carsalesystem.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carsalesystem.adapter.CarListAdapter;
import com.example.carsalesystem.databinding.FragmentCarListBinding;
import com.example.carsalesystem.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarListFragment extends Fragment {

    private FragmentCarListBinding mBinding;
    private List<Car> cars = new ArrayList<>();

    public CarListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarListFragment.
     */

    public static CarListFragment newInstance(String param1, String param2) {
        CarListFragment fragment = new CarListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
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

//        cars.add(new Car("111","布加迪","200",16,11,
//                "http://img10.360buyimg.com/n0/jfs/t475/205/872540024/139773/c3d8a304/54924e2eNfcc12a5f.jpg",
//                "布加迪的车子就像是艺术品一般，它车辆的引擎全是由手工制造和调校，所有可以轻量化的零件都不放过，布加迪注重车辆的细节与平衡。"));
//        cars.add(new Car("111","布加迪","200",16,11,
//                "http://img10.360buyimg.com/n0/jfs/t475/205/872540024/139773/c3d8a304/54924e2eNfcc12a5f.jpg",
//                "布加迪的车子就像是艺术品一般，它车辆的引擎全是由手工制造和调校，所有可以轻量化的零件都不放过，布加迪注重车辆的细节与平衡。"));
//        cars.add(new Car("111","布加迪","200",16,11,
//                "http://img10.360buyimg.com/n0/jfs/t475/205/872540024/139773/c3d8a304/54924e2eNfcc12a5f.jpg",
//                "布加迪的车子就像是艺术品一般，它车辆的引擎全是由手工制造和调校，所有可以轻量化的零件都不放过，布加迪注重车辆的细节与平衡。"));
        CarListAdapter carListAdapter = new CarListAdapter(this.getContext(),cars);
        mBinding.recycleview.setAdapter(carListAdapter);
        mBinding.recycleview.setLayoutManager(new GridLayoutManager(this.getContext(),1));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return mBinding.getRoot();
    }
}