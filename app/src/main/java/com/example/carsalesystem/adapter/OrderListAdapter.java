package com.example.carsalesystem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsalesystem.R;
import com.example.carsalesystem.activity.OrderDetailActivity;
import com.example.carsalesystem.controller.UserController;
import com.example.carsalesystem.model.Order;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

        holder.itemView.setOnClickListener(v->{


                int position = holder.getAdapterPosition();
                Order order = orders.get(position);
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("order_id",order.getOrder_id());
                context.startActivity(intent);


        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.ViewHolder holder, int position) {

        Order order = orders.get(position);

        holder.carName.setText(order.getCar_name());
        holder.carCnt.setText(String.valueOf(order.getCount()));
        holder.buyTime.setText(order.getOrder_time());
        holder.sellMan.setText(order.getSellman_name());


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView carName;

        private TextView carCnt;
        private TextView buyTime;
        private TextView sellMan;




        public ViewHolder(View view) {
            super(view);

            carName = view.findViewById(R.id.buy_car_name);
            carCnt = view.findViewById(R.id.buy_count);
            buyTime = view.findViewById(R.id.buy_time);
            sellMan = view.findViewById(R.id.sell_man);

        }


    }
}
