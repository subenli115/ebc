package com.benwunet.sign.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.benwunet.base.utils.ArrayUtils;
import com.benwunet.sign.R;
import com.benwunet.sign.ui.fragment.CodeLoginFragment;
import com.benwunet.sign.ui.fragment.FaceLoginFragment;
import com.benwunet.sign.ui.fragment.PwdLoginFragment;

import java.util.ArrayList;
import java.util.List;


public class TabPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] strings;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext=context;
        strings = ArrayUtils.getArray(mContext, R.array.sign_tabs);
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Fragment getItem(int position) {
        fragmentList.add(new FaceLoginFragment());
        fragmentList.add(new PwdLoginFragment());
        fragmentList.add(new CodeLoginFragment());
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
