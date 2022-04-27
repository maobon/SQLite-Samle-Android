package com.buaa.sample.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.buaa.sample.R;
import com.buaa.sample.model.Major;
import com.buaa.sample.model.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.InnerHolder> {

    private final List<StudentInfo> studentInfoList = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.dataBind(studentInfoList.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);

        } else {
            StudentInfo studentInfo = studentInfoList.get(position);
            Bundle bundle = (Bundle) payloads.get(0);

            for (String key : bundle.keySet()) {
                switch (key) {
                    case "age":
                        holder.tvAge.setText(String.valueOf(studentInfo.getAge()));
                        break;

                    case "index":
                        holder.tvClassName.setText(Major.values()[studentInfo.getIndex()].name);
                        break;

                    case "name":
                        holder.tvName.setText(studentInfo.getName());
                        break;
                }
            }
        }
    }

    public void refreshDataSet(List<StudentInfo> dataSet) {
        DiffUtil.DiffResult diffResult =
                DiffUtil.calculateDiff(new DiffUtilCallback(this.studentInfoList, dataSet));

        this.studentInfoList.clear();
        this.studentInfoList.addAll(dataSet);

        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return studentInfoList.size();
    }

    // ViewHolder
    protected class InnerHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final TextView tvName;
        private final TextView tvClassName;
        private final TextView tvAge;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvName = itemView.findViewById(R.id.tv_name);
            tvClassName = itemView.findViewById(R.id.tv_class_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }

        public void dataBind(StudentInfo studentInfo) {
            tvName.setText(studentInfo.getName());
            tvClassName.setText(Major.values()[studentInfo.getIndex()].name);
            tvAge.setText(String.valueOf(studentInfo.getAge()));
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition(), studentInfoList.get(getAdapterPosition()));
        }
    }

    // interface

    private ItemClickListener clickListener;

    public interface ItemClickListener {

        void onItemClick(int adapterPosition, StudentInfo studentInfo);
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
