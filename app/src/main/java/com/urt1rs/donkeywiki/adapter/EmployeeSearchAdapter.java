package com.urt1rs.donkeywiki.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 2019/9/5
 * @author Hongzhi.Liu
 *
 * 用于展示干员搜索结果的类
 */
public class EmployeeSearchAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public EmployeeSearchAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public interface HolderType {
        int TITLE = 1;
        int RESULT = 2;
    }
}
