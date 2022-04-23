package com.buaa.sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buaa.sample.databinding.LayoutItemBinding;
import com.buaa.sample.model.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.InnerHolder> {

    private List<StudentInfo> studentInfoList = new ArrayList<>();

    private ItemClickListener clickListener;

    public interface ItemClickListener {

        void onItemClick(int adapterPosition);
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        StudentInfo studentInfo = studentInfoList.get(position);
        holder.dataBind(studentInfo);
    }

    @SuppressWarnings("all")
    public void refreshDataSet(List<StudentInfo> dataSet) {
        this.studentInfoList = new ArrayList<>(dataSet);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return studentInfoList.size();
    }

    // ViewHolder
    protected class InnerHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final LayoutItemBinding itemBinding;

        public InnerHolder(@NonNull LayoutItemBinding itemBinding) {
            super(itemBinding.getRoot());

            this.itemBinding = itemBinding;
            itemBinding.getRoot().setOnClickListener(this);
        }

        public void dataBind(StudentInfo studentInfo) {
            itemBinding.tvName.setText(studentInfo.getName());
            itemBinding.tvClassName.setText(studentInfo.getClassName());
            itemBinding.tvAge.setText(String.valueOf(studentInfo.getAge()));
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition());
        }
    }

}
