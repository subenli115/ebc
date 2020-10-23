package com.benwunet.sign.ui.respository;



import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.global.ApiKey;
import com.benwunet.base.global.SPKeyGlobal;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.base.utils.GsonUtils;
import com.benwunet.base.utils.MapUtils;
import com.benwunet.base.utils.RSAUtils;
import com.benwunet.sign.ui.bean.UserBean;
import com.benwunet.sign.ui.bean.StringDataBean;
import com.benwunet.sign.ui.source.LocalDataSource;
import com.benwunet.sign.ui.viewmodel.LoginViewModel;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import java.util.Map;

import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 * Created by feng on 2020/10/21.
 */
public class SignRepository extends BaseModel  implements  LocalDataSource {
    private volatile static SignRepository instance = null;
    private final LocalDataSource mLocalDataSource;
    private final LoginViewModel viewModel;

    private SignRepository(@NonNull LocalDataSource localDataSource, LoginViewModel viewModel)  {
        this.mLocalDataSource = localDataSource;
        this.viewModel = viewModel;
    }

    public static SignRepository getInstance(LocalDataSource localDataSource, LoginViewModel viewModel) {
        if (instance == null) {
            synchronized (SignRepository.class) {
                if (instance == null) {
                    instance = new SignRepository(localDataSource,viewModel);

                }
            }
        }
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

    public void login(String phone, String pwd) {
        HttpManager.post(ApiKey.OAUTH_PASS)
                .cacheKey(this.getClass().getSimpleName())
                .headers("client_id","app")
                .headers("client_secret","123456")
                .upJson(GsonUtils.toJson(MapUtils.getLoginMap(phone,pwd)))
                .execute(new SimpleCallBack<UserBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(UserBean result) {
                        ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
                    }

                });
    }

    public SingleLiveEvent<UserBean> codeLogin(String phone, final SingleLiveEvent<UserBean> user, String verifyCode) {
        HttpManager.post(ApiKey.OAUTH_SMS)
                .headers("client_id","app")
                .headers("client_secret","123456")
                .accessToken(true)
                .upJson(GsonUtils.toJson(MapUtils.getCodeMap(verifyCode,phone)))
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<UserBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(UserBean result) {
                    }

                });
        return user;
    }

    public void register(final String phone, final String password, String confirm, String verifyCode) {
        Map<String, String> defMap = MapUtils.getDefMap(false);
        defMap.put("mobile",phone);
        defMap.put("password",RSAUtils.encrypt(password));
        defMap.put("confirm",RSAUtils.encrypt(confirm));
        defMap.put("code",verifyCode);
        HttpManager.post(ApiKey.MEMBER_REG)
                .cacheKey(this.getClass().getSimpleName())
                .upJson(GsonUtils.toJson(defMap))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        StringDataBean bean = GsonUtils.fromLocalJson(result, StringDataBean.class);
                        ToastUtils.showLong(bean.getMessage());
                        if(bean.getCode()==0){
                            savePassword(password);
                            saveUserName(phone);
                            viewModel.finish();
                        }
                    }
                });
    }


    @Override
    public void saveUserName(String userName) {
        mLocalDataSource.saveUserName(userName);
    }

    @Override
    public void savePassword(String password) {
        mLocalDataSource.savePassword(password);
    }

    @Override
    public String getUserName() {
        return mLocalDataSource.getUserName();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }
}
