package material.com.cn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;


import org.json.JSONArray;


import java.util.ArrayList;

import material.com.cn.module.Product;
import material.com.cn.myapplication.R;

public class GSONActivity extends AppCompatActivity {
    ArrayList<Product> list = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        testGSON();

    }

    private void testGSON() {
        JSONArray jsonArray;

        Gson gson = new Gson();
        Product product = new Product();
        String json = "[{'name':'kevin','age':25},{'name':'cissy','age':24}]";
        try {
            jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                product = gson.fromJson(jsonArray.get(i).toString(), Product.class);
                list.add(product);
            }
        } catch (Exception e) {

        }

        Toast.makeText(GSONActivity.this, list.get(1).getName(), Toast.LENGTH_SHORT).show();
    }
}
