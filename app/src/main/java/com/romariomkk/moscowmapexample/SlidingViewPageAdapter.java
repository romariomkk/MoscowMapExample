package com.romariomkk.moscowmapexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romariomkk on 29.12.2016.
 */
public class SlidingViewPageAdapter extends FragmentPagerAdapter {

    public static final String TITLE = "title";

    private List<Fragment> fragList = new ArrayList<>();

    public SlidingViewPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragList.get(position).getArguments().getString(TITLE);
    }

    public SlidingViewPageAdapter addFragment(Fragment frag) {
        fragList.add(frag);
        return this;
    }
}
