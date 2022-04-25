package com.buaa.sample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.buaa.sample.adapter.StudentInfoAdapter;
import com.buaa.sample.dao.StudentDao;
import com.buaa.sample.databinding.ActivityMainBinding;
import com.buaa.sample.model.StudentInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private StudentInfoAdapter mStudentInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        mStudentInfoAdapter = new StudentInfoAdapter();

        RecyclerView recyclerView = activityMainBinding.getRoot();
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mStudentInfoAdapter);

        mStudentInfoAdapter.setClickListener((adapterPosition, studentInfo) -> {
            InfoActivity.launch(MainActivity.this, studentInfo);
        });
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
        mStudentInfoAdapter.refreshDataSet(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StudentDao.getInstance(this).disconnect();
        activityMainBinding = null;
    }
}