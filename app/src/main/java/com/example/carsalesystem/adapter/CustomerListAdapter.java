package com.example.carsalesystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.CustomerCardBinding;
import com.example.carsalesystem.model.Customer;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {


    private List<Customer> customers;
    private Context context;

    public CustomerListAdapter(Context context, List<Customer> customers) {
        this.customers = customers;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_card,parent,false);


        ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListAdapter.ViewHolder holder, int position) {

        Customer customer = customers.get(position);

        holder.customerSex.setText(customer.getSex());
        holder.customerAge.setText(customer.getAge());
        holder.customerName.setText(customer.getName());
        holder.customerPhone.setText(customer.getPhone());

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView customerName;
        private TextView customerSex;
        private TextView customerAge;
        private TextView customerPhone;

        public ViewHolder(View view) {
            super(view);

            customerName = view.findViewById(R.id.customer_name);
            customerAge = view.findViewById(R.id.customer_age);
            customerPhone = view.findViewById(R.id.customer_phone);
            customerSex = view.findViewById(R.id.customer_sex);
        }


    }
}
