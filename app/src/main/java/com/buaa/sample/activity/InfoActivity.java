package com.buaa.sample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.buaa.sample.R;
import com.buaa.sample.dao.StudentDao;
import com.buaa.sample.databinding.ActivityInfoBinding;
import com.buaa.sample.model.StudentInfo;
import com.buaa.sample.utils.Major;

import java.util.Random;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding activityInfoBinding;
    private int majorIndex;

    /**
     * 启动
     *
     * @param activity Activity
     */
    public static void launch(Activity activity) {
        launch(activity, null);
    }

    /**
     * 启动
     *
     * @param activity Activity
     * @param info     StudentInfo
     */
    public static void launch(Activity activity, StudentInfo info) {
        Intent intent = new Intent(activity, InfoActivity.class);
        if (info != null)
            intent.putExtra("info", info);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInfoBinding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(activityInfoBinding.getRoot());

        initSpinner();
        initViews((StudentInfo) getIntent().getSerializableExtra("info"));
    }

    private void initViews(StudentInfo info) {
        // display current select student info
        if (info != null) {
            activityInfoBinding.btnAdd.setVisibility(View.GONE);
            activityInfoBinding.etName.setText(info.getName());
            activityInfoBinding.spinnerMajor.setSelection(info.getIndex());
            activityInfoBinding.etAge.setText(String.valueOf(info.getAge()));
        } else {
            generateRandomData();
            activityInfoBinding.btnDelete.setVisibility(View.GONE);
            activityInfoBinding.btnUpdate.setVisibility(View.GONE);
        }

        // add new student info
        activityInfoBinding.btnAdd.setOnClickListener(view -> {
            StudentInfo student = new StudentInfo(
                    activityInfoBinding.etName.getText().toString(),
                    majorIndex,
                    Integer.parseInt(activityInfoBinding.etAge.getText().toString())
            );
            StudentDao.getInstance(this).insert(student);
            finish();
        });

        // update student info
        activityInfoBinding.btnUpdate.setOnClickListener(view -> {
            StudentInfo student = new StudentInfo(
                    activityInfoBinding.etName.getText().toString(),
                    majorIndex,
                    Integer.parseInt(activityInfoBinding.etAge.getText().toString())
            );
            assert info != null;
            student.setId(info.getId());
            StudentDao.getInstance(this).update(student);
            finish();
        });

        // delete student info
        activityInfoBinding.btnDelete.setOnClickListener(view -> {
            assert info != null;
            StudentDao.getInstance(this).delete(info.getId());
            finish();
        });
    }

    private void initSpinner() {
        activityInfoBinding.spinnerMajor
                .setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Major.getMajorArray()));
        activityInfoBinding.spinnerMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                majorIndex = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void generateRandomData() {
        String[] stringArray = getResources().getStringArray(R.array.names_random_list);
        activityInfoBinding.etName.setText(stringArray[new Random().nextInt(stringArray.length - 1)]);
        activityInfoBinding.spinnerMajor.setSelection(new Random().nextInt(2));
        activityInfoBinding.etAge.setText(String.valueOf(18 + new Random().nextInt(4)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StudentDao.getInstance(this).disconnect();
        activityInfoBinding = null;
    }
}