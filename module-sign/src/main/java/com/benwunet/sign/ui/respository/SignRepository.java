package com.benwunet.sign.ui.respository;


import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.bean.StringDataBean;
import com.benwunet.base.global.ApiKey;
import com.benwunet.base.global.IConstants;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.base.utils.GsonUtils;
import com.benwunet.base.utils.MapUtils;
import com.benwunet.base.utils.RSAUtils;
import com.benwunet.msg.DemoHelper;
import com.benwunet.msg.common.interfaceOrImplement.DemoEmCallBack;
import com.benwunet.msg.common.interfaceOrImplement.ResultCallBack;
import com.benwunet.msg.common.net.ErrorCode;
import com.benwunet.msg.common.net.Resource;
import com.benwunet.msg.common.repositories.BaseEMRepository;
import com.benwunet.msg.common.repositories.NetworkOnlyResource;
import com.benwunet.sign.SignModuleInit;
import com.benwunet.sign.ui.activity.InputInfoFirstActivity;
import com.benwunet.sign.ui.bean.UserBean;
import com.benwunet.sign.ui.bean.UserLoginBean;
import com.benwunet.sign.ui.source.LocalDataSource;
import com.benwunet.sign.ui.viewmodel.LoginViewModel;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import java.util.Map;

import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.StringUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.sign.ui.respository
 * @ClassName: SignRepository
 * @Description: 登录模块仓库
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 11:20
 * @Version: 1.0
 */

public class SignRepository extends BaseEMRepository implements LocalDataSource {
    private volatile static SignRepository instance = null;
    private final LocalDataSource mLocalDataSource;
    private final LoginViewModel viewModel;

    public SignRepository(@NonNull LocalDataSource localDataSource, LoginViewModel viewModel) {
        this.mLocalDataSource = localDataSource;
        this.viewModel = viewModel;
    }

    public static SignRepository getInstance(LocalDataSource localDataSource, LoginViewModel viewModel) {
        if (instance == null) {
            synchronized (SignRepository.class) {
                if (instance == null) {
                    instance = new SignRepository(localDataSource, viewModel);

                }
            }
        }
        return instance;
    }


