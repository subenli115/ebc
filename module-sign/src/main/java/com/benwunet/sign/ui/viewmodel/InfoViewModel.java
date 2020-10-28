package com.benwunet.sign.ui.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.global.IConstants;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.sign.ui.activity.InputInfoFirstActivity;
import com.benwunet.sign.ui.activity.InputInfoFourthActivity;
import com.benwunet.sign.ui.activity.InputInfoSecondActivity;
import com.benwunet.sign.ui.activity.InputInfoThirdActivity;
import com.benwunet.sign.ui.bean.IndustryListBean;
import com.benwunet.sign.ui.bean.TopicBean;
import com.benwunet.sign.ui.bean.jobListBean;
import com.benwunet.sign.ui.respository.InfoRepository;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * @Package: com.benwunet.sign.ui
 * @ClassName: InfoViewModel
 * @Description: 完善资料业务
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 18:22
 * @Version: 1.0
 */


public class InfoViewModel extends BaseViewModel {

    private InfoRepository infoRepository = InfoRepository.getInstance(this);
    public SingleLiveEvent<String> imgUrl = new SingleLiveEvent<>();
    public SingleLiveEvent<String> name = new SingleLiveEvent<>();
    public SingleLiveEvent<String> company = new SingleLiveEvent<>();
    public SingleLiveEvent<String> selectIndustry = new SingleLiveEvent<>();
    public SingleLiveEvent<String> selectJob = new SingleLiveEvent<>();
    public SingleLiveEvent<List<jobListBean>> job = new SingleLiveEvent<>();
    public SingleLiveEvent<String> city = new SingleLiveEvent<>();
    public SingleLiveEvent<List<IndustryListBean>> industry = new SingleLiveEvent<>();
    public SingleLiveEvent<TopicBean> hotTag = new SingleLiveEvent<>();
    public SingleLiveEvent<TopicBean> newTag = new SingleLiveEvent<>();
    public SingleLiveEvent<String> time = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> showCity = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> showJob = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> showIndustry = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> isShowDialog = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> isSelectPhoto = new SingleLiveEvent<>();
    public SingleLiveEvent<Integer> selectType = new SingleLiveEvent<>();


    //关闭页面按钮
    public BindingCommand closeOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });


    //图片选择按钮
    public BindingCommand photoOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isSelectPhoto.setValue(true);
        }
    });


    //下一步按钮
    public BindingCommand nextOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Class<? extends LifecycleProvider> aClass = getLifecycleProvider().getClass();
            if (aClass.equals(InputInfoFirstActivity.class)) {
                startActivity(InputInfoSecondActivity.class);
            } else if (aClass.equals(InputInfoSecondActivity.class)) {
                startActivity(InputInfoThirdActivity.class);
            }else if(aClass.equals(InputInfoThirdActivity.class)){
                startActivity(InputInfoFourthActivity.class);
            }else {
                ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
            }
        }
    });

    //时间选择按钮
    public BindingCommand timeOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isShowDialog.call();
        }
    });

    //城市选择按钮
    public BindingCommand cityOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showCity.call();
        }
    });


    //职业
    public BindingCommand jobOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showJob.call();
        }
    });
    //行业
    public BindingCommand industryOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showIndustry.call();
        }
    });


    //跳过按钮
    public BindingCommand skipOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
        }
    });

    //性别男按钮
    public BindingCommand manOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            selectType.setValue(View.VISIBLE);
        }
    });
    //性别女按钮
    public BindingCommand womanOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            selectType.setValue(View.GONE);
        }
    });
    private SingleLiveEvent<List<IndustryListBean>> data;


    public InfoViewModel(@NonNull Application application) {
        super(application);
        time.setValue("2020年10月26日");
        selectType.setValue(View.VISIBLE);
    }

    public void  initListData() {
        infoRepository.getPosition(job);
        infoRepository.getIndustry(industry);
    }

    public void  initTagData() {
        infoRepository.getTopic(hotTag, IConstants.HOTTOPIC);
        infoRepository.getTopic(newTag,IConstants.NEWTOPIC);
    }


}
