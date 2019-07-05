package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.CrimeDbSchema.CrimeBaseHelper;
import database.CrimeDbSchema.CrimeCursorWrapper;
import database.CrimeDbSchema.CrimeDbSchema;
import database.CrimeDbSchema.CrimeDbSchema.CrimeTable;

/**
 * 带有私有构造方法和get()的单例，存储crime数组对象p150
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    // private List<Crime> mCrimes; // 在第十四章删除单例p238

    private Context mContext; // p235
    private SQLiteDatabase mDatabase; // 不用单例的列表了，用其存储数据

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){ // p152创建100个模型数据crime,然后第13章删除

        mContext = context.getApplicationContext(); // p235 打开SQLiteDatabase
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();

        // mCrimes = new ArrayList<>(); // 第十四章删除

//        for (int i = 0; i<100;i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.add(crime);
//        }
    }

    // 手动添加crime p223
    public void addCrime(Crime c) {
        // mCrimes.add(c); // 第十四章删除

        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values); // 插入记录p240
    }

    public List<Crime> getCrimes() {
        // return mCrimes; // 第十四章删除

        // return new ArrayList<>();

        // 遍历查询出所有的crime，返回Crime数组对象p245
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst(); // 移动到第一个元素
            while (!cursor.isAfterLast()) {  // 判断是否还有数据
                crimes.add(cursor.getCrime());
                cursor.moveToNext(); // 读取下一行数据
            }
        } finally {
            cursor.close(); // 很重要，不关闭会奔溃
        }

        return crimes;
    }

    public Crime getCrime(UUID id) {

//        for (Crime crime : mCrimes) {
//            if (crime.getId().equals(id)) {
//                return crime;
//            }
//        } // 第十四章删除

        // return null;

        // p246十四章新增
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    // 更新记录p240
    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    } // ?是sql传参，此处是防止SQL注入

    // ContentValues用于数据库写入和更新操作的辅助类p239
    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }

    // 新增一个便利方法query查询CrimeTable
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

}
