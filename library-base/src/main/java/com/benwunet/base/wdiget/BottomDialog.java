package com.benwunet.base.wdiget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.benwunet.base.R;
import com.benwunet.base.config.GlideEngine;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.util.List;


/**
 * [底部弹出dialog]
 *
 **/
public class BottomDialog extends Dialog implements View.OnClickListener {

    private  ImageView mView;
    private  Activity mContext;
    private Button photographBtn;
    private Button localPhotosBtn;
    private Button cancelBtn;

    private View.OnClickListener confirmListener;
    private View.OnClickListener cancelListener;
    private View.OnClickListener middleListener;

    private String confirmText;
    private String middleText;
    private String cancelText;

    /**
     * @param context
     */
    public BottomDialog(Activity context) {
        super(context, R.style.dialogFullscreen);

        mContext=context;
    }

    /**
     * @param context
     */
    public BottomDialog(Activity context, ImageView view) {
        super(context, R.style.dialogFullscreen);
        mView=view;
        mContext=context;
    }

    /**
     * @param context
     * @param theme
     */
    public BottomDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * @param context
     */
    public BottomDialog(Context context, String confirmText, String middleText) {
        super(context, R.style.dialogFullscreen);
        this.confirmText = confirmText;
        this.middleText = middleText;
    }

    /**
     * @param context
     */
    public BottomDialog(Context context, String confirmText, String middleText, String cancelText) {
        super(context, R.style.dialogFullscreen);
        this.confirmText = confirmText;
        this.middleText = middleText;
        this.cancelText = cancelText;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_bottom);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.5f;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(layoutParams);

        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        photographBtn = (Button) findViewById(R.id.photographBtn);
        localPhotosBtn = (Button) findViewById(R.id.localPhotosBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        if (!TextUtils.isEmpty(confirmText)) {
            photographBtn.setText(confirmText);
        }
        if (!TextUtils.isEmpty(middleText)) {
            localPhotosBtn.setText(middleText);
        }
        if (!TextUtils.isEmpty(cancelText)) {
            cancelBtn.setText(cancelText);
        }

        cancelBtn.setOnClickListener(this);
        photographBtn.setOnClickListener(this);
        localPhotosBtn.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dismiss();
        return true;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photographBtn) {
            PictureSelector.create(mContext)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .isEnableCrop(true)
                    .maxSelectNum(1)
                    .selectionMode(PictureConfig.SINGLE)
                    .withAspectRatio(1, 1)
                    .forResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(List<LocalMedia> result) {
                            Glide.with(mContext).load(result.get(0).getCutPath()).into(mView);
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                dismiss();
            return;
        }
        if (id == R.id.localPhotosBtn) {
            PictureSelector.create(mContext)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .maxSelectNum(1)
                    .isEnableCrop(true)
                    .withAspectRatio(1, 1)
                    .selectionMode(PictureConfig.MULTIPLE)
                    .forResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(List<LocalMedia> result) {
                            Glide.with(mContext).load(result.get(0).getCutPath()).into(mView);

                        }

                        @Override
                        public void onCancel() {

                        }
                    });
               dismiss();
            return;
        }
        if (id == R.id.cancelBtn) {
            if (cancelListener != null) {
                cancelListener.onClick(v);
            }
            dismiss();
            return;
        }
    }

    public View.OnClickListener getConfirmListener() {
        return confirmListener;
    }

    public void setConfirmListener(View.OnClickListener confirmListener) {
        this.confirmListener = confirmListener;
    }

    public View.OnClickListener getCancelListener() {
        return cancelListener;
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    public View.OnClickListener getMiddleListener() {
        return middleListener;
    }

    public void setMiddleListener(View.OnClickListener middleListener) {
        this.middleListener = middleListener;
    }
}
