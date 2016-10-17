package com.moon.niceweather.activity;

import android.os.Bundle;

import com.moon.niceweather.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class EmptyActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }

}
