package com.tzxscu.cdfy.AllAsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.tzxscu.cdfy.dbutils.webserviceutil;
import com.mingle.widget.ShapeLoadingDialog;
import com.tzxscu.cdfy.fixed_assets_scan.R;
import com.tzxscu.cdfy.utils.DbrecieveListen;

import org.w3c.dom.Text;


/**
 * Created by Administrator on 2018/3/16.
 */

public class myAsyn extends AsyncTask {

    private Activity mactivity;
    private ShapeLoadingDialog shapeLoadingDialog;

    DbrecieveListen dbrecieveListen;


    public myAsyn(Activity activity){
        super();
        mactivity = activity;
    }

    public void setDbrecieveListen(DbrecieveListen dbrecieveListen){
        this.dbrecieveListen = dbrecieveListen;
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        String ret="";

        webserviceutil webserviceutil = new webserviceutil();
        try {
           ret= webserviceutil.dbupdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(mactivity,this).loadText("加载中").build();
        shapeLoadingDialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        shapeLoadingDialog.dismiss();
        dbrecieveListen.onDataSuccessfully(o);
        Toast.makeText(mactivity,o.toString(),Toast.LENGTH_SHORT).show();
        super.onPostExecute(o);
    }
}
