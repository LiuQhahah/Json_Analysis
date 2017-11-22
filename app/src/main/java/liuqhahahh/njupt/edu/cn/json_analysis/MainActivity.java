package liuqhahahh.njupt.edu.cn.json_analysis;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

   private final OkHttpClient client = new OkHttpClient();
   private final Gson gson = new Gson();


   private final String url = "https://fb2e1a82-8890-4143-810d-e5c79f44a611-bluemix.cloudant.com/nodered/_all_docs?include_docs=true&limit=100";
    private String humidity;
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * 主线程中不给进行网络请求，开启子线程：运行json函数
         */

       /* new Thread(new Runnable() {
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
        }).start();

*/

       getCloudantData();
    }

   /* static class Gist{
        Map<String,GistFile> files;
    }

    static class GistFile{
        String content;
    }*/

   private void getCloudantData(){
       Request request   = new Request.Builder()
               .url(url)

               .cacheControl(CacheControl.FORCE_NETWORK)
               .build();

       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               Log.e(TAG,"okhttp is request error");
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG,"okhttp is request success!");
                //获取服务器返回的json字符串
               String responseString = response.body().string();
               Log.i(TAG,"获取字符串responseString:"+responseString);
               //使用Gson 解析json字符串
               CommData commData  = gson.fromJson(responseString,CommData.class);


               List<CloudantData> cloudantDataList = commData.getRows();

               Log.i(TAG,"cloudantDataList size:"+cloudantDataList.size());
               for (int i= 0;i<cloudantDataList.size();i++){
                   final String temp = cloudantDataList.get(i).doc.payload.d.getTemp();
                   final String humidity = cloudantDataList.get(i).doc.payload.d.getHumidity();
                   //在主线程中修改UI
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           //进行图标刷新。。。。
                           Log.i(TAG,"temp:"+temp+"\n humidity:"+humidity);
                       }
                   });
               }

            /*   List<Rows> rows =  commData.getRows();
               Log.i(TAG,"cloudantDataList size:"+rows.size());
               for (int i= 0;i<rows.size();i++){
                    temp =rows.get(i).getDoc().getPayload().getD().getTemp();
                    humidity = rows.get(i).getDoc().getPayload().getD().getHumidity();
                   //在主线程中修改UI
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           //进行图标刷新。。。。
                           Log.i(TAG,"temp:"+temp+"\n humidity:"+humidity);
                       }
                   });
               }*/
           }
       });
   }
}
