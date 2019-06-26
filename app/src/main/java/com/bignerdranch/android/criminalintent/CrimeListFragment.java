package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override // CrimeListFragment配置视图 P162
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getmCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
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
            Toast.makeText(getActivity(),
                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
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

        }
}
