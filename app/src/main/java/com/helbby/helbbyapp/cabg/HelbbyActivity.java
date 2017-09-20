package com.helbby.helbbyapp.cabg;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;

public class HelbbyActivity extends AppCompatActivity {

    TabLayout tabLayoutHelbby;
    ViewPager viewPagerHelbby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helbby);

        AccessToken.getCurrentAccessToken();

        tabLayoutHelbby = (TabLayout) findViewById(R.id.tabLayoutHelbby);
        viewPagerHelbby = (ViewPager) findViewById(R.id.viewPagerHelbby);

        tabLayoutHelbby.setupWithViewPager(viewPagerHelbby);
        viewPagerHelbby.setAdapter(new AdapterHelbby(getSupportFragmentManager()));
    }
}
