package com.example.administrator.myapplication;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.wangCallLi.Li;
import com.example.administrator.myapplication.wangCallLi.Wang;
import com.example.administrator.myapplication.widget.EasyView;
import com.example.administrator.myapplication.widget.MeasureStateEnum;
import com.example.administrator.myapplication.widget.MyLineLayout;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView textView,textWithLooper;
    Handler handler,handlerLooper,mThreadHandler;
    MyLineLayout layout;
    EasyView myView;
    int n = 0;
    private LooperThread childThread;
    HandlerThread handlerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        textWithLooper = findViewById(R.id.textWithLooper);
        layout = findViewById(R.id.contentPanel);
        myView = findViewById(R.id.myView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Click",Toast.LENGTH_SHORT).show();
                layout.setState(MeasureStateEnum.MEASURED.ordinal());
                myView.invalidate();
                layout.invalidate();
            }
        });

        //第一种   https://www.jianshu.com/p/359fa0ff2e67
        Employee employee = new Employee(new Boss()); //创建控制器对象,将提供给他的回调对象传入（返回的类型是接口，但是接口是不能实例化的，但是接口可以声明一个引用，指向其实现类，也就是说，在实际中返回值都是这个接口的实现类的对象。这点就是面向对象的一大特点）
        employee.startDoWorks();//启动控制器对象运行

        //重写doWork方法
        Employee employee1 = new Employee(new CallBackInterface() {
            @Override
            public void doWork() {
                Log.e("TTT", "干完了");
            }
        });
        employee1.startDoWorks();//启动控制器对象运行

        //第二种
        Other other = new Other();
        other.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "模仿Botton回调", Toast.LENGTH_SHORT).show();
            }
        });


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(String.valueOf(msg.obj));
                super.handleMessage(msg);
            }
        };

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            private int i;
            @Override
            public void run() {
                i++;
                Message msg = new Message();
                msg.obj = i;
                Log.e("Tag", String.valueOf(msg));
                handler.sendMessage(msg);
            }
        };
        //延时1s，每隔500毫秒执行一次run方法
        timer.schedule(timerTask,2000,500);

//        TestThread testThread = new TestThread();
//        new Thread(testThread).start();



        /**
         * new 一个小李
         */
        Li li = new Li();
        /**
         * new 一个小王
         */
        Wang wang = new Wang(li);
        /**
         * 小王问小李问题
         */
        wang.askQuestion("1 + 1 = ?");




        //适用于会长时间在后台运行，并且间隔时间内更新UI
        handlerThread = new HandlerThread("LooperThread");
        handlerThread.start();
        mThreadHandler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(final Message msg) {
                Log.e("Tag",String.valueOf(msg.obj));
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        textWithLooper.setText(String.valueOf(n+13));
                    }
                });
            }
        };

        //子线程开启消息循环
        new Thread(new LooperThread()).start();
        findViewById(R.id.loop_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = n + 1;
                Message msg = new Message();
                msg.obj = n;
                handlerLooper.sendMessage(msg);

                mThreadHandler.sendEmptyMessage(n);
            }
        });



    }

    public class TestThread implements Runnable {

        private int i;
        @Override
        public void run() {
            while (true) {
                i++;
                Log.e("test",String.valueOf(i));
            }
        }
    }


    public class LooperThread implements Runnable {
        @Override
        public void run() {
            Looper.prepare();
            Toast.makeText(MainActivity.this,"Looper",Toast.LENGTH_SHORT).show();
            handlerLooper = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.e("Tag",String.valueOf(msg.obj));
                }
            };
            Looper.loop();
        }
    }

}
