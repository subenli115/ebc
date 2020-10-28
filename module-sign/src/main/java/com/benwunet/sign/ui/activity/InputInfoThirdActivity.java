package com.benwunet.sign.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityInputInfoThirdBinding;
import com.benwunet.sign.ui.bean.IndustryListBean;
import com.benwunet.sign.ui.bean.jobListBean;
import com.benwunet.sign.ui.viewmodel.InfoViewModel;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.sign.ui.activity
 * @ClassName: InputInfoThirdActivity
 * @Description: 完善资料页面3
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 18:21
 * @Version: 1.0
 */


public class InputInfoThirdActivity extends BaseActivity<ActivityInputInfoThirdBinding, InfoViewModel> {

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private OptionsPickerView<Object> mPickerView;
    private List<Object> mOptionsAgeItems;
    private List<jobListBean> jobListBeans1;
    private List<jobListBean> jobList;
    private List<IndustryListBean> industryList;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_input_info_third;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initViewObservable() {
        binding.setLifecycleOwner(this);
        viewModel.initListData();
        viewModel.showJob.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mPickerView = new OptionsPickerBuilder(InputInfoThirdActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        viewModel.selectJob.setValue(jobList.get(options1).getPositionName());
                    }
                }).build();
                mOptionsAgeItems = new ArrayList<>();
                for (int i = 0; i < jobList.size(); i++) {
                    mOptionsAgeItems.add(jobList.get(i).getPositionName());
                }
                mPickerView.setPicker(mOptionsAgeItems);
                mPickerView.show();

            }
        });

        viewModel.showIndustry.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mPickerView = new OptionsPickerBuilder(InputInfoThirdActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        viewModel.selectIndustry.setValue(industryList.get(options1).getIndustryName());

                    }
                }).build();
                mOptionsAgeItems = new ArrayList<>();
                for (int i = 0; i < jobList.size(); i++) {
                    mOptionsAgeItems.add(industryList.get(i).getIndustryName());
                }
                mPickerView.setPicker(mOptionsAgeItems);
                mPickerView.show();

            }
        });

        viewModel.showCity.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(InputInfoThirdActivity.this, CityListSelectActivity.class);
                startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);

            }
        });
        viewModel.industry.observe(this, new Observer<List<IndustryListBean>>() {
            @Override
            public void onChanged(List<IndustryListBean> industryListBeans) {
                industryList = industryListBeans;
            }
        });
        viewModel.job.observe(this, new Observer<List<jobListBean>>() {
            @Override
            public void onChanged(List<jobListBean> jobListBeans) {
                jobList = jobListBeans;
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

                CityInfoBean cityInfoBean = (CityInfoBean) bundle.getParcelable("cityinfo");

                if (null == cityInfoBean) {
                    return;
                }
                viewModel.city.setValue(cityInfoBean.getName());
            }
        }
    }


    @Override
    public void initData() {
        XXPermissions.with(this)
                .permission(Permission.ACCESS_FINE_LOCATION)
                .permission(Permission.ACCESS_COARSE_LOCATION)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                        initLocation();
                        startLocation();
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean never) {
                        finish();
                    }
                });
        binding.setLifecycleOwner(this);
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }


    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {

            if (null != location) {
                viewModel.city.setValue(location.getCity());
            }
        }
    };
}
