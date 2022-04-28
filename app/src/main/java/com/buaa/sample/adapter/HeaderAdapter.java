package com.buaa.sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buaa.sample.R;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder> {

    // interface
    private SimpleListener simpleListener;

    public void setSimpleListener(SimpleListener simpleListener) {
        this.simpleListener = simpleListener;
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_header, parent, false);
        return new HeaderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    // HeaderViewHolder
    protected class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            // order by major
            Button btnOrderByMajor = itemView.findViewById(R.id.btn_order_by_major);
            btnOrderByMajor.setOnClickListener(view -> {
                if (simpleListener != null) {
                    simpleListener.onHeaderItemClick(btnOrderByMajor.getId());
                }
            });
            // order by age
            Button btnOrderByAge = itemView.findViewById(R.id.btn_order_by_age);
            btnOrderByAge.setOnClickListener(view -> {
                if (simpleListener != null) {
                    simpleListener.onHeaderItemClick(btnOrderByAge.getId());
                }
            });
        }
    }

}
