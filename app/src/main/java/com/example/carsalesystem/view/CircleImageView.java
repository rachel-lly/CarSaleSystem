package com.example.carsalesystem.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CircleImageView extends AppCompatImageView {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private BitmapShader mShader = null;
    private Bitmap mBitmap = null;
    private Matrix mMatrix = new Matrix();

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = getBitmap(getDrawable());
        if(bitmap!=null){
            int min = Math.min(getWidth(),getHeight());

            if(mShader==null||bitmap != mBitmap){
                mBitmap = bitmap;
                mShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
            }

            mMatrix.setScale((float) min/bitmap.getWidth(),(float) min/bitmap.getHeight());
            mShader.setLocalMatrix(mMatrix);

            mPaint.setShader(mShader);
            mPaint.setAntiAlias(true);

            float radius = min/2.0f;
            canvas.drawCircle(radius,radius,radius,mPaint);


        }else{
            super.onDraw(canvas);
        }
    }

    private Bitmap getBitmap(Drawable drawable){
        if(drawable instanceof BitmapDrawable){
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if(drawable instanceof ColorDrawable){
            Rect rect = drawable.getBounds();
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            int color = ((ColorDrawable) drawable).getColor();
            Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(
                    Color.alpha(color),
                    Color.red(color),
                    Color.green(color),
                    Color.blue(color)
            );
            return bitmap;
        }
        return null;
    }
}
