package com.benwunet.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;


public class ScrollControlViewPager extends ViewPager {
	private final String TAG = ScrollControlViewPager.class.getSimpleName();
	private boolean scroll = true;//false 禁止viewpager左右滑动

	public ScrollControlViewPager(Context context) {
		super(context);
	}

	public ScrollControlViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * @param scroll
	 */
	public void setScroll(boolean scroll) {
		this.scroll = scroll;
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (!scroll)
			return false;
		else
			return super.onTouchEvent(arg0);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (!scroll)
			return false;
		else
			return super.onInterceptTouchEvent(arg0);
	}
	@Override
	protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
		if (v != this && v instanceof ViewPager) {
			return true;
		}
		return super.canScroll(v, checkV, dx, x, y);
	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		super.setCurrentItem(item, smoothScroll);
	}

	@Override
	public void setCurrentItem(int item) {
		super.setCurrentItem(item);
	}

}
