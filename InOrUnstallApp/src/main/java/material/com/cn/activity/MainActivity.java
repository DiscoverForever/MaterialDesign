package material.com.cn.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

import material.com.cn.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private Button bt_unstall;
    private Button bt_install;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_unstall = (Button) this.findViewById(R.id.bt_unstall);
        bt_unstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:material.com.cn.myapplication"));
                startActivity(intent);

            }
        });
        bt_install = (Button) this.findViewById(R.id.bt_install);
        bt_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);

                File file = new File(Environment.getExternalStorageDirectory(), "Download/200000.apk");
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

                startActivity(intent);

            }
        });
    }
}
