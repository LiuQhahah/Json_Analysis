package liuqhahahh.njupt.edu.cn.json_analysis;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mTextView;
    private String url = "http://m.weather.com.cn/data/101010100.html";
    protected CharSequence weatherResult = null;

   private final OkHttpClient client = new OkHttpClient();
   private final Gson gson = new Gson();
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
        jsonReaderTest();
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
    private void jsonReaderTest(){
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
    }
}
