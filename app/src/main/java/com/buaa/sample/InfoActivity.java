package com.buaa.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.buaa.sample.databinding.ActivityInfoBinding;
import com.buaa.sample.model.StudentInfo;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding activityInfoBinding;

    public static void launch(Activity activity, StudentInfo info) {
        Intent intent = new Intent(activity, InfoActivity.class);
        intent.putExtra("info", info);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInfoBinding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(activityInfoBinding.getRoot());

        StudentInfo info = (StudentInfo) getIntent().getSerializableExtra("info");
        Log.wtf("TTT", "info::" + info);

        activityInfoBinding.etName.setText(info.getName());
        activityInfoBinding.etClassName.setText(info.getClassName());
        activityInfoBinding.etAge.setText(String.valueOf(info.getAge()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInfoBinding = null;
    }
}