package com.example.enn.testmd5file;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.enn.testmd5file.md.MD5;
import com.example.enn.testmd5file.md.FileByMD5;
import android.os.StatFs;
import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";
    private Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt1) {
            fileBymd();
        }
        if (v.getId() == R.id.bt2) {
            findFile();
        }
    }
    public void findFile(){
        File file = new File("/sdcard/ennbulehex/ble_app_uart.hex");
        String request = MD5.getFileMd5(file);
        Log.d(TAG,request);
        Toast.makeText(getApplicationContext(), request, 1).show();
    }
    public void fileBymd() {
        File filePath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){// 判断是否插入SD卡
            testBlock();
            FileByMD5 m_md5 = new FileByMD5();
            //String request = m_md5.md5sum(fDir + "/xxxx.xml");
            String request = m_md5.md5sum("/sdcard/ennbulehex/ble_app_uart.hex");
            Log.d(TAG,request);
            Toast.makeText(getApplicationContext(), request, 1).show();
        }
    }

    private void testBlock() {
        File filePath;
        filePath = Environment.getExternalStorageDirectory(); // 获得sdcard的路径
        StatFs stat = new StatFs(filePath.getPath()); // 创建StatFs对象，这个对象很重要SD卡的信息就靠它获取了
        long blockSize = stat.getBlockSize(); // 获得block的大小
        float totalBlocks = stat.getBlockCount(); // 获得总容量
        int sizeInMb = (int) (blockSize * totalBlocks) / 1024 / 1024; // 转换成单位是兆的
        long availableBlocks = stat.getAvailableBlocks(); // 获得可用容量
        float percent = availableBlocks / totalBlocks; // 获得可用比例
        percent = (int) (percent * 1000); // 舍去多余小数位数
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks fileBymd the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
