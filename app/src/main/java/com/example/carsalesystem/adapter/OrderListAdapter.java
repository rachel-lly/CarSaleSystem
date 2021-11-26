package com.example.carsalesystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsalesystem.R;
import com.example.carsalesystem.model.Order;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {


    private List<Order> orders;
    private Context context;

    public OrderListAdapter(Context context, List<Order> orders) {
        this.orders = orders;
        this.context = context;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_buy_car_card,parent,false);


        OrderListAdapter.ViewHolder holder = new OrderListAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.ViewHolder holder, int position) {

        Order order = orders.get(position);

        holder.carName.setText(order.getCar_name());
        holder.carPrice.setText(order.getPrice());
        holder.carCnt.setText(String.valueOf(order.getCount()));
        holder.buyTime.setText(order.getOrder_time());
        holder.sellMan.setText(order.getSellman_name());
        holder.agencyName.setText(order.getAgency_name());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView carName;
        private TextView carPrice;
        private TextView carCnt;
        private TextView buyTime;
        private TextView sellMan;
        private TextView agencyName;



        public ViewHolder(View view) {
            super(view);

            carName = view.findViewById(R.id.buy_car_name);
            carPrice = view.findViewById(R.id.buy_price);
            carCnt = view.findViewById(R.id.buy_count);
            buyTime = view.findViewById(R.id.buy_time);

            sellMan = view.findViewById(R.id.sell_man);
            agencyName = view.findViewById(R.id.agency_name);
        }


    }
}
