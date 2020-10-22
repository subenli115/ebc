package com.benwunet.sign.ui.respository;



import com.benwunet.base.global.ApiKey;
import com.benwunet.base.utils.GsonUtils;
import com.benwunet.base.utils.MapUtils;
import com.benwunet.base.utils.RSAUtils;
import com.benwunet.sign.ui.bean.UserBean;
import com.benwunet.sign.ui.bean.StringDataBean;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.ApiResult;
import com.zhouyou.http.request.HttpManager;

import java.util.Map;

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
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        StringDataBean bean = GsonUtils.fromLocalJson(result, StringDataBean.class);
                        if(bean.getCode()==0){
                            verifyCode.setValue(true);
                        }else {
                            ToastUtils.showLong(bean.getMessage());
                        }

                    }
                });
        return verifyCode;
    }

    public SingleLiveEvent<Boolean> Login(String phone, final SingleLiveEvent<Boolean> verifyCode) {
        HttpManager.get(ApiKey.OAUTH_SMS)
                .params("mobile", phone)
                .params("type","RES")
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        StringDataBean bean = GsonUtils.fromLocalJson(result, StringDataBean.class);
                        if(bean.getCode()==0){
                            verifyCode.setValue(true);
                        }else {
                            ToastUtils.showLong(bean.getMessage());
                        }

                    }

                });
        return verifyCode;
    }

    public SingleLiveEvent<UserBean> codeLogin(String phone, final SingleLiveEvent<UserBean> user) {
        HttpManager.get(ApiKey.OAUTH_SMS)
                .params("mobile", phone)
                .params("type","RES")
                .headers("client_id","")
                .headers("client_secret","")
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<UserBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(UserBean result) {
//                        VerifyCodeBean bean = GsonUtils.fromLocalJson(result,VerifyCodeBean.class);
//                        if(bean.getCode()==0){
//                            verifyCode.setValue(true);
//                        }else {
//                            ToastUtils.showLong(bean.getMessage());
//                        }
                    }

                });
        return user;
    }

    public void register(String phone, final SingleLiveEvent<Boolean> registerResult, String password, String confirm) {
        Map<String, String> defMap = MapUtils.getDefMap(false);
        defMap.put("mobile",phone);
        defMap.put("password",RSAUtils.encrypt(password));
        defMap.put("confirm",RSAUtils.encrypt(confirm));
        defMap.put("code","RES");
        HttpManager.post(ApiKey.MEMBER_REG)
                .cacheKey(this.getClass().getSimpleName())
                .upJson(GsonUtils.toJson(defMap))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                    }

                    @Override
                    public void onSuccess(String result) {
                        StringDataBean bean = GsonUtils.fromLocalJson(result, StringDataBean.class);
                        if(bean.getCode()==0){
                            registerResult.setValue(true);
                        }else {
                            ToastUtils.showLong(bean.getMessage());
                        }
                    }
                });
    }






}
