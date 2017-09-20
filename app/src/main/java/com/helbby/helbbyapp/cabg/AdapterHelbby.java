package com.helbby.helbbyapp.cabg;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class AdapterHelbby extends FragmentPagerAdapter {

    public AdapterHelbby(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new PregHelbby();
        }else if (position == 1){
            return new CategoriasHelbby();
        }else if (position == 2){
            return new PrincipalHelbby();
        }else if (position == 3){
            return new ChatHelbby();
        }else if (position == 4){
            return new PerfilHelbby();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
    
}
