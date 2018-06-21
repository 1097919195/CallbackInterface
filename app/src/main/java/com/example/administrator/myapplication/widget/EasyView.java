package com.example.administrator.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class EasyView extends View {
    boolean b = true;
    public EasyView(Context context) {
        super(context);
    }

    public EasyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyLineLayoutBg);
        b = a.getBoolean(R.styleable.MyLineLayoutBg_unMeasure, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        b = !b;
        if (b) {
//            Paint paint = new Paint();
//            paint.setColor(Color.YELLOW);
//            paint.setStyle(Paint.Style.FILL);
//            canvas.drawRect(new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()), paint);
            canvas.drawColor(Color.GREEN);
        }else {
            canvas.drawColor(Color.YELLOW);
        }
        super.onDraw(canvas);
    }
}
