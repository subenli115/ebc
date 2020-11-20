package com.benwunet.user.ui.wdiget;

import android.view.View;

import com.benwunet.user.R;
import com.benwunet.user.ui.activity.UserHomeActivity;

/**
 * Created by ${feng} on 2017/4/9.
 *
 *
 */

public class MenuItemController implements View.OnClickListener {
    private UserHomeActivity activity;

    public MenuItemController(UserHomeActivity activity) {
        this.activity = activity;
    }

    //会话界面的加号
    @Override
    public void onClick(View v) {
        int id = v.getId();
        activity.dismissPopWindow();
        if (id == R.id.rl_first) {

        } else if (id == R.id.rl_second) {

        } else if (id == R.id.rl_third) {

        } else if (id == R.id.rl_forth) {

        } else if (id == R.id.rl_fifth) {

        }

    }
}
