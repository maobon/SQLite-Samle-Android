package com.buaa.sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buaa.sample.R;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder> {

    private static final String TAG = "HH";

    // interface
    private SimpleListener simpleListener;

    public void setSimpleListener(SimpleListener simpleListener) {
        this.simpleListener = simpleListener;
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_header, parent, false);
        return new HeaderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {
        holder.haha();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private boolean flag;
    private boolean flag2;

    // HeaderViewHolder
    protected class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivOrderByMajor;
        private final ImageView ivOrderByAge;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            // order by major
            ivOrderByMajor = itemView.findViewById(R.id.btn_order_by_major);
            ivOrderByMajor.setOnClickListener(view -> {
                if (simpleListener != null) {
                    flag = !flag;
                    simpleListener.onHeaderItemClick(ivOrderByMajor.getId());
                }
            });

            // order by age
            ivOrderByAge = itemView.findViewById(R.id.btn_order_by_age);
            ivOrderByAge.setOnClickListener(view -> {
                if (simpleListener != null) {
                    flag2 = !flag2;
                    simpleListener.onHeaderItemClick(ivOrderByAge.getId());
                }
            });
        }

        public void haha() {
            if (flag) {
                ivOrderByMajor.setImageResource(R.drawable.ic_action_desc);
            } else {
                ivOrderByMajor.setImageResource(R.drawable.ic_action_asc);
            }

            if (flag2) {
                ivOrderByAge.setImageResource(R.drawable.ic_action_desc);
            } else {
                ivOrderByAge.setImageResource(R.drawable.ic_action_asc);
            }
        }
    }

}
