package com.benwunet.home.ui.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.model.BaseViewModel;
import com.benwunet.base.utils.QRCodeUtil;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.ActivityHomeCardCodeBinding;
import com.benwunet.home.ui.bean.CodeBean;
import com.google.gson.Gson;

import java.util.Date;

import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.home.ui.activity
 * @ClassName: HomeCodeActivity
 * @Description: 本无名片二维码界面
 * @Author: feng
 * @CreateDate: 2020/11/10 0010 15:51
 * @Version: 1.0
 */

public class HomeCodeActivity extends BaseActivity<ActivityHomeCardCodeBinding, BaseViewModel> {
    private Context mContext;
    private String name;
    private String cardId;
    private boolean isRepresentative;
    private String companyName;
    private Bitmap qrImage;
    //拿到路由过来的参数

    public static void startAction(String name, String cardId, boolean isRepresentative, String companyName) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, HomeCodeActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("cardId", cardId);
        intent.putExtra("companyName", companyName);
        intent.putExtra("isRepresentative", isRepresentative);
        activity.startActivity(intent);
    }

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_home_card_code;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     *  /**
     *  * 生成二维码Bitmap
     *  *
     *  * @param content 内容
     *  * @param widthPix 图片宽度
     *  * @param heightPix 图片高度
     *  * @param logoBm 二维码中心的Logo图标（可以为null）
     *  * @param filePath 用于存储二维码图片的文件路径
     *  * @return 生成二维码及保存文件是否成功
     */
    @Override
    public void initData() {
        mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
            cardId = intent.getStringExtra("cardId");
            companyName = intent.getStringExtra("companyName");
            isRepresentative = intent.getBooleanExtra("isRepresentative", false);
        }
        if (isRepresentative) {
            binding.llBusiness.setVisibility(View.VISIBLE);
            binding.tvBusinessName.setText(name);
            binding.tvCompany.setText(companyName);
        }
        binding.tvName.setText(name);
        binding.tvSave.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                MediaStore.Images.Media.insertImage(mContext.getContentResolver(), qrImage, "title", "description");
                ToastUtils.showLong("保存成功");
            }
        });
        initImageCode();
    }

    /**
     *
     * 生成二维码图片
     */
    private void initImageCode() {
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap logo = null;
        if (drawable instanceof BitmapDrawable) {
            logo = ((BitmapDrawable) drawable).getBitmap();
        }
        //生成json然后根据json生成二维码
        //json格式 {"type":"user","user":{"appkey":"4f7aef34fb361292c566a1cd","platform":"a","username":"nnnn"}}
        CodeBean codeBean = new CodeBean();
        long time = new Date().getTime();
        codeBean.setDate("" + time);
        Gson gson = new Gson();
        String content = gson.toJson(codeBean);
         qrImage = QRCodeUtil.createQRImage(content, 361, 361, logo, "");
        if(isRepresentative){
            binding.ivBusinessCode.setImageBitmap(qrImage);
        }else {
            binding.ivCode.setImageBitmap(qrImage);
        }
    }


}
