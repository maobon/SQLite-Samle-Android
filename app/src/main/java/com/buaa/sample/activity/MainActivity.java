package com.buaa.sample.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.buaa.sample.R;
import com.buaa.sample.adapter.HeaderAdapter;
import com.buaa.sample.adapter.StudentInfoAdapter;
import com.buaa.sample.adapter.callback.SimpleListener;
import com.buaa.sample.dao.StudentDao;
import com.buaa.sample.databinding.ActivityMainBinding;
import com.buaa.sample.model.StudentInfo;

import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private StudentInfoAdapter mStudentInfoAdapter;
    private ConcatAdapter mConcatAdapter;

    // 默认专业排序
    private Comparator<StudentInfo> mComparator = Comparator.comparingInt(StudentInfo::getIndex);
    private boolean flag = false;
    private boolean flag2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        initViews();
    }


    private void initViews() {
        HeaderAdapter headerAdapter = new HeaderAdapter();

        mStudentInfoAdapter = new StudentInfoAdapter();
        mStudentInfoAdapter.setSimpleListener(new SimpleListener() {
            @Override
            public void onContentItemClick(StudentInfo studentInfo) {
                InfoActivity.launch(MainActivity.this, studentInfo);
            }
        });

        headerAdapter.setSimpleListener(new SimpleListener() {
            @Override
            public void onHeaderItemClick(int buttonId) {

                if (R.id.btn_order_by_age == buttonId) {

                    flag = !flag;
                    mComparator = Comparator.comparingInt(StudentInfo::getAge);
                    if (flag) {
                        mComparator = mComparator.reversed();
                    }

                } else if (R.id.btn_order_by_major == buttonId) {

                    flag2 = !flag2;
                    mComparator = Comparator.comparingInt(StudentInfo::getIndex);

                    if (flag2) {
                        mComparator = mComparator.reversed();
                    }
                }

                updateRecyclerView();
            }
        });

        RecyclerView recyclerView = activityMainBinding.getRoot();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mConcatAdapter = new ConcatAdapter(headerAdapter, mStudentInfoAdapter);
        recyclerView.setAdapter(mConcatAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_new)
            InfoActivity.launch(this);
        return true;
    }

    private void updateRecyclerView() {
        List<StudentInfo> list = StudentDao.getInstance(this).query();
        list.sort(mComparator);

        RecyclerView recyclerView = activityMainBinding.getRoot();
        if (list.size() < 2) {
            recyclerView.setAdapter(mStudentInfoAdapter);
        } else {
            recyclerView.setAdapter(mConcatAdapter);
        }

        mStudentInfoAdapter.refreshDataSet(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StudentDao.getInstance(this).disconnect();
        activityMainBinding = null;
    }
}