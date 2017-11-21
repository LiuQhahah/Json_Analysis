package liuqhahahh.njupt.edu.cn.json_analysis;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.CacheResponse;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mTextView;
    private String url = "http://m.weather.com.cn/data/101010100.html";
    protected CharSequence weatherResult = null;

    ///private final OkHttpClient client4cache = new OkHttpClient() ;
   //private final OkHttpClient client = new OkHttpClient();
   private final Gson gson = new Gson();
    private OkHttpClient client4cache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Gson gson1 = new Gson();
        //创建JavaBean类的对象
        EntityStudent student = new EntityStudent();
        String json = "{\"id\":1,\"name\":\"小明\",\"sex\":\"男\",\"age\":18,\"height\":175}";
        //用GSON方法将JSON数据转为单个类实体

        student = gson1.fromJson(json,EntityStudent.class);
        //调用student方法展示解析的数据
        student.show();*/

        /**
         * 使用异步加载技术。
         */
        //new RequestTask().execute();

        //主线程中不给进行网络请求，开启子线程：运行json函数
      /*  new Thread(new Runnable() {
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

                // 最为关键！！的方法
                Gist gist = gson.fromJson(response.body().charStream(),Gist.class);
                //Log.i(TAG,"response:"+response.body().);
                for (Map.Entry<String,GistFile> entry:gist.files.entrySet()){
                    Log.i(TAG,entry.getKey()+":"+entry.getValue().content);
                }
            }
        }).start();*/

        /**
         *  通过JsonReader解析Json对象
         */
        //jsonReaderTest();





        //okhttp    添加缓存

        int cacheSize = 10*1024*1024;//10 MiB
        String okhttpCachePath = getApplication().getCacheDir().getPath()+ File.separator+"okhttp";
        File   okhttpCache = new File(okhttpCachePath);
        if (!okhttpCache.exists()){
            okhttpCache.mkdirs();
        }

        Cache cache = new Cache(okhttpCache,cacheSize);

        client4cache = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        /**
         * okhttp 4 cache
         *
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request4cache = new Request.Builder()
                        .url("http://publicobject.com/helloworld.txt")
                        .build();


                try {
                    Response response4cache1 = client4cache.newCall(request4cache).execute();

                if (!response4cache1.isSuccessful()) throw new IOException("Unexpected code"+response4cache1);

                    String response4cache1Body = response4cache1.body().string();
                    Log.i(TAG,"Response4cache 1 response :"+response4cache1);
                    Log.i(TAG,"Response4cache 1 cache response :"+response4cache1.cacheResponse());
                    Log.i(TAG,"Response4cache 1 network response :"+response4cache1.networkResponse());


                    Response response4cache2 = client4cache.newCall(request4cache).execute();
                    if (!response4cache2.isSuccessful()) throw new IOException("Unexpected code"+response4cache2);

                    String response4cache2Body = response4cache2.body().string();
                    Log.i(TAG,"Response4cache 2 response :"+response4cache2);
                    Log.i(TAG,"Response4cache 2 cache response :"+response4cache2.cacheResponse());
                    Log.i(TAG,"Response4cache 2 network response :"+response4cache2.networkResponse());


                    Log.i(TAG,"Response4cache 2 equals Response4cache 1 ?"+response4cache2Body.equals(response4cache1Body));



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Gist{
        Map<String,GistFile> files;
    }

    static class GistFile{
        String content;
    }


    /**
     *  通过JsonReader解析Json对象
     */
   /* private void jsonReaderTest(){
        String jsonData = "[{\"username\":\"name01\",\"userId\":001},{\"username\":\"name02\",\"userId\":002}]";

        com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new StringReader(jsonData));
        reader.setLenient(true);//在宽松情况下解析

        try {
            reader.beginArray();//开始解析数据
            while (reader.hasNext()){//如果有下一个数组，则继续解析
                reader.beginObject();//开始解析一个新的对象
                while (reader.hasNext()){
                    String tagname = reader.nextName();//获取下一个属性名
                    if (tagname.equals("username")){
                        Log.i(TAG,"username:"+reader.nextString());
                    }else if (tagname.equals("userId")){
                        Log.i(TAG,"userId:"+reader.nextString());
                    }
                }
                reader.endObject();//结束对象的解析

            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * okhttp 4 cache
     * @param cacheDirectory
     */
 /*  public void CacheResponse(File cacheDirectory){
       int cacheSize = 10*1024*1024;//10 MiB
       String okhttpCachePath = getCacheDir().getPath()+ File.separator+"okhttp";
       File   okhttpCache = new File(okhttpCachePath);
       if (!okhttpCache.exists()){
           okhttpCache.mkdirs();
       }

       Cache cache = new Cache(okhttpCache,cacheSize);

       client4cache= new OkHttpClient.Builder()
               .cache(cache)
               .build();
   }*/
}