    public SingleLiveEvent<Boolean> getVerifyCode(String phone, final SingleLiveEvent<Boolean> verifyCode, String type) {
        HttpManager.get(ApiKey.NOTIFY_SMS)
                .params("mobile", phone)
                .params("type", type)
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        StringDataBean bean = GsonUtils.fromLocalJson(result, StringDataBean.class);
                        if (bean.getCode() == 0) {
                            verifyCode.setValue(true);
                        } else {
                            ToastUtils.showLong(bean.getMessage());
                        }

                    }
                });
        return verifyCode;
    }


    public void login(final String phone, final String pwd, final LoginViewModel viewModel) {
        HttpManager.post(ApiKey.OAUTH_PASS)
                .cacheKey(this.getClass().getSimpleName())
                .headers("client_id", "app")
                .cacheMode(CacheMode.NO_CACHE)
                .headers("client_secret", "123456")
                .upJson(GsonUtils.toJson(MapUtils.getLoginMap(phone, pwd)))
                .execute(new SimpleCallBack<UserLoginBean>() {
                    @Override
                    public void onError(ApiException e) {
                        viewModel.dismissDialog();
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(UserLoginBean result) {
                        if (result != null) {
                            savePassword(pwd);
                            saveUserName(phone);
                            saveToken(result.getAccess_token());
                            saveRefreshToken(result.getRefresh_token());
                            getUserInfo(viewModel);
                        }
                    }

                });

    }

    public void codeLogin(String phone, String verifyCode) {

        HttpManager.post(ApiKey.OAUTH_SMS)
                .headers("client_id", "app")
                .headers("client_secret", "123456")
                .cacheMode(CacheMode.NO_CACHE)
                .upJson(GsonUtils.toJson(MapUtils.getCodeMap(verifyCode, phone)))
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<UserLoginBean>() {
                    @Override
                    public void onError(ApiException e) {
                        viewModel.dismissDialog();
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(UserLoginBean result) {
                        if (result != null) {
                            saveUserName(phone);
                            saveToken(result.getAccess_token());
                            saveRefreshToken(result.getRefresh_token());
                            getUserInfo(viewModel);
                        }

                    }

                });
    }


    public void getUserInfo(final LoginViewModel viewModel) {
        HttpManager.get(ApiKey.MEMBER_CURRENT)
                .cacheMode(CacheMode.NO_CACHE)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<UserBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(UserBean result) {
                        viewModel.dismissDialog();
                        if(result.isIsFirstLogin()){
                            viewModel.startActivity(InputInfoFirstActivity.class);
                        }else {
                            if(!result.getMemberId().equals(EMClient.getInstance().getCurrentUser())){
                                loginToServer(result.getMemberId(),result.getPassword().substring(0,32),false);
                            }else {
                                successForCallBack();
                                ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
                            }
                        }
                    }

                });
    }




    public void register(final String phone, final String password, String confirm, String verifyCode, String type) {
        String url = "";
        if (StringUtils.equals(type, IConstants.REG)) {
            url = ApiKey.MEMBER_REG;
        } else {
            url = ApiKey.MEMBER_RES;
        }
        Map<String, String> defMap = MapUtils.getDefMap(false);
        defMap.put("mobile", phone);
        defMap.put("password", RSAUtils.encrypt(password));
        defMap.put("confirm", RSAUtils.encrypt(confirm));
        defMap.put("code", verifyCode);
        HttpManager.post(url)
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
                        if (bean.getCode() == 0) {
                            savePassword(password);
                            saveUserName(phone);
                            viewModel.closeOnClickCommand.execute();
                        }
                    }
                });
    }

    /**
     * 登录过后需要加载的数据
     * @return
     */
    public LiveData<Resource<Boolean>> loadAllInfoFromHX() {
        return new NetworkOnlyResource<Boolean>() {

            @Override
            protected void createCall(ResultCallBack<LiveData<Boolean>> callBack) {
                if(isAutoLogin()) {
                    runOnIOThread(() -> {
                        if(isLoggedIn()) {
                            callBack.onSuccess(createLiveData(true));
                        }else {
                            callBack.onError(ErrorCode.EM_NOT_LOGIN);
                        }

                    });
                }else {
                    callBack.onError(ErrorCode.EM_NOT_LOGIN);
                }

            }
        }.asLiveData();
    }

    /**
     * 登录到服务器，可选择密码登录或者token登录
     * @param userName
     * @param pwd
     * @param isTokenFlag
     * @return
     */
    public LiveData<Resource<EaseUser>> loginToServer(String userName, String pwd, boolean isTokenFlag) {
        return new NetworkOnlyResource<EaseUser>() {

            @Override
            protected void createCall(@NonNull ResultCallBack<LiveData<EaseUser>> callBack) {
                DemoHelper.getInstance().init(SignModuleInit.getmApplication());
                DemoHelper.getInstance().getModel().setCurrentUserName(userName);
                DemoHelper.getInstance().getModel().setCurrentUserPwd(pwd);
                if(isTokenFlag) {
                    EMClient.getInstance().loginWithToken(userName, pwd, new DemoEmCallBack() {
                        @Override
                        public void onSuccess() {
                            successForCallBack();
                        }

                        @Override
                        public void onError(int code, String error) {
                            callBack.onError(code, error);
                        }
                    });
                }else {
                    EMClient.getInstance().login(userName, pwd, new DemoEmCallBack() {
                        @Override
                        public void onSuccess() {
                            successForCallBack();
                            ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
                        }

                        @Override
                        public void onError(int code, String error) {
                            Looper.prepare();
                            ToastUtils.showLong(error);
                            Looper.loop();
                        }
                    });
                }

            }

        }.asLiveData();
    }


    /**
     * 从本地数据库加载所有的对话及群组
     */
    private void loadAllConversationsAndGroups() {
        // 初始化数据库
        initDb(SignModuleInit.getmApplication());
        // 从本地数据库加载所有的对话及群组
        getChatManager().loadAllConversations();
        getGroupManager().loadAllGroups();
    }


    private void successForCallBack() {
        // ** manually load all local groups and conversation
        loadAllConversationsAndGroups();
        // get current user id
        String currentUser = EMClient.getInstance().getCurrentUser();
        EaseUser user = new EaseUser(currentUser);
//        callBack.onSuccess(new MutableLiveData<>(user));
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
    public void saveToken(String token) {
        mLocalDataSource.saveToken(token);
    }

    @Override
    public void saveRefreshToken(String reToken) {
        mLocalDataSource.saveRefreshToken(reToken);
    }

    @Override
    public String getUserName() {
        return mLocalDataSource.getUserName();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }

    @Override
    public String getToken() {
        return mLocalDataSource.getToken();
    }
}
