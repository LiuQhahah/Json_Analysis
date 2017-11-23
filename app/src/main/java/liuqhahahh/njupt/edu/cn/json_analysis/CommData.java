package liuqhahahh.njupt.edu.cn.json_analysis;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * Created by root on 17-11-21.
 */

public class CommData {
    private static final String TAG = "CommData";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    private List<CloudantData> cloudantDataList;
    public List<CloudantData> getRows() {
        return rows;
    }

    public void setRows(List<CloudantData> rows) {
        this.rows = rows;
    }

    private List<CloudantData> rows;

    public String getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(String total_rows) {
        this.total_rows = total_rows;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }



    private String total_rows;
    private String offset;

    public List<CloudantData> getCloudantData(String url){
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


                cloudantDataList = commData.getRows();

            /*    Log.i(TAG,"cloudantDataList size:"+cloudantDataList.size());
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
                }*/


            }
        });

        return cloudantDataList;
    }
    }
