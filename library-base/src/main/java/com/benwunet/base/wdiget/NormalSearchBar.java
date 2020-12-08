package com.benwunet.base.wdiget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.benwunet.base.R;

import me.goldze.mvvmhabit.base.AppManager;


public class NormalSearchBar extends RelativeLayout {

    private FrameLayout mCenterContainer;
    private LinearLayout mRightContainer;
    private ImageView ivRight, ivRight2, ivRight3;
    private TextView ivBack, tvRight;
    private RelativeLayout rlCommonTitle;
    private View verLine;
    private int imgWidth;
    private int backStyle;
    private int defPadding;
    private int textRightColor = Color.parseColor("#666666");
    private float textRightSize = 16f;

    public NormalSearchBar(Context context) {
        this(context, null);
    }

    public NormalSearchBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bar_search, this, true);
        ivBack = (TextView) findViewById(R.id.tv_back);
        verLine = findViewById(R.id.verLine);
        mCenterContainer = (FrameLayout) findViewById(R.id.center_container);
        mRightContainer = (LinearLayout) findViewById(R.id.rightContainer);
        rlCommonTitle = (RelativeLayout) findViewById(R.id.common_title);

        defPadding = getResources().getDimensionPixelSize(R.dimen.ntb_padding);
        imgWidth = getResources().getDimensionPixelSize(R.dimen.ntb_img_width);
