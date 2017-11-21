package liuqhahahh.njupt.edu.cn.json_analysis;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

   /* private TextView mTextView;
    private String url = "http://m.weather.com.cn/data/101010100.html";
    protected CharSequence weatherResult = null;*/

   private final OkHttpClient client = new OkHttpClient();
   private final Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Gson gson = new Gson();
        //创建JavaBean类的对象
        EntityStudent student = new EntityStudent();
        String json = "{\"id\":1,\"name\":\"小明\",\"sex\":\"男\",\"age\":18,\"height\":175}";
        //用GSON方法将JSON数据转为单个类实体

        student = gson.fromJson(json,EntityStudent.class);
        //调用student方法展示解析的数据
        student.show();*/

        /**
         * 使用异步加载技术。
         */
        //new RequestTask().execute();

        //主线程中不给进行网络请求，开启子线程：运行json函数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("https://api.github.com/gists/c2a7c39532239ff261be")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (!response.isSuccessful())
                    try {
                        throw new IOException("Unexpected code "+response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                Gist gist = gson.fromJson(response.body().charStream(),Gist.class);
                for (Map.Entry<String,GistFile> entry:gist.files.entrySet()){
                    Log.i(TAG,entry.getKey()+":"+entry.getValue().content);

                }
            }
        }).start();


    }


    /**
     * GSON 解析
     */
 /*   public void run() throws IOException {
        Request request = new Request.Builder()
                    .url("https://api.github.com/gists/c2a7c39532239ff261be")
                    .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful())
            throw new IOException("Unexpected code "+response);

        Gist gist = gson.fromJson(response.body().charStream(),Gist.class);
        for (Map.Entry<String,GistFile> entry:gist.files.entrySet()){
            Log.i(TAG,entry.getKey()+":"+entry.getValue().content);

        }

    }*/

    static class Gist{
        Map<String,GistFile> files;
    }

    static class GistFile{
        String content;
    }





    /*private class RequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            *//**
             * 在doInBackground方法中，做一些诸如网络请求等耗时操作。
             *//*
            return RequestData();
        }

        *//**
         * onPostExecute方法主要是主线程中的数据更新。
         *//*
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                //如果获取的result数据不为空，那么对其进行JSON解析。并显示在手机屏幕上。
                JSONAnalysis(result);
            } else if (result == null) {
                Toast.makeText(MainActivity.this, "请求数据失败", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }*/

    /**
     * 网络请求，这里用的是HttpClient，区别于上一篇文章的HttpURLConnection。
     * @return
     *//*
    public String RequestData() {


        HttpGetHC4 getHC4=  new HttpGetHC4(url);

        HttpClients client = new ;
        StringBuilder builder = null;
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                builder = new StringBuilder();
                String s = null;
                for (s = reader.readLine(); s != null; s = reader.readLine()) {
                    builder.append(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    *//**
     * 对请求回来的数据进行JSON解析。
     * @param result
     *//*
    public void JSONAnalysis(String result) {
        JSONObject object = null;
        try {
            object = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject ObjectInfo = object.optJSONObject("weatherinfo");
        String city = ObjectInfo.optString("city");
        String date = ObjectInfo.optString("date_y");
        String week = ObjectInfo.optString("week");
        String temp = ObjectInfo.optString("temp1");
        String weather = ObjectInfo.optString("weather1");
        String index = ObjectInfo.optString("index_d");

        weatherResult = "城市：" + city + "\n日期：" + date + "\n星期：" + week
                + "\n温度：" + temp + "\n天气情况：" + weather + "\n穿衣指数：" + index;
        mTextView.setText(weatherResult);
    }*/

}
