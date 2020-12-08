package com.benwunet.ebc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.benwunet.base.config.ModuleLifecycleConfig;
import com.benwunet.base.wdiget.GlideRoundTransform;
import com.benwunet.msg.common.utils.PreferenceManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.hyphenate.util.EMLog;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.bingoogolapple.baseadapter.BGABaseAdapterUtil;
import cn.bingoogolapple.photopicker.imageloader.BGAImage;
import cn.bingoogolapple.photopicker.imageloader.BGAImageLoader;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * Created by feng on 2020/10/15.
 */

public class MyAPP extends BaseApplication implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();
//        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                throwable.printStackTrace();//这里处理所有的Rxjava异常
//            }
//        });
        //初始化组件(靠前)
        AutoSizeConfig.getInstance()
                .setCustomFragment(true)
                .setUseDeviceSize(false);


        PreferenceManager.init(this);
        closeAndroidPDialog();
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //....
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
        BGAImage.setImageLoader(new BGAGlideImageLoader490());
    }


    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        EMLog.e("demoApp", e.getMessage());
    }


    /**
     * 为了兼容5.0以下使用vector图标
     */
    static {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }


    /**
     * 解决androidP 第一次打开程序出现莫名弹窗
     * 弹窗内容“detected problems with api ”
     */
    private void closeAndroidPDialog(){
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
            try {
                Class aClass = Class.forName("android.content.pm.PackageParser$Package");
                Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
                declaredConstructor.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Class cls = Class.forName("android.app.ActivityThread");
                Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
                declaredMethod.setAccessible(true);
                Object activityThread = declaredMethod.invoke(null);
                Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
                mHiddenApiWarningShown.setAccessible(true);
                mHiddenApiWarningShown.setBoolean(activityThread, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class BGAGlideImageLoader490 extends BGAImageLoader {

        @Override
        public void display(ImageView imageView, String path, int loadingResId, int failResId, int width, int height, DisplayDelegate delegate) {
            final String finalPath = getPath(path);
            Activity activity = getActivity(imageView);
            MultiTransformation<Bitmap>     multiLeft = new MultiTransformation<>(new CenterCrop(),new GlideRoundTransform(getBaseContext(),5));
            Glide.with(activity).load(finalPath).apply(RequestOptions.placeholderOf(loadingResId).override(width,height).error(failResId).dontAnimate()
                    .bitmapTransform(multiLeft)
            ).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    if (delegate != null) {
                        delegate.onSuccess(imageView, finalPath);
                    }
                    return false;
                }
            }).into(imageView);
        }

        @Override
        public void download(String path, DownloadDelegate delegate) {
            final String finalPath = getPath(path);
            Glide.with(BGABaseAdapterUtil.getApp()).asBitmap().load(finalPath).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    if (delegate != null) {
                        delegate.onSuccess(finalPath, resource);
                    }
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    if (delegate != null) {
                        delegate.onFailed(finalPath);
                    }
                }
            });
        }

        @Override
        public void pause(Activity activity) {
            Glide.with(activity).pauseRequests();
        }

        @Override
        public void resume(Activity activity) {
            Glide.with(activity).resumeRequestsRecursive();
        }
    }
}
