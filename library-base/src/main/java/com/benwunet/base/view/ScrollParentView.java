package com.benwunet.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;

/**
 * author:yoyo
 * date:2020/6/9
 */
public class ScrollParentView extends LinearLayout implements NestedScrollingParent {
    private View topView;
    private View centerView;
    private View contentView;
    private NestedScrollingParentHelper mParentHelper;
    private int imgHeight;
    private int tvHeight;


    public ScrollParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollParentView(Context context) {
        super(context);
        init();
    }

    /**
     * 初始化内部三个子视图
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        topView = getChildAt(0);
        centerView = getChildAt(1);
        contentView = getChildAt(2);
        topView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (imgHeight <= 0) {
                    imgHeight = topView.getMeasuredHeight();
                }
            }
        });
        centerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (tvHeight <= 0) {
                    tvHeight = centerView.getMeasuredHeight();
                }
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), topView.getMeasuredHeight() + centerView.getMeasuredHeight() + contentView.getMeasuredHeight());

    }

    public int getTopViewHeight() {
        return topView.getMeasuredHeight();
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {

        return true;
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);

    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        mParentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    /**
     * 处理上滑和下滑 顶部需要滚动的距离
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        boolean headerScrollUp = dy > 0 && getScrollY() < imgHeight;
        boolean headerScrollDown = dy < 0 && getScrollY() > 0 && !target.canScrollVertically(-1);
        if (headerScrollUp || headerScrollDown) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }


    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > imgHeight) {
            y = imgHeight;
        }

        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return super.onInterceptTouchEvent(event);
    }

}