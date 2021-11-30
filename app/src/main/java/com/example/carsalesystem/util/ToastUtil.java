package com.example.carsalesystem.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void toastShort(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
