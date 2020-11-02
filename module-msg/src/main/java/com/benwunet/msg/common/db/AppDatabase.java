package com.benwunet.msg.common.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.benwunet.msg.common.db.converter.DateConverter;
import com.benwunet.msg.common.db.dao.AppKeyDao;
import com.benwunet.msg.common.db.dao.EmUserDao;
import com.benwunet.msg.common.db.dao.InviteMessageDao;
import com.benwunet.msg.common.db.dao.MsgTypeManageDao;
import com.benwunet.msg.common.db.entity.AppKeyEntity;
import com.benwunet.msg.common.db.entity.EmUserEntity;
import com.benwunet.msg.common.db.entity.InviteMessage;
import com.benwunet.msg.common.db.entity.MsgTypeManageEntity;

@Database(entities = {EmUserEntity.class,
        InviteMessage.class,
        MsgTypeManageEntity.class,
        AppKeyEntity.class},
        version = 13)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmUserDao userDao();

    public abstract InviteMessageDao inviteMessageDao();

    public abstract MsgTypeManageDao msgTypeManageDao();

    public abstract AppKeyDao appKeyDao();
}
