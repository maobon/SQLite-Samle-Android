package com.buaa.sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buaa.sample.R;
import com.buaa.sample.adapter.callback.SimpleListener;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder> {

    private boolean iconFlag1, iconFlag2;
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
        holder.updateDisplayIcon();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

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
                    simpleListener.onHeaderItemClick(ivOrderByMajor.getId());
                    iconFlag1 = !iconFlag1;
                }
            });

            // order by age
            ivOrderByAge = itemView.findViewById(R.id.btn_order_by_age);
            ivOrderByAge.setOnClickListener(view -> {
                if (simpleListener != null) {
                    simpleListener.onHeaderItemClick(ivOrderByAge.getId());
                    iconFlag2 = !iconFlag2;
                }
            });
        }

        public void updateDisplayIcon() {
            if (iconFlag1) ivOrderByMajor.setImageResource(R.drawable.ic_action_desc);
            else ivOrderByMajor.setImageResource(R.drawable.ic_action_asc);

            if (iconFlag2) ivOrderByAge.setImageResource(R.drawable.ic_action_desc);
            else ivOrderByAge.setImageResource(R.drawable.ic_action_asc);
        }
    }

}
