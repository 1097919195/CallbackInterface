package com.example.administrator.myapplication;

import android.util.Log;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

//创建控制类，也就是本例中的员工对象，他要持有老板的地址(即回调接口)
public class Employee {
    CallBackInterface callBackInterface;
    public Employee(CallBackInterface clickListener) {
        this.callBackInterface =clickListener;
//        startDoWorks();//这样的话不需要控制器，但是这样的话接口回调的意义就没有了，程序没有起到扩展作用
    }

    public void startDoWorks() {
        Log.e("TTT", "玩命干活中....");
        callBackInterface.doWork();//调用的其实是Boss中重写的doWork方法
    }
}
