package com.example.carsalesystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carsalesystem.databinding.CustomerCardBinding;
import com.example.carsalesystem.model.Customer;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private CustomerCardBinding mBinding;
    private List<Customer> customers;
    private Context context;

    public CustomerListAdapter(Context context, List<Customer> customers) {
        this.customers = customers;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = CustomerCardBinding.inflate(LayoutInflater.from(context));

        ViewHolder holder = new ViewHolder(mBinding);


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

        private CustomerCardBinding mBinding;

        private TextView customerName;
        private TextView customerSex;
        private TextView customerAge;
        private TextView customerPhone;

        public ViewHolder(CustomerCardBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;

            customerName = mBinding.customerName;
            customerAge = mBinding.customerAge;
            customerPhone = mBinding.customerPhone;
            customerSex = mBinding.customerSex;
        }


    }
}
