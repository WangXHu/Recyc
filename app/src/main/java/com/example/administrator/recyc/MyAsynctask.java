package com.example.administrator.recyc;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/8.
 */
public class MyAsynctask extends AsyncTask<String, Integer, String> {


    //运行在主线程上,在doInBackground之前执行
    @Override
    protected void onPreExecute() {
    }

    //运行在子线程上,做耗时操作
    @Override
    protected String doInBackground(String... params) {
        // 取出参数,因为是数组,所以取参数时按角标获取
        String json = params[0];
        String string = "";
        try {
            URL url = new URL(json);
            HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
            openConnection.connect();
            if (openConnection.getResponseCode() == 200) {
                InputStream inputStream = openConnection.getInputStream();
                // 记录每一行读到的数据
                String s = "";
                // 追加流里的数据
                StringBuffer buffer = new StringBuffer();
                // 读出流里的数据
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream));
                // 只要不等于null,流里就一直有数据,就要一直循环读取
                while ((s = bufferedReader.readLine()) != null) {
                    buffer.append(s);
                }
                string = buffer.toString();
                bufferedReader.close();
                inputStream.close();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }

    //接受doInBackground的返回值
    @Override
    protected void onPostExecute(String s) {
    }
}
