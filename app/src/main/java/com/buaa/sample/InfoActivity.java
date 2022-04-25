package com.buaa.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.buaa.sample.dao.StudentDao;
import com.buaa.sample.databinding.ActivityInfoBinding;
import com.buaa.sample.model.Major;
import com.buaa.sample.model.StudentInfo;

import java.util.Random;
import java.util.UUID;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding activityInfoBinding;
    private String majorIndex;

    public static void launch(Activity activity) {
        launch(activity, null);
    }

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

        final StudentDao dao = StudentDao.getInstance(this);

        initSpinner();

        StudentInfo info = (StudentInfo) getIntent().getSerializableExtra("info");
        if (info != null) {
            activityInfoBinding.etName.setText(info.getName());
            activityInfoBinding.spinnerMajor.setSelection(Integer.parseInt(info.getClassName()));
            activityInfoBinding.etAge.setText(String.valueOf(info.getAge()));

        } else {
            generateRandomData();
        }

        // ...
        activityInfoBinding.btnAdd.setOnClickListener(view -> {
            StudentInfo student = new StudentInfo(
                    activityInfoBinding.etName.getText().toString(),
                    majorIndex,
                    Integer.parseInt(activityInfoBinding.etAge.getText().toString())
            );
            dao.insert(student);
            finish();
        });

        activityInfoBinding.btnUpdate.setOnClickListener(view -> {
            StudentInfo student = new StudentInfo(
                    activityInfoBinding.etName.getText().toString(),
                    majorIndex,
                    Integer.parseInt(activityInfoBinding.etAge.getText().toString())
            );
            assert info != null;
            student.setId(info.getId());
            dao.update(student);
            finish();
        });

        activityInfoBinding.btnDelete.setOnClickListener(view -> {
            assert info != null;
            dao.delete(info.getId());
            finish();
        });
    }

    private void initSpinner() {
        String[] strings = new String[Major.values().length];
        int index = 0;
        for (Major major : Major.values()) {
            strings[index] = major.name;
            index++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, strings);
        activityInfoBinding.spinnerMajor.setAdapter(adapter);

        activityInfoBinding.spinnerMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                majorIndex = String.valueOf(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void generateRandomData() {
        activityInfoBinding.etName.setText(UUID.randomUUID().toString().split("-")[3]);
        activityInfoBinding.spinnerMajor.setSelection(0);
        activityInfoBinding.etAge.setText(String.valueOf(new Random().nextInt(30)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInfoBinding = null;
    }

}