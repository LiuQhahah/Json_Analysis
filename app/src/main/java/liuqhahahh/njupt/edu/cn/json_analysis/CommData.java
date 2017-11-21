package liuqhahahh.njupt.edu.cn.json_analysis;

import android.util.Log;

import java.util.List;

/**
 * Created by root on 17-11-21.
 */

public class CommData {



    private String retCode;
    private List<CloudantData> rows;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<CloudantData> getCloudantData() {
        return rows;
    }

    public void setCloudantData(List<CloudantData> cloudantData) {
        this.rows = cloudantData;
    }


    public class Rows{
        public String id;
        public String key;
        public String value;
        public Doc doc;
    }
    public class Doc{
        public String _id;
        public String _key;
        public String topic;
        public Payload payload;
    }
    public class Payload{
        public D d;
    }

    public class D{
        public String temp;
        public String humidity;
        public Location location;
    }
    public class Location{
        public String latitude;
        public String longitude;
    }





}
