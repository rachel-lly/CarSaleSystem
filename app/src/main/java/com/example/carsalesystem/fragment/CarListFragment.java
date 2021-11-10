package com.example.carsalesystem.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.FragmentCarListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarListFragment extends Fragment {

    private FragmentCarListBinding mBinding;

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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCarListBinding.inflate(LayoutInflater.from(this.getContext()));
        return inflater.inflate(R.layout.fragment_car_list, container, false);
    }
}