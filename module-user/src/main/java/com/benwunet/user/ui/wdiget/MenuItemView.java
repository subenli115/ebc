package com.benwunet.user.ui.wdiget;

import android.view.View;
import android.widget.RelativeLayout;

import com.benwunet.user.R;


public class MenuItemView {

    private View mView;
    private RelativeLayout rlFirst;
    private RelativeLayout rlSecond;
    private RelativeLayout rlThird;
    private RelativeLayout rlForth;
    private RelativeLayout rlFifth;

    public MenuItemView(View view) {
        this.mView = view;
    }

    public void initModule() {
        rlFirst = mView.findViewById(R.id.rl_first);
        rlSecond = mView.findViewById(R.id.rl_second);
        rlThird = mView.findViewById(R.id.rl_third);
        rlForth = mView.findViewById(R.id.rl_forth);
        rlFifth = mView.findViewById(R.id.rl_fifth);
    }

    public void setListeners(View.OnClickListener listener) {
        rlFirst.setOnClickListener(listener);
        rlSecond.setOnClickListener(listener);
        rlThird.setOnClickListener(listener);
        rlForth.setOnClickListener(listener);
        rlFifth.setOnClickListener(listener);
    }

}
