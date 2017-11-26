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



    public CommData() {

    }



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


}
