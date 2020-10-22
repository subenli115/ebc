package com.benwunet.sign.ui.respository;



import android.util.Base64;
import android.util.Log;

import com.benwunet.base.global.ApiKey;
import com.benwunet.base.utils.GsonUtils;
import com.benwunet.base.utils.RSAUtil;
import com.benwunet.base.utils.RSAUtils;
import com.benwunet.base.utils.ToastUtil;
import com.benwunet.sign.ui.bean.VerifyCodeBean;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.ApiResult;
import com.zhouyou.http.request.HttpManager;

import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 * Created by feng on 2020/10/21.
 */
public class SignRepository extends BaseModel  {
    private static final SignRepository instance = new SignRepository();

    private SignRepository()  {
    }

    public static SignRepository getInstance() {
        return instance;
    }

    public SingleLiveEvent<Boolean> getVerifyCode(String phone, final SingleLiveEvent<Boolean> verifyCode, String type) {
        HttpManager.get(ApiKey.NOTIFY_SMS)
                .params("mobile", phone)
                .params("type",type)
                .cacheKey(this.getClass().getSimpleName())
                .execute(new CallBackProxy<ApiResult<String>, String>(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        VerifyCodeBean bean = GsonUtils.fromLocalJson(result,VerifyCodeBean.class);
                        if(bean.getCode()==0){
                            verifyCode.setValue(true);
                        }else {
                            ToastUtils.showLong(bean.getMessage());
                        }

                    }
                }) {
                });
        return verifyCode;
    }

    public SingleLiveEvent<Boolean> Login(String phone, final SingleLiveEvent<Boolean> verifyCode) {
        HttpManager.get(ApiKey.OAUTH_SMS)
                .params("mobile", phone)
                .params("type","RES")
                .cacheKey(this.getClass().getSimpleName())
                .execute(new CallBackProxy<ApiResult<String>, String>(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        VerifyCodeBean bean = GsonUtils.fromLocalJson(result,VerifyCodeBean.class);
                        if(bean.getCode()==0){
                            verifyCode.setValue(true);
                        }else {
                            ToastUtils.showLong(bean.getMessage());
                        }

                    }
                }) {
                });
        return verifyCode;
    }

    public SingleLiveEvent<Boolean> register(String phone, final SingleLiveEvent<Boolean> verifyCode) {
        HttpManager.get(ApiKey.MEMBER_REG)
                .params("mobile", phone)
                .params("password",RSAUtils.encrypt("s66225303"))
                .params("confirm",RSAUtils.encrypt("s66225303"))
                .params("code","RES")
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                    }

                    @Override
                    public void onSuccess(String o) {

                    }
                });
        return verifyCode;
    }






}
