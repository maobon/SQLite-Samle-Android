package com.buaa.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.buaa.sample.dao.StudentDao;
import com.buaa.sample.databinding.ActivityInfoBinding;
import com.buaa.sample.model.StudentInfo;

import java.util.Random;
import java.util.UUID;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding activityInfoBinding;

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

        StudentInfo info = (StudentInfo) getIntent().getSerializableExtra("info");
        if (info != null) {
            activityInfoBinding.etName.setText(info.getName());
            activityInfoBinding.etClassName.setText(info.getClassName());
            activityInfoBinding.etAge.setText(String.valueOf(info.getAge()));
        } else {
            generateRandomData();
        }

        // ...
        activityInfoBinding.btnAdd.setOnClickListener(view -> {
            StudentInfo student = new StudentInfo(
                    activityInfoBinding.etName.getText().toString(),
                    activityInfoBinding.etClassName.getText().toString(),
                    Integer.parseInt(activityInfoBinding.etAge.getText().toString())
            );
            dao.insert(student);
            finish();
        });

        activityInfoBinding.btnUpdate.setOnClickListener(view -> {
            StudentInfo student = new StudentInfo(
                    activityInfoBinding.etName.getText().toString(),
                    activityInfoBinding.etClassName.getText().toString(),
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

    private void generateRandomData() {
        activityInfoBinding.etName.setText(UUID.randomUUID().toString().split("-")[3]);
        activityInfoBinding.etClassName.setText("计算机科学与技术-2020");
        activityInfoBinding.etAge.setText(String.valueOf(new Random().nextInt(30)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInfoBinding = null;
    }
}