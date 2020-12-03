package com.benwunet.base.config;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.benwunet.base.R;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.listener.OnImageCompleteCallback;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * @author：luck
 * @date：2020/4/30 10:54 AM
 * @describe：Picasso加载引擎
 */
public class PicassoEngine implements ImageEngine {

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Override
    public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        VideoRequestHandler videoRequestHandler = new VideoRequestHandler();
        if (PictureMimeType.isContent(url)) {
            Picasso.with(context)
                    .load(Uri.parse(url))
                    .into(imageView);
        } else {
            if (PictureMimeType.isUrlHasVideo(url)) {
                Picasso picasso = new Picasso.Builder(context.getApplicationContext())
                        .addRequestHandler(videoRequestHandler)
                        .build();
                picasso.load(videoRequestHandler.SCHEME_VIDEO + ":" + url)
                        .into(imageView);
            } else {
                Picasso.with(context)
                        .load(new File(url))
                        .into(imageView);
            }
        }
        Log.e("fggffff","vvv");
        Glide.with(context).load(url).into(imageView);

    }

    /**
     * 加载网络图片适配长图方案
     * # 注意：此方法只有加载网络图片才会回调
     *
     * @param context
     * @param url
     * @param imageView
     * @param longImageView
     * @param callback      网络图片加载回调监听 {link after version 2.5.1 Please use the #OnImageCompleteCallback#}
     */
    @Override
    public void loadImage(@NonNull Context context, @NonNull String url,
                          @NonNull ImageView imageView,
                          SubsamplingScaleImageView longImageView, OnImageCompleteCallback callback) {
    }

    @Override
    public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, SubsamplingScaleImageView longImageView) {

    }


    /**
     * 加载相册目录
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 承载图片ImageView
     */
    @Override
    public void loadFolderImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        VideoRequestHandler videoRequestHandler = new VideoRequestHandler();
        if (PictureMimeType.isContent(url)) {
            Picasso.with(context)
                    .load(Uri.parse(url))
                    .resize(180, 180)
                    .centerCrop()
                    .placeholder(R.drawable.picture_image_placeholder)
                    .into(imageView);
        } else {
            if (PictureMimeType.isUrlHasVideo(url)) {
                Picasso picasso = new Picasso.Builder(context.getApplicationContext())
                        .addRequestHandler(videoRequestHandler)
                        .build();
                picasso.load(videoRequestHandler.SCHEME_VIDEO + ":" + url)
                        .resize(180, 180)
                        .centerCrop()
                        .placeholder(R.drawable.picture_image_placeholder)
                        .into(imageView);
            } else {
                Picasso.with(context)
                        .load(new File(url))
                        .resize(180, 180)
                        .centerCrop()
                        .placeholder(R.drawable.picture_image_placeholder)
                        .into(imageView);
            }
        }
    }


    /**
     * 加载gif
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 承载图片ImageView
     */
    @Override
    public void loadAsGifImage(@NonNull Context context, @NonNull String url,
                               @NonNull ImageView imageView) {
        if (PictureMimeType.isContent(url)) {
            Picasso.with(context)
                    .load(Uri.parse(url))
                    .into(imageView);
        } else {
            Picasso.with(context)
                    .load(new File(url))
                    .into(imageView);
        }
    }

    /**
     * 加载图片列表图片
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 承载图片ImageView
     */
    @Override
    public void loadGridImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        VideoRequestHandler videoRequestHandler = new VideoRequestHandler();
        if (PictureMimeType.isContent(url)) {
            Picasso.with(context)
                    .load(Uri.parse(url))
                    .resize(200, 200)
                    .centerCrop()
                    .placeholder(R.drawable.picture_image_placeholder)
                    .into(imageView);
        } else {
            if (PictureMimeType.isUrlHasVideo(url)) {
                Picasso picasso = new Picasso.Builder(context.getApplicationContext())
                        .addRequestHandler(videoRequestHandler)
                        .build();
                picasso.load(videoRequestHandler.SCHEME_VIDEO + ":" + url)
                        .resize(200, 200)
                        .centerCrop()
                        .placeholder(R.drawable.picture_image_placeholder)
                        .into(imageView);
            } else {
                Picasso.with(context)
                        .load(new File(url))
                        .resize(200, 200)
                        .centerCrop()
                        .placeholder(R.drawable.picture_image_placeholder)
                        .into(imageView);
            }
        }
    }

    private PicassoEngine() {
    }

    private static PicassoEngine instance;

    public static PicassoEngine createPicassoEngine() {
        if (null == instance) {
            synchronized (PicassoEngine.class) {
                if (null == instance) {
                    instance = new PicassoEngine();
                }
            }
        }
        return instance;
    }
}
