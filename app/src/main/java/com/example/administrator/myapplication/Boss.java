package com.example.administrator.myapplication;

import android.util.Log;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

//创建回调接口的实现类，此例中，员工干完活后还要干什么事情是老板说了算的
public class Boss implements CallBackInterface{
    @Override
    public void doWork() {
        Log.e("TTT", "打电话给老板，告知已经完成工作了");
    }
}
