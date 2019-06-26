package com.bignerdranch.android.criminalintent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

//public class CrimeActivity extends FragmentActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//
//        FragmentManager fm = getSupportFragmentManager(); // p126
//
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if (fragment == null) {
//            fragment = new CrimeFragment();
//            fm.beginTransaction()
//                    .add(R.id.fragment_container, fragment)
//                    .commit();
//        }
//    }
//}

// 实现p155的抽象类activity后，改变CrimeActivity
public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
