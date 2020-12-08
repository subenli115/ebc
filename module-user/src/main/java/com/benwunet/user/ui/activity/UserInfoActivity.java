package com.benwunet.user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserInfoBinding;
import com.benwunet.user.ui.bean.MeInfoBean;
import com.benwunet.user.ui.viewmodel.SettingViewModel;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserInfoActivity
 * @Description: 个人资料界面
 * @Author: feng
 * @CreateDate: 2020/11/11 0011 09:34
 * @Version: 1.0
 */


public class UserInfoActivity extends BaseActivity<ActivityUserInfoBinding, SettingViewModel> {
    private List<String> mOptionsAgeItems;
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_info;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initData();
        binding.igvBirthday.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                TimePickerView pvTime = new TimePickerBuilder(UserInfoActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String time = simpleDateFormat.format(date);
                        binding.igvBirthday.setRigthText(time);
                        viewModel.time.setValue(time);
                    }
                }).build();
                pvTime.show();
            }
        });
        binding.igvSex.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                OptionsPickerView<String> mPickerView = new OptionsPickerBuilder(UserInfoActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        binding.igvSex.setRigthText(mOptionsAgeItems.get(options1));
                        viewModel.sex.setValue(mOptionsAgeItems.get(options1));
                    }
                }).build();
                mOptionsAgeItems = new ArrayList<>();
                mOptionsAgeItems.add("男");
                mOptionsAgeItems.add("女");
                mPickerView.setPicker(mOptionsAgeItems);
                mPickerView.show();
            }
        });

        binding.igvCity.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, CityListSelectActivity.class);
                startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
            }
        });
    }

    @Override
    public void initViewObservable() {
        viewModel.info.observe(this, new Observer<MeInfoBean>() {
            @Override
            public void onChanged(MeInfoBean meInfoBean) {
                binding.igvBirthday.setRigthText(meInfoBean.getBirthday());
                binding.igvSex.setRigthText(meInfoBean.getGender() == 1 ? "男" : "女");
                binding.igvCity.setRigthText(meInfoBean.getAreaCityName()==null?"":meInfoBean.getAreaCityName());
                binding.editSign.setText(meInfoBean.getMemberSign());
            }
        });
        viewModel.complete.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();
                CityInfoBean cityInfoBean = bundle.getParcelable("cityinfo");
                if (null == cityInfoBean) {
                    return;
                }
                binding.igvCity.setRigthText(cityInfoBean.getName());
                viewModel.city.setValue(cityInfoBean.getName());
                viewModel.setCityId(cityInfoBean.getId());
            }
        }
    }

}