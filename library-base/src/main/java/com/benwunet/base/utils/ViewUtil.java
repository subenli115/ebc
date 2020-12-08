package com.benwunet.base.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.benwunet.base.model.BaseModel;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by Administrator on 2017/1/6.
 */

public class ViewUtil {

    public static <T extends View> T getView(View rootView, int id) {
        if (rootView == null) return null;
        return (T) rootView.findViewById(id);
    }

    public static String getText(TextView textView) {
        return textView == null ? "" : textView.getText().toString();
    }

    public static void setText(TextView textView, CharSequence text) {
        if (textView != null && !TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }

    public static void setText(View rootView, int id, String text) {
        if (!TextUtils.isEmpty(text)) {
            View viewById = rootView.findViewById(id);
            if (viewById instanceof TextView) {
                ((TextView) viewById).setText(text);
            }
        }
    }

    public static void reqFocus(View view) {
        view.requestFocus();
        view.setFocusableInTouchMode(true);
    }






    public static void changeTabText(TabLayout tabLayout, String[] tabs) {
        if (EmptyUtils.isEmpty(tabs)) return;
        try {
            for (int i = 0; i < tabs.length; i++) {
                if (!TextUtils.isEmpty(tabs[i])) {
                    TabLayout.Tab tab = tabLayout.getTabAt(i);
                    if (tab != null) {
                        tab.setText(tabs[i]);
                    }
                }
            }
//            reflexIndicator(tabLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTabText(TabLayout tabLayout, String[] tabs) {
        if (EmptyUtils.isEmpty(tabs)) return;
        try {
            for (int i = 0; i < tabs.length; i++) {
                if (EmptyUtils.isNotEmpty(tabs[i])) {
                    tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static final String TAG = "ViewUtil";

    private static String sharePath;

    public static String getSharePath() {
        return sharePath;
    }

    public static void setSharePath(String sharePath) {
        ViewUtil.sharePath = sharePath;
    }



    public static <T extends Activity, E extends BaseModel> T getActivity(FragmentActivity activity) {
        if (activity != null) {
            return (T) activity;
        }
        return null;
    }

    public static void setSystemUiHide(Activity activity) {
        setSystemUiHide(activity, false);
    }

    public static void setSystemUiHide(Activity activity, boolean isHideNavigation) {

        if (Build.VERSION.SDK_INT < 16) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            int uiFlags;
            if (isHideNavigation) {
                uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN //hide statusBar
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; //hide navigationBar
            } else {
                uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN;//hide statusBar
            }
            activity.getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        }


        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(lp);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void setSystemUiShow(Activity activity) {
        if (Build.VERSION.SDK_INT < 16) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            int uiFlags = View.SYSTEM_UI_FLAG_VISIBLE;
            activity.getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        }
        WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
        attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setAttributes(attr);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void setViewsShow(int visibility, View... views) {
        if (EmptyUtils.isNotEmpty(views)) {
            for (int i = 0; i < views.length; i++) {
                if (views[i].getVisibility() != visibility) {
                    views[i].setVisibility(visibility);
                }
            }
        }
    }

    public static void setOnClickListener(View headView, int id, View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            View view = getView(headView, id);
            if (view != null) {
                view.setOnClickListener(onClickListener);
            }
        }
    }

    public static void initViewPager(final ViewPager viewPager, final TabLayout tabLayout) {
        if (EmptyUtils.isEmpty(viewPager) || EmptyUtils.isEmpty(tabLayout)) return;
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.setScrollPosition(position, 0, true);
            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static void setHtmlText(TextView tv, String detail) {
        if (tv == null || EmptyUtils.isEmpty(detail)) return;
        if ((detail.contains("<p>") && detail.contains("</p>")) || (detail.contains("<P>") && detail.contains("</P>"))) {
            tv.setText(Html.fromHtml(detail));
        } else {
            tv.setText(detail);
        }
    }

    public static float getBaseLine(Paint paint) {
        return paint !=null ? (Math.abs(paint.ascent()) - paint.descent()) / 2:0;
    }

    public static void setFocusable(View view,boolean focus) {
        if(view!=null && view instanceof TextView){
            view.setFocusable(focus);
            view.setFocusableInTouchMode(focus);
        }
    }

    public static int getColor(int alpha, int color) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }

    public static void removeGlobalLayout(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (view != null && listener != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
            } else {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
            }
        }
    }
    public static void addOnGlobalLayoutListener(final View view, final BaseCallBack call) {
        if (view != null && call != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    removeGlobalLayout(view,this);
                    call.call();
                }
            });
        }
    }

    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (view!=null && drawable!=null){
            if (Build.VERSION.SDK_INT>=16){
                view.setBackground(drawable);
            }else{
                view.setBackgroundDrawable(drawable);
            }

        }
    }

    public interface BaseCallBack {
        void call();
    }
}
