package com.helbby.helbbyapp.cabg;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;

public class HelbbyActivity extends AppCompatActivity {

    TabLayout tabLayoutHelbby;
    ViewPager viewPagerHelbby;
    private int[] tabIcons = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helbby);

        AccessToken.getCurrentAccessToken();

        tabLayoutHelbby = (TabLayout) findViewById(R.id.tabLayoutHelbby);
        viewPagerHelbby = (ViewPager) findViewById(R.id.viewPagerHelbby);

        tabLayoutHelbby.setupWithViewPager(viewPagerHelbby);
        viewPagerHelbby.setAdapter(new AdapterHelbby(getSupportFragmentManager()));
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayoutHelbby.getTabAt(0).setIcon(tabIcons[0]);
        tabLayoutHelbby.getTabAt(1).setIcon(tabIcons[1]);
        tabLayoutHelbby.getTabAt(2).setIcon(tabIcons[2]);
        tabLayoutHelbby.getTabAt(3).setIcon(tabIcons[3]);
        tabLayoutHelbby.getTabAt(4).setIcon(tabIcons[4]);
    }
}
