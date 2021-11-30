package com.example.carsalesystem.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.carsalesystem.R;
import com.example.carsalesystem.databinding.ActivityEditMineBinding;
import com.example.carsalesystem.model.MessageEvent;
import com.example.carsalesystem.model.User;
import com.example.carsalesystem.retrofit.DataManager;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditMineActivity extends AppCompatActivity {

    private ActivityEditMineBinding mBinding;
    private User user;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityEditMineBinding.inflate(LayoutInflater.from(this));

        mBinding.editMineToolbar.setNavigationIcon(R.mipmap.back);
        mBinding.editMineToolbar.setNavigationOnClickListener(v->finish());

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        DataManager.getInstance().getUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    this.user = user;
                   initUser(user);
                });


        mBinding.sexLayout.setOnClickListener(v->{
            showMenu(v,R.menu.choose_sex_menu);
        });


        mBinding.addButton.setOnClickListener(v->{


            if(TextUtils.isEmpty(mBinding.nameEv.getText())){
                Toast.makeText(this,"姓名不能为空",Toast.LENGTH_SHORT).show();
            }else{
                String name = mBinding.nameEv.getText().toString();
                String sex = mBinding.sex.getText().toString();

                if(TextUtils.isEmpty(mBinding.ageEv.getText())){
                    Toast.makeText(this,"年龄不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    String age = mBinding.ageEv.getText().toString();
                    int age_now = Integer.parseInt(age);
                    if(age_now>0&&age_now<150){
                        if(!TextUtils.isEmpty(mBinding.phoneEv.getText())) {
                            String phone = mBinding.phoneEv.getText().toString();
                            if(isPhone(phone)){
                                changeMineMsg(name, sex, age_now, phone);
                                finish();
                            }else{
                                Toast.makeText(this,"手机号不合法",Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this,"年龄不合法",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        setContentView(mBinding.getRoot());
    }

    private void initUser(User user) {
        mBinding.mineId.setText(user.getId());
        mBinding.mineAgency.setText(user.getAgency_name());
        mBinding.ageEv.setText(String.valueOf(user.getAge()));
        mBinding.nameEv.setText(user.getUsername());
        mBinding.phoneEv.setText(user.getPhone());
        mBinding.sex.setText(user.getSex());
    }

    private void changeMineMsg(String name, String sex, int age, String phone) {
        DataManager.getInstance().updateUser(user.getId(),name,sex,age,user.getAgency_name(),phone,user.getAgency_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody ->{
                    EventBus.getDefault().post(new MessageEvent("editUser"));
                    Toast.makeText(this,responseBody.string(),Toast.LENGTH_LONG).show();
                },throwable -> {
                    throwable.printStackTrace();
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showMenu(View v, int choose_sex_menu) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.getMenuInflater().inflate(choose_sex_menu,popupMenu.getMenu());
        popupMenu.setGravity(Gravity.END);


        popupMenu.setOnMenuItemClickListener(item ->{
            switch (item.getItemId()) {
                case R.id.boy: {
                    mBinding.sex.setText("男");
                    return true;
                }
                case R.id.girl: {
                    mBinding.sex.setText("女");
                    return true;
                }
            }
            return true;
        });


        popupMenu.show();
    }

    /**
     * 手机号码校验(三大运营商最新号段 合作版 2021-03)
     * 移动号段：
     * 134 135 136 137 138 139 147 148 150 151 152 157 158 159 172 178 182 183 184 187 188 195 198
     * <p>
     * 联通号段：
     * 130 131 132 145 146 155 156 166 167 171 175 176 185 186 196
     * <p>
     * 电信号段：
     * 133 149 153 173 174 177 180 181 189 191 193 199
     * <p>
     * 虚拟运营商:
     * 162 165 167 170 171
     * <p>
     * 13开头排序：(0-9)（134 135 136 137 138 139 130 131 132 133）
     * 14开头排序：(5-9)（147 148 145 146 149）
     * 15开头排序：(0-3|5-9)（150 151 152 157 158 159 155 156 153）
     * 16开头排序：(6-7)（166 167）
     * 17开头排序：(1-8)（172 178 171 175 176 173 174 177）
     * 18开头排序：(0-9)（182 183 184 187 188 185 186 180 181 189）
     * 19开头排序：(1|3|5|6|8|9)（195 198 196 191 193 199）
     *
     * @param phone 手机号码
     * @return 是否属于三大运营商号段范围
     * @see {https://www.qqzeng.com/article/phone.html}
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16[6-7])|(17[1-8])|(18[0-9])|(19[1|3])|(19[5|6])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(phone).matches();
    }


}