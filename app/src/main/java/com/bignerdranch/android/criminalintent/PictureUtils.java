package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * author : Wonder
 * e-mail : 2581343872@qq.com
 * date   : 2019/7/11 14:56
 * desc   : 该类是为了解决压缩照片进入Bitmap后原尺寸放大。确定文件大小后，考虑给个区域打下，再让Bitmap读取缩放文件
 * version:
 */
public class PictureUtils {
    // p275
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight) {
        // 读取桌面上照片的大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        // 按比例缩小
        int inSampleSize = 1; // 水平像素比
        if (srcHeight > destHeight || srcWidth > destWidth) {
            if (srcWidth > destHeight) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destHeight);
            }
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        // 读取和创建最终的bitmap
        return BitmapFactory.decodeFile(path, options);
    }

    // p276 在视图实际大小尺寸出来前的保守估算，因为尺寸只有在布局实例化后才会出现，fragment刚启动是看不见尺寸
    public static Bitmap getScaledBitmap(String path, Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay()
                .getSize(size);

        return getScaledBitmap(path, size.x, size.y);
    }

}
