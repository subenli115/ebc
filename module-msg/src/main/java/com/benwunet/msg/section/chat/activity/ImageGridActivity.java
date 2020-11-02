package com.benwunet.msg.section.chat.activity;

import androidx.fragment.app.FragmentTransaction;

import com.benwunet.msg.section.base.BaseInitActivity;
import com.benwunet.msg.section.chat.fragment.ImageGridFragment;
import com.benwunet.msg.R;

public class ImageGridActivity extends BaseInitActivity {

	private static final String TAG = "ImageGridActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_image_grid;
    }

    @Override
    protected void initData() {
        super.initData();
        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fl_fragment, new ImageGridFragment(), TAG);
            ft.commit();
        }
    }
}
