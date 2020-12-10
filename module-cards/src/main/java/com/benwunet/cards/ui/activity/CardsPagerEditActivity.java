package com.benwunet.cards.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.contract.AppConstans;
import com.benwunet.base.livedatas.LiveDataBus;
import com.benwunet.base.utils.FileUtils;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.cards.BR;
import com.benwunet.cards.R;
import com.benwunet.cards.databinding.ActivityCardsEditPagerBinding;
import com.benwunet.cards.ui.bean.CardInfoBean;
import com.benwunet.cards.ui.viewmodel.CardsViewModel;

/**
 * @Package: com.benwunet.cards.ui.activity
 * @ClassName: CardsPagerEditActivity
 * @Description: 编辑纸质卡片
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class CardsPagerEditActivity extends BaseActivity<ActivityCardsEditPagerBinding, CardsViewModel> {

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cards_edit_pager;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        initCardInfo();
    }

    private void initCardInfo() {
         CardInfoBean result = (CardInfoBean) getIntent().getSerializableExtra("result");
        final CardInfoBean.WordsResultBean bean = result.getWords_result();
        Bitmap bitmap= BitmapFactory.decodeFile(bean.getPath());

        binding.ivCard.setImageBitmap(bitmap);
        binding.etAddress.setText(bean.getADDR().get(0));
        binding.etCompany.setText(bean.getCOMPANY().get(0));
        binding.etName.setText(bean.getNAME().get(0));
        binding.etUrl.setText(bean.getURL().get(0));
        binding.etPhone.setText(bean.getMOBILE().get(0));
        binding.etMail.setText(bean.getEMAIL().get(0));
        binding.tvSave.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
                LiveDataBus.get().with(AppConstans.BusTag.ADDBEAN).setValue(bean);
            }
        });
    }


}
