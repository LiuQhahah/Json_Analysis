package liuqhahahh.njupt.edu.cn.json_analysis;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;

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

      CommData commData = new CommData();
      List<CloudantData> cloudantDataList = commData.getCloudantData(url);
      Log.i(TAG,"cloudantDataList size:"+cloudantDataList.size());
    }



  /* private void getCloudantData(){
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


           }
       });
   }*/
}
