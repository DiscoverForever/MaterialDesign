package material.com.cn.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.SendReceiver)
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);


    }

    @OnClick({R.id.SendReceiver,R.id.StartService,R.id.StopService})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.StartService:
                Toast.makeText(MainActivity.this, "sssss", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,MyService.class);
                i.putExtra("data","test");
                startService(i);
                break;
            case R.id.StopService:
                stopService(new Intent(this,MyService.class));
                break;
            case R.id.SendReceiver:
                Intent intent = new Intent("MyReceiver");
                intent.putExtra("msg", "Hello Receiver");
                sendBroadcast(intent);
                break;
        }

    }
}
