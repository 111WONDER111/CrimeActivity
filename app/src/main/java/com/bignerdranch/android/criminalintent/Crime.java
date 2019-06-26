package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * 项目的获取和设置方法Crime类
 */
public class Crime {

    private UUID mId; // 通用唯一标识码
    private String mTitle;

    public Date getDate() {
        return mDate;
    }

    public void setmDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() { // 布尔型，is
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    private Date mDate;// p132 第八章
    private boolean mSolved;

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

}
