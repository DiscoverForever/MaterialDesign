package material.com.cn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import material.com.cn.myapplication.R;

public class AnimationActivity extends AppCompatActivity {
    @ViewInject(R.id.imgView)
    private ImageView imgView;
    private ScaleAnimation sca = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
    private RotateAnimation rotate = new RotateAnimation(0,359,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ViewUtils.inject(this);
    }

    @OnClick({R.id.imgView,R.id.remote,R.id.stop})
    public void click(View v) {
        switch (v.getId()){
            case R.id.imgView:
                sca.setDuration(500);
                imgView.startAnimation(sca);
                break;
            case R.id.remote:
                rotate.setDuration(5000);//动画时间
                rotate.setRepeatCount(-1);//重复次数 -1为循环
                rotate.setInterpolator(new LinearInterpolator());//匀速
                imgView.startAnimation(rotate);
                break;
            case R.id.stop:
                rotate.cancel();
                break;
        }

    }
}
