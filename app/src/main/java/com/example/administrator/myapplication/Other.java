package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class Other {
    OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        onClick();
//        onClickListener.onClick();//简单写法（去掉全部注释调的就好了）

    }

    private void onClick() {
        onClickListener.onClick();
    }

//    interface OnClickListener {
//        void onClick();
//    }
}
