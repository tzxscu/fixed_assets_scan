package com.tzxscu.cdfy.fixed_assets_scan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.tzxscu.cdfy.dbutils.dbclass;
import com.mingle.widget.ShapeLoadingDialog;
import com.xys.libzxing.zxing.activity.CaptureActivity;


public class code_scan extends AppCompatActivity {


    private static final int REQUEST_CODE_SCAN = 0x0000;
    private ShapeLoadingDialog shapeLoadingDialog;

    private static final String DECODED_CONTENT_KEY = "result";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scan);


       // shapeLoadingDialog = new ShapeLoadingDialog.Builder(this).loadText("加载中。。。").build();

        textView = findViewById(R.id.barcode_res);
        Button button = findViewById(R.id.scan_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(code_scan.this, CaptureActivity.class);
            startActivityForResult(intent,REQUEST_CODE_SCAN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                textView.setText(content);
            } else {
                textView.setText("未找到代码:" + requestCode + ":" + resultCode);
            }
        } else {
            textView.setText("取消:" + requestCode + ":" + resultCode);
        }
    }


}
