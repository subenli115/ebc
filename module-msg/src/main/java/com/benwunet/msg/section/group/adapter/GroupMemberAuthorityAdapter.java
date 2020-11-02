package com.benwunet.msg.section.group.adapter;

import com.benwunet.msg.section.contact.adapter.ContactListAdapter;
import com.benwunet.msg.R;

public class GroupMemberAuthorityAdapter extends ContactListAdapter {

    @Override
    public int getEmptyLayoutId() {
        return R.layout.ease_layout_default_no_data;
    }
}
