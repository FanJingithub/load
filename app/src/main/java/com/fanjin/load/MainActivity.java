package com.fanjin.load;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    String packname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        packname = "com.feasycom.feasyblue";
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();
                if (checkPackInfo(packname)) {
                    Intent intent = packageManager.getLaunchIntentForPackage(packname);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "没有安装" + packname, Toast.LENGTH_LONG).show();
                }

                connect();

            }
        });


    }

    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    private void connect() {

        EchoWebSocketListener listener = new EchoWebSocketListener();
        Request request = new Request.Builder()
                .url("ws://118.89.111.214:2333/api/status")
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newWebSocket(request, listener);

        client.dispatcher().executorService().shutdown();
    }
}
