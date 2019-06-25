package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * 项目的获取和设置方法Crime类
 */
public class Crime {

    private UUID mId; // 通用唯一标识码
    private String mTitle;

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() { // 布尔型，is
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    private Date mDate;// p132 第八章
    private boolean mSolved;

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

}
