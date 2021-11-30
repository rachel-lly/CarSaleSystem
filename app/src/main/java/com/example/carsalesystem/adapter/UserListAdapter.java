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
import com.example.carsalesystem.model.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {


    private List<User> users;
    private Context context;

    public UserListAdapter(Context context, List<User> users) {
        this.users = users;
        this.context = context;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_card,parent,false);


        UserListAdapter.ViewHolder holder = new UserListAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {

        User User = users.get(position);

        holder.UserId.setText(User.getId());
        holder.UserSex.setText(User.getSex());
        holder.UserAge.setText(String.valueOf(User.getAge()));
        holder.UserName.setText(User.getUsername());
        holder.UserPhone.setText(User.getPhone());

        if(User.getSex().equals("女")){
            Glide.with(context).load(R.drawable.customer_avatar_girl).into(holder.UserAvatar);
        }else{
            Glide.with(context).load(R.drawable.customer_avatar_boy).into(holder.UserAvatar);
        }

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView UserName;
        private TextView UserId;
        private TextView UserSex;
        private TextView UserAge;
        private TextView UserPhone;

        private ImageView UserAvatar;

        public ViewHolder(View view) {
            super(view);

            UserName = view.findViewById(R.id.user_name);
            UserId = view.findViewById(R.id.user_id);
            UserAge = view.findViewById(R.id.user_age);
            UserPhone = view.findViewById(R.id.user_phone);
            UserSex = view.findViewById(R.id.user_sex);

            UserAvatar = view.findViewById(R.id.user_avatar);
        }


    }
}