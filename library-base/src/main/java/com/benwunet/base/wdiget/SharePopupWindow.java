package com.benwunet.base.wdiget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.benwunet.base.R;
import com.benwunet.base.utils.EmptyUtils;
import com.benwunet.base.utils.ViewUtil;


/**
 * @author 吴祖清
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

    public SharePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_share;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.background);
        setOnClickListener(R.id.btn_cancel);
        animView = getView(R.id.layout_custom_container);
        background = getView(R.id.background);
        animView.setClickable(false);
        setDurtion(400);
    }
    @Override
    public void onClick(View v) {
        dismiss();
    }



    private String shareTitle;
    private String shareDescript;
    private String shareUrl;

//    private void share(SHARE_MEDIA shareMedia) {
//        ShareUtil.shareWeb1(mContext, shareMedia, getShareDescript(), sharePic, getRealUrl(), getShareTitle(), getShareDescript());
//        if (mCallBack!=null)mCallBack.call();
//    }


    private ViewUtil.BaseCallBack mCallBack ;

    public void setCallBack(ViewUtil.BaseCallBack callBack) {
        mCallBack = callBack;
    }

    @NonNull
    private String getRealUrl() {
        if (!isNeedRealUrl()){
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