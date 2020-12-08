package com.benwunet.cards.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.benwunet.base.contract.AppConstans;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.livedatas.LiveDataBus;
import com.benwunet.base.utils.FileUtils;
import com.benwunet.base.utils.ToastUtil;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.cards.BR;
import com.benwunet.cards.R;
import com.benwunet.cards.databinding.ActivityCardsPagerBinding;
import com.benwunet.cards.service.RecognizeService;
import com.benwunet.cards.ui.adapter.CardsPagerAdapter;
import com.benwunet.cards.ui.bean.CardInfoBean;
import com.benwunet.cards.ui.viewmodel.CardsViewModel;
import com.benwunet.cards.ui.viewmodel.DynamicItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.ToastUtils;


/**
 * @Package: com.benwunet.cards.ui.activity
 * @ClassName: CardsPaperActivity
 * @Description: 纸质卡片
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class CardsPaperActivity extends BaseActivity<ActivityCardsPagerBinding, CardsViewModel> {
    private static final String API_KEY = "di2ablb8RruQB6VX93B3RKr0";//替换成自己的api_key
    private static final String SECRET_KEY = "Gxu0pYGaWijcH3RO1aaTqc6rh8fCXp6S";//请替换成你的Secret key
    private boolean hasGotToken;
    private Context mContext;
    private static final int REQUEST_CODE_BUSINESSCARD = 128;
    private CardsPagerAdapter adapter;

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cards_pager;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        mContext = this;
        initAccessTokenWithAkSk();
        initAdapter();
        LiveDataBus.get().with(AppConstans.BusTag.ADDBEAN).observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object wordsResultBean) {
                CardInfoBean.WordsResultBean wordsResultBean1 = (CardInfoBean.WordsResultBean) wordsResultBean;
                DynamicItemViewModel themesItemViewModel1 = new DynamicItemViewModel();
                themesItemViewModel1.isVisible=false;
                themesItemViewModel1.coverUrl=wordsResultBean1.getPath();
                adapter.addData(themesItemViewModel1);

            }
        });
    }

    private void initAdapter() {
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setNestedScrollingEnabled(false);
        binding.recyclerview.setLayoutManager(new GridLayoutManager(mContext,2));
        List<BaseCustomViewModel> data = new ArrayList<>();
        DynamicItemViewModel themesItemViewModel1 = new DynamicItemViewModel();
        themesItemViewModel1.isVisible=true;
        data.add(themesItemViewModel1);
         adapter = new CardsPagerAdapter(R.layout.item_cards_pager,mContext);
        adapter.addChildClickViewIds(R.id.rl_add);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication(), CameraActivity.CONTENT_TYPE_GENERAL).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent, REQUEST_CODE_BUSINESSCARD);
            }
        });
        adapter.setNewData(data);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(CardsPagerDetailsActivity.class);
            }
        });
        binding.recyclerview.setAdapter(adapter);

    }

    //通过AK/SK的方式获得AccessToken。
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                // 调用成功，返回AccessToken对象
                String token = result.getAccessToken();
                Log.i("token", "token:-------->" + token);
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
            }
        }, getApplicationContext(), API_KEY, SECRET_KEY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，名片
        if (requestCode == REQUEST_CODE_BUSINESSCARD && resultCode == Activity.RESULT_OK) {
            RecognizeService.recBusinessCard(this, FileUtils.getSaveFile(getApplication(), CameraActivity.CONTENT_TYPE_GENERAL).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            try {
                                Gson gson = new Gson();
                                Type type = new TypeToken<CardInfoBean>() {}.getType();
                                CardInfoBean cardInfoBean = gson.fromJson(result, type);
                                cardInfoBean.getWords_result().setPath(FileUtils.getSaveFile(getApplication(), CameraActivity.CONTENT_TYPE_GENERAL).getAbsolutePath());
                                Intent intent = new Intent(mContext,CardsPagerEditActivity.class);
                                intent.putExtra("result",cardInfoBean);
                                startActivity(intent);
                            }catch (Exception e){
                                ToastUtils.showLong("不能解析的图片");
                            }
                        }
                    });
        }
    }

}