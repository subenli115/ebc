package com.benwunet.find.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.benwunet.base.utils.ArrayUtils;
import com.benwunet.find.R;
import com.benwunet.find.ui.fragment.FindFollowFragment;
import com.benwunet.find.ui.fragment.FindNearbyFragment;
import com.benwunet.find.ui.fragment.FindRecommendFragment;

import java.util.ArrayList;
import java.util.List;


public class FindTabPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] strings;

    public FindTabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext=context;
        strings = ArrayUtils.getArray(mContext, R.array.find_tabs);
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Fragment getItem(int position) {
        fragmentList.add(new FindRecommendFragment());
        fragmentList.add(new FindFollowFragment());
        fragmentList.add(new FindNearbyFragment());
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
