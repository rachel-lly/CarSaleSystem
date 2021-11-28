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
import com.example.carsalesystem.model.Customer;

import java.util.List;

public class ChooseCustomerListAdapter extends RecyclerView.Adapter<ChooseCustomerListAdapter.ViewHolder> {


    private List<Customer> customers;
    private Context context;
    private ClickListener mClickListener;

    public ChooseCustomerListAdapter( Context context, List<Customer> customers,ClickListener mClickListener) {
        this.customers = customers;
        this.context = context;
        this.mClickListener = mClickListener;
    }


    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @NonNull
    @Override
    public ChooseCustomerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_card,parent,false);


        ChooseCustomerListAdapter.ViewHolder holder = new ChooseCustomerListAdapter.ViewHolder(view);

        holder.itemView.setOnClickListener(v -> {

            int position = holder.getAdapterPosition();
            Customer customer = customers.get(position);
            mClickListener.onClick(customer.getCustomer_id(),customer.getName());

        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseCustomerListAdapter.ViewHolder holder, int position) {

        Customer customer = customers.get(position);

        holder.customerId.setText(customer.getCustomer_id());
        holder.customerSex.setText(customer.getSex());
        holder.customerAge.setText(String.valueOf(customer.getAge()));
        holder.customerName.setText(customer.getName());
        holder.customerPhone.setText(customer.getPhone());

        if(customer.getSex().equals("女")){
            Glide.with(context).load(R.drawable.avatar_girl).into(holder.customerAvatar);
        }else{
            Glide.with(context).load(R.drawable.avatar).into(holder.customerAvatar);
        }

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView customerName;
        private TextView customerId;
        private TextView customerSex;
        private TextView customerAge;
        private TextView customerPhone;

        private ImageView customerAvatar;

        public ViewHolder(View view) {
            super(view);

            customerName = view.findViewById(R.id.customer_name);
            customerId = view.findViewById(R.id.customer_id);
            customerAge = view.findViewById(R.id.customer_age);
            customerPhone = view.findViewById(R.id.customer_phone);
            customerSex = view.findViewById(R.id.customer_sex);

            customerAvatar = view.findViewById(R.id.customer_avatar);
        }


    }

    public interface ClickListener{
        void onClick(String customerId,String customerName);
    }
}
