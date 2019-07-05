package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle"; // p230

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private boolean mSubtitleVisibel; //用于记录子标题状态

    // 让FragmentManager知道CrimeListFragment需接收选项菜单方法回调 p222
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override // CrimeListFragment配置视图 P162
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisibel = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE); // p230
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {  // 刷新列表项P181
        super.onResume();
        updateUI();
    }

    // p230 保存子标题状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisibel);
    }


    // 实例化fragment_crime_list.xml中的定义菜单p221
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisibel) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }


    // 实现onOptionsItemSelected方法响应菜单项的选择事件 p224
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity
                        .newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle: // 响应show subtitle菜单项单击事件
                mSubtitleVisibel = !mSubtitleVisibel;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 实现子标题显示crime记录条数,设置工具栏子标题
    private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        // String crimeCount = Integer.toString(crimeCountint); // 要匹配subtitle_format的类型
        String subtitle = getString(R.string.subtitle_format, crimeCount);

        if (!mSubtitleVisibel) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);

    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {

            mAdapter.setCrimes(crimes); // 十四章用于刷新回退后crime显示，p246

            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();

    }

    // 定义ViewHolder内部类，为了看见列表crime
    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

            // public TextView mTitleTextView;
            private TextView mTitleTextView;
            private TextView mDateTextView;
            private CheckBox mSolveCheckBox;
            private Crime mCrime;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // mTitleTextView = (TextView) itemView;
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolveCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

            // 在CrimeHolder中绑定视图
            public void bindCrime (Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolveCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(getActivity(),
//                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
//                    .show();

            // 将toast替换为启动CrimeActivity实例的代码
            // Intent intent = new Intent(getActivity(), CrimeActivity.class);
            // Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());// p175
                Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());// P189因为设置左滑右滑，改变启动的活动
                startActivity(intent);
        }
    }

        // 创建adapter内部类
        private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

            private List<Crime> mCrimes;

            public CrimeAdapter(List<Crime> crimes) {
                mCrimes = crimes;
            }

            @Override
            public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater
                        // 实例化标准库中simple_list_item_1的布局
                        //.inflate(android.R.layout.simple_list_item_1, parent, false);

                        // 自建列表布局后引用的p168
                        .inflate(R.layout.list_item_crime, parent, false);
                return new CrimeHolder(view);
            }

            @Override
            public void onBindViewHolder(CrimeHolder holder, int position) {
                Crime crime = mCrimes.get(position);
                // holder.mTitleTextView.setText(crime.getTitle());
                holder.bindCrime(crime);
            }

            @Override
            public int getItemCount() {
                return mCrimes.size();
            }

            // 十四章用于刷新回退后crime显示，p246
            public void setCrimes(List<Crime> crimes) {
                mCrimes = crimes;
            }

        }
}
