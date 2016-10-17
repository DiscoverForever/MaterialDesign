package material.com.cn.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import material.com.cn.myapplication.R;

public class CameraActivity extends AppCompatActivity {
    @ViewInject(R.id.btn_takePhoto)
    private Button btn_takePhoto;
    @ViewInject(R.id.btn_photoLib)
    private Button btn_photoLib;
    @ViewInject(R.id.iv_photo)
    private ImageView iv_photo;
    private final int REQUEST_CODE_CAPTURE_CAMEIA = 0;
    private final int REQUEST_CODE_PHOTO_LIB = 1;
    private final int PHOTO_PICKED_WITH_DATA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ViewUtils.inject(this);
        init();
    }

    private void init() {

    }

    @OnClick({R.id.btn_takePhoto, R.id.btn_photoLib})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_takePhoto:
                getImageFromCamera();
                break;
            case R.id.btn_photoLib:
                getImageFromPhotoLib();
                break;
        }

    }

    private void getImageFromPhotoLib() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");

        startActivityForResult(intent, REQUEST_CODE_PHOTO_LIB);
    }

    protected void getImageFromCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
        } else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case REQUEST_CODE_CAPTURE_CAMEIA:
                if (resultCode == Activity.RESULT_OK) {
                    final Bitmap photo = data.getParcelableExtra("data");
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setType("image/*");
                    intent.putExtra("data", photo);
                    intent.putExtra("crop", "true");
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 128);
                    intent.putExtra("outputY", 128);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);

                }
                break;
            case REQUEST_CODE_PHOTO_LIB:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    try {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        iv_photo.setImageBitmap(bitmap);

                    } catch (Exception e) {

                    }
                }
                break;
            case PHOTO_PICKED_WITH_DATA:
                final Bitmap photo = data.getParcelableExtra("data");
                if(photo!=null) {
                    iv_photo.setImageBitmap(photo);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
