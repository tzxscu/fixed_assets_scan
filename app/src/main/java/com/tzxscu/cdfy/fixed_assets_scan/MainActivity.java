package com.tzxscu.cdfy.fixed_assets_scan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mingle.widget.ShapeLoadingDialog;
import com.tzxscu.cdfy.AllAsyncTask.myAsyn;
import com.tzxscu.cdfy.utils.DbrecieveListen;
import com.tzxscu.cdfy.dbutils.dbcreate;

public class MainActivity extends AppCompatActivity {


    private myAsyn mysyn;
    private ShapeLoadingDialog sa;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.main_page_text);
        Button button = findViewById(R.id.asset_scan_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,code_scan.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                getupdate();
                break;
            case R.id.update:
                Toast.makeText(this,"update",Toast.LENGTH_SHORT).show();
                break;
            case R.id.create_db:
                dbcreate mdbcreate = new dbcreate(this);
                mdbcreate.getWritableDatabase();
            default:
                break;
        }
        return true;
    }

    private boolean getupdate(){
        mysyn = new myAsyn(this);
        mysyn.execute();
        mysyn.setDbrecieveListen(new DbrecieveListen() {
            @Override
            public void onDataSuccessfully(Object data) {
                textView.setText(data.toString());
            }
            @Override
            public void onDataFailed() {

            }
        });
        return  true;
    }

}
