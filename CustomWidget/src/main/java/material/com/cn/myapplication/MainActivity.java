package material.com.cn.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyView myView = (MyView) this.findViewById(R.id.myView);
//        myView.setShowText(true);
//        Toast.makeText(MainActivity.this, ""+ myView.getShowText(), Toast.LENGTH_SHORT).show();
    }
}
