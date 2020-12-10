package com.benwunet.base.wdiget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.benwunet.base.R;
import com.benwunet.base.utils.EmptyUtils;
import com.benwunet.base.utils.ViewUtil;

import me.goldze.mvvmhabit.utils.ToastUtils;


/**
 * @author feng
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class SharePopupWindow extends BasePopupWindow {

    private View contentViewPop;
    private View background;
    private View rlCode;
    private View rlLink;
    private String copyText;
    private View rlGroup;


    public View getRlGroup() {
        return rlGroup;
    }

    public void setRlGroup(View rlGroup) {
        this.rlGroup = rlGroup;
    }

    public SharePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_share;
    }

    public View getRlCode() {
        return rlCode;
    }

    public void setRlCode(View rlCode) {
        this.rlCode = rlCode;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.background);
        setOnClickListener(R.id.btn_cancel);
        animView = getView(R.id.layout_custom_container);
        rlCode = getView(R.id.rl_code);
        rlGroup = getView(R.id.rl_group);
        rlLink = getView(R.id.rl_link);
        rlLink.setOnClickListener(this);
        background = getView(R.id.background);
        animView.setClickable(false);
        setDurtion(400);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_link) {
            copyLink();
        }
        dismiss();
    }
    public  void setCopyText(String text){
        copyText=text;
    }



    public void copyLink() {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);

        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
                ClipData clipData = ClipData.newPlainText(null, copyText);

        // 把数据集设置（复制）到剪贴板
                clipboard.setPrimaryClip(clipData);
                ToastUtils.showLong("复制成功");
    }


    private String shareTitle;
    private String shareDescript;
    private String shareUrl;

//    private void share(SHARE_MEDIA shareMedia) {
//        ShareUtil.shareWeb1(mContext, shareMedia, getShareDescript(), sharePic, getRealUrl(), getShareTitle(), getShareDescript());
//        if (mCallBack!=null)mCallBack.call();
//    }


    private ViewUtil.BaseCallBack mCallBack;

    public void setCallBack(ViewUtil.BaseCallBack callBack) {
        mCallBack = callBack;
    }

    @NonNull
    private String getRealUrl() {
        if (!isNeedRealUrl()) {
            return getShareUrl();
        }
        if (getShareUrl().contains("?")) {
            return getShareUrl() + "&isShare=1";
        }
        return getShareUrl() + "?isShare=1";
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDescript() {
        return EmptyUtils.isEmpty(shareDescript) ? "" : shareDescript;
    }

    public void setShareDescript(String shareDescript) {
        this.shareDescript = shareDescript;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    private boolean needRealUrl = true;

    public boolean isNeedRealUrl() {
        return needRealUrl;
    }

    public void setNeedRealUrl(boolean needRealUrl) {
        this.needRealUrl = needRealUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    private String sharePic;

    public void setSharePic(String picture) {
        this.sharePic = picture;
    }

//    public static void showPopup(Context activity, SharePopupWindow popupWindow, ShareBean shareBean) {
//        showPopup(activity, popupWindow, shareBean.getShareTitle(), shareBean.getShareDescript(), shareBean.getShareUrl(),shareBean.getSharePic());
//    }

    private static void showPopup1(Context activity, SharePopupWindow popupWindow, String shareTitle, String shareDescript, String shareUrl, String sharePic, String zhibo) {

        if (popupWindow == null) {
            popupWindow = new SharePopupWindow(activity);

        }
        popupWindow.setSharePic(sharePic);
        popupWindow.setShareTitle(shareTitle);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE); //解决pop被底部导航遮挡的问题
        popupWindow.setShareUrl(shareUrl);
        popupWindow.setShareDescript(shareDescript);
        if (activity instanceof Activity) {
            popupWindow.show((Activity) activity);
        } else {
            popupWindow.show();
        }
    }

//    public static void showPopup(Context activity, SharePopupWindow popupWindow, PracticeHeadBean.DataBean musicEntry) {
//        showPopup(activity, popupWindow, musicEntry.getShareTitle(), musicEntry.getShareDescribe(), musicEntry.getShareUrl(),musicEntry.getSharePic());
//    }

    public static void showPopup(Context activity, SharePopupWindow popupWindow, String shareTitle, String shareDescript, String shareUrl, String sharePic) {
        if (popupWindow == null) {
            popupWindow = new SharePopupWindow(activity);
        }
        popupWindow.setSharePic(sharePic);
        popupWindow.setShareTitle(shareTitle);
        popupWindow.setShareUrl(shareUrl);
        popupWindow.setShareDescript(shareDescript);
        if (activity instanceof Activity) {
            popupWindow.show((Activity) activity);
        } else {
            popupWindow.show();
        }
    }


}