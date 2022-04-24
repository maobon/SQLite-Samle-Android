package com.buaa.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

    //private List<StudentInfo> mDataSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        final StudentDao dao = StudentDao.getInstance(this);
        mStudentInfoAdapter = new StudentInfoAdapter();

        RecyclerView recyclerView = activityMainBinding.getRoot();
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mStudentInfoAdapter);

        List<StudentInfo> list = dao.query();
        if (list != null && list.size() > 0) {
            mStudentInfoAdapter.refreshDataSet(list);
        }

        mStudentInfoAdapter.setClickListener((adapterPosition, studentInfo) -> {
            // mDataSet.remove(adapterPosition);
            // mStudentInfoAdapter.refreshDataSet(mDataSet);
            // dao.delete();
            Log.wtf("XXX", "info:" + studentInfo.getId());

            dao.delete(studentInfo.getId());
            updateRecyclerView();

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_new) {
            final StudentDao dao = StudentDao.getInstance(this);
            Toast.makeText(this, "afdadad", Toast.LENGTH_SHORT).show();

            long insert = dao.insert(new StudentInfo("hhh", "kkkk", 13));
            Log.wtf("HAHA", "id:" + insert);

            updateRecyclerView();

        }
        return true;
    }

    private void updateRecyclerView() {
        List<StudentInfo> list = StudentDao.getInstance(this).query();
        mStudentInfoAdapter.refreshDataSet(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityMainBinding = null;
    }
}