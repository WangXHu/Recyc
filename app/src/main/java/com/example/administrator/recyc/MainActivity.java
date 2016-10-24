package com.example.administrator.recyc;

import android.app.Activity;
import android.os.Bundle;
import java.util.concurrent.ExecutionException;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

public class MainActivity extends Activity {

    //    RequestQueue queue;
    RecyclerView mRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化View
        initView();
        //从网上初始化数据
        getHttpResult();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mRV = (RecyclerView) findViewById(R.id.rv_basefragment);
        mRV.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * 从网上获取数据
     */
    private void getHttpResult() {
        //		1.异步任务的实例必须在UI线程中创建。
//		2.execute(Params... params)方法必须在UI线程中调用。
//		5.一个任务实例只能执行一次，如果执行第二次将会抛出异常。
        //得到AsyncTask对象
        MyAsynctask myAsyncTask = new MyAsynctask();
        try {
            //执行异步任务,传递的参数被doInBackground方法接收,
            //get()方法得到耗时操作doInBackground的返回值
            String string = myAsyncTask.execute(Url.HOME_ONE_URL).get();

            Gson gson = new Gson();
            CommunityBean communityBean = gson.fromJson(string, CommunityBean.class);

            //设置适配器
            mRV.setAdapter(new MAdapter(MainActivity.this, communityBean));


        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
