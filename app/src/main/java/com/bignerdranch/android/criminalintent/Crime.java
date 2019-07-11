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

    public void setDate(Date date) {
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

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    // P271 照片文件名获取方法
    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    private String mSuspect; // 存放嫌疑人姓名，p252

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

        this(UUID.randomUUID());

        // mId = UUID.randomUUID(); // p245删除
        // mDate = new Date(); // p245删除
    }

    // p244
    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

}
