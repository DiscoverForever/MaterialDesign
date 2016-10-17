package material.com.cn.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import material.com.cn.myapplication.R;
import material.com.cn.adapter.ImagListAdapter;

public class ImageListActivity extends AppCompatActivity {
    @ViewInject(R.id.listView)
    private PullToRefreshListView imgList;
    private Context context;
    private ImagListAdapter adapter;
    private ArrayList<String> dataList;
    private Boolean isRefreshing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        ViewUtils.inject(this);
        context = this;
        init();

    }

    private void init() {
        imgList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });
        adapter = new ImagListAdapter(context, getList());
        imgList.setAdapter(adapter);
        imgList.setMode(PullToRefreshBase.Mode.BOTH);//两端刷新
        imgList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                ILoadingLayout startLabels = imgList
                        .getLoadingLayoutProxy(true, false);
                startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
                startLabels.setRefreshingLabel("正在载入...");// 刷新时
                startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
                imgList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgList.onRefreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                ILoadingLayout endLabels = imgList.getLoadingLayoutProxy(
                        false, true);
                endLabels.setPullLabel("上拉加载...");// 刚下拉时，显示的提示
                endLabels.setRefreshingLabel("正在载入...");// 刷新时
                endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
                imgList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgList.onRefreshComplete();
                    }
                }, 1000);
            }

        });

    }

    private ArrayList<String> getList() {
        dataList = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            dataList.add("http://scimg.jb51.net/allimg/160716/105-160G61F250436.jpg");
            dataList.add("http://img4.imgtn.bdimg.com/it/u=2862293121,1590831239&fm=21&gp=0.jpg");
            dataList.add("http://pic1.ooopic.com/uploadfilepic/sheji/2008-11-04/OOOPIC_terryliu_20081104f2fe7dd50a8f5b2e.jpg");
            dataList.add("http://img3.3lian.com/2013/c2/26/d/79.jpg");
            dataList.add("http://img.taopic.com/uploads/allimg/120901/219077-120Z122105651.jpg");
            dataList.add("http://scimg.jb51.net/allimg/160716/105-160G61F250436.jpg");
            dataList.add("http://img4.imgtn.bdimg.com/it/u=2862293121,1590831239&fm=21&gp=0.jpg");
            dataList.add("http://pic1.ooopic.com/uploadfilepic/sheji/2008-11-04/OOOPIC_terryliu_20081104f2fe7dd50a8f5b2e.jpg");
            dataList.add("http://img3.3lian.com/2013/c2/26/d/79.jpg");
        }
        return dataList;
    }
}
