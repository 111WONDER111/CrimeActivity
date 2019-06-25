package com.bignerdranch.android.criminalintent;

import java.util.UUID;

public class Crime {

    private UUID mId; // 通用唯一标识码
    private String mTitle;

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
    }

}