//        defPadding = (int) TypedValue.applyDimension(1, 12, getResources().getDisplayMetrics());
//        imgWidth = (int) TypedValue.applyDimension(1, 48, getResources().getDisplayMetrics());


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NormalTitleBar);
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_bg)) {
            rlCommonTitle.setBackgroundColor(array.getColor(R.styleable.NormalTitleBar_ntb_bg, Color.TRANSPARENT));
        }
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_img_padding)) {
            defPadding = array.getDimensionPixelSize(R.styleable.NormalTitleBar_ntb_img_padding, defPadding);
//            rlCommonTitle.setBackgroundColor(array.getColor(R.styleable.NormalTitleBar_ntb_bg,Color.TRANSPARENT));
        }

        if (array.hasValue(R.styleable.NormalTitleBar_ntb_tv_left)) {
            int style = array.getInteger(R.styleable.NormalTitleBar_ntb_tv_left, 0);
            switch (style) {
                case 0:
                    setTvLeftVisiable(true, false);
                    break;
                case 1:
                    setTvLeftVisiable(true, true);
                    break;
            }
        }
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_right_text_color)) {
            textRightColor = array.getColor(R.styleable.NormalTitleBar_ntb_right_text_color, textRightColor);
        }
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_right_text_size)) {
            textRightSize = array.getFloat(R.styleable.NormalTitleBar_ntb_right_text_size, 14f);
        }
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_right_text)) {
            createRightText();
            String rightText = array.getString(R.styleable.NormalTitleBar_ntb_right_text);
            tvRight.setText(rightText);
        }
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_right_img_size)) {
            imgWidth = array.getDimensionPixelSize(R.styleable.NormalTitleBar_ntb_right_img_size, (int) TypedValue.applyDimension(1, 48,
                    getResources().getDisplayMetrics()));
        }
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_right_img1)) {
            ivRight = createRightImage(array.getDrawable(R.styleable.NormalTitleBar_ntb_right_img1), R.id.id_right_img1);
        }
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_right_img2)) {
            ivRight2 = createRightImage(array.getDrawable(R.styleable.NormalTitleBar_ntb_right_img2), R.id.id_right_img2);
        }
        if (array.hasValue(R.styleable.NormalTitleBar_ntb_right_img3)) {
            ivRight3 = createRightImage(array.getDrawable(R.styleable.NormalTitleBar_ntb_right_img3), R.id.id_right_img3);
        }

        array.recycle();
    }


    private void createRightText() {
        if (tvRight != null) return;
        tvRight = new TextView(getContext());
        tvRight.setId(R.id.tv_right);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        tvRight.setGravity(Gravity.CENTER);
        tvRight.setPadding(defPadding, 0, defPadding, 0);
        tvRight.setTextColor(textRightColor);
        tvRight.setTextSize(textRightSize);
        tvRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_tran_gray));
        mRightContainer.addView(tvRight, params);
    }


    private ImageView createRightImage(Drawable drawable, int id) {
        ImageView iv = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        if (drawable != null) {
            iv.setImageDrawable(drawable);
        }
        iv.setId(id);
        iv.setPadding(defPadding, defPadding, defPadding, defPadding);
        iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_tran_gray));
        mRightContainer.addView(iv, params);
        return iv;
    }

    /**
     * 加入view
     *
     * @param view
     */
    public void addCenterLayout(View view) {
        mCenterContainer.addView(view);
    }

    /**
     * 管理返回按钮
     * 设置标题栏左侧字符串
     *
     * @param visiable 是否显示
     */
    public void setTvLeftVisiable(boolean visiable) {
        if (visiable) {
            ivBack.setVisibility(View.VISIBLE);
            verLine.setVisibility(INVISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
            verLine.setVisibility(INVISIBLE);
        }
    }

    public void setVerLineVisiable(boolean visiable) {
        if (visiable) {
            verLine.setVisibility(VISIBLE);
        } else {
            verLine.setVisibility(INVISIBLE);
        }
    }


    /**
     * 设置标题栏左侧字符串
     *
     * @param visiable 是否显示
     */
    public void setTvLeftVisiable(boolean visiable, boolean isFinish) {
        Log.e("setTvLeftVisiable", "" + isFinish);
        setTvLeftVisiable(visiable);
        if (isFinish) {
            ivBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("setTvLeftVisiable", "setTvLeftVisiable");
                    KeyBordUtil.hideSoftKeyboard(v);
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).finish();
                    } else {
                        AppManager.getAppManager().currentActivity().finish();
                    }
                }
            });
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param tvLeftText
     */
    public void setTvLeft(String tvLeftText) {
        ivBack.setVisibility(VISIBLE);
        ivBack.setText(tvLeftText);
        ivBack.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }


    /**
     * 右图标
     */
    public void setRightImagVisibility(boolean visible) {
        if (ivRight != null) ivRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 右图标2
     */
    public void setRightImag2Visibility(boolean visible) {
        if (ivRight2 != null) ivRight2.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    public void setRightImag2Src(int id) {
        if (ivRight2 == null) {
            ivRight2 = createRightImage(getResources().getDrawable(id), R.id.id_right_img2);
        }
        ivRight2.setVisibility(View.VISIBLE);
        ivRight2.setImageResource(id);
    }


    /**
     * 获取右按钮
     *
     * @return
     */
    public View getRightImage() {
        return ivRight;
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public String getRightText() {
        if (tvRight != null) {

            return tvRight.getText().toString();
        }
        return "";
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public TextView getRightTextView() {
        return tvRight;
    }

    /**
     * 左图标
     *
     * @param id
     */
    public void setLeftImagSrc(int id) {
        if (ivBack == null) return;
        ivBack.setVisibility(VISIBLE);
        ivBack.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0);

    }

    /**
     * 左文字
     *
     * @param str
     */
    public void setLeftTitle(String str) {
        ivBack.setText(str);
        setLeftImagSrc(0);
    }

    /**
     * 右标题
     */
    public void setRightTitleVisibility(boolean visible) {
        if (tvRight == null) {
            createRightText();
        }
        tvRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    public TextView setRightTitle(String text) {
        if (tvRight == null) {
            createRightText();
        }
        tvRight.setText(text);
        setRightTitleVisibility(true);
        return tvRight;
    }

    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    /*
     * 点击事件
     */
    public void setBackColor() {
        ivBack.setTextColor(Color.WHITE);
    }


    public void setOnRightTextListener(OnClickListener listener) {
        if (tvRight == null) return;
        tvRight.setOnClickListener(listener);
    }

    /**
     * 标题背景颜色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        rlCommonTitle.setBackgroundColor(getResources().getColor(color));
    }

    public void setBackGroundDraw(int color) {
        rlCommonTitle.setBackgroundColor(color);
    }

    public Drawable getBackGroundDrawable() {
        return rlCommonTitle.getBackground();
    }

    public void setTvRightTextColor(int color) {
        tvRight.setTextColor(color);
    }


    public void setCenterView(View view, int width) {
        LayoutParams lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addView(view, params);
    }

    public void setRightToggle() {
        String rightText = tvRight.getText().toString();
        if ("编辑".equals(rightText)) {
            tvRight.setText("取消");
        } else if ("取消".equals(rightText)) {
            tvRight.setText("编辑");
        }
    }


    public void setStyle(int style) {
        switch (style) {
            case 2:
                setBackGroundColor(R.color.transparent);
                if (tvRight != null)
                    tvRight.setTextColor(getResources().getColor(R.color.white));
                setLeftImagSrc(R.mipmap.back_white);
                break;

        }
    }

}
