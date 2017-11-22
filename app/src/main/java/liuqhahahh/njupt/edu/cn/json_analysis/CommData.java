package liuqhahahh.njupt.edu.cn.json_analysis;

import android.util.Log;

import java.util.List;

/**
 *
 * Created by root on 17-11-21.
 */

public class CommData {


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


   /* public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    private List<Rows> rows;*/





}
