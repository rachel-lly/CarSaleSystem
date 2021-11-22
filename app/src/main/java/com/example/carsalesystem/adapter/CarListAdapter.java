package com.example.carsalesystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.CarinfoCardBinding;
import com.example.carsalesystem.model.Car;
import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {


    private Context context;
    private List<Car> carList;

    public CarListAdapter(Context context, List<Car> carList) {

        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carinfo_card,parent,false);
        CarinfoCardBinding mBinding = CarinfoCardBinding.inflate(LayoutInflater.from(context));
        ViewHolder holder = new ViewHolder(view,mBinding);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Car car = carList.get(position);

        holder.carDes.setText(car.getDescription());
        Glide.with(context).load(car.getUrl()).into(holder.carImg);
        holder.carName.setText(car.getCar_name());
        holder.carNum.setText(String.valueOf(car.getCount()));
        holder.carSoldNum.setText(String.valueOf(car.getSell_number()));
        holder.carPrice.setText(car.getPrice());

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView carImg;

        private TextView carName;
        private TextView carPrice;
        private TextView carNum;
        private TextView carSoldNum;
        private TextView carDes;

        public ViewHolder(View view,CarinfoCardBinding mBinding) {

            super(view);

            carImg = mBinding.carImg;
            carName = mBinding.carName;
            carPrice = mBinding.carPrice;
            carNum = mBinding.carCnt;
            carSoldNum = mBinding.carSoldCnt;
            carDes = mBinding.carDes;
        }
        
    }
}
