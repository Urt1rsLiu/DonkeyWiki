package com.urt1rs.donkeywiki.widget;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.urt1rs.donkeywiki.R;
import com.urt1rs.donkeywiki.constant.employee.Tag;
import com.urt1rs.donkeywiki.util.TagUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterPopupWindow extends PopupWindow {

    private Activity mActivity;
    private TagListAdapter mAdapter;



    @BindView(R.id.rv_tag_list)
    public RecyclerView mTagList;

    public FilterPopupWindow(Activity activity, View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        mActivity = activity;
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
        ButterKnife.bind(this, getContentView());
        mAdapter = new TagListAdapter(mActivity);
        mTagList.setAdapter(mAdapter);
        mTagList.setLayoutManager(new GridLayoutManager(mActivity, TagListAdapter.COLUMN_COUNT));
//        mTagList.addItemDecoration();
    }

    public void destroy() {
        mActivity = null;
    }

    @Override
    public void showAsDropDown(View view) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 0.7f;
        mActivity.getWindow().setAttributes(lp);
        setTouchable(true);
        setOutsideTouchable(true);
        super.showAsDropDown(view);
    }

    @OnClick(R.id.tv_ok)
    public void onClickFilterOk() {

    }

    @OnClick(R.id.tv_reverse_tags)
    public void onClickReverse() {
       mAdapter.clear();
    }

    private class TagListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int COLUMN_COUNT = 4;

        public List<String> tags = TagUtils.getAllTags();

        private SparseArray<String> mSelectTags;

        public TagListAdapter(Context context) {
            mSelectTags = new SparseArray<>(6);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TagViewHolder holder = new TagViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.holder_tag, null, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TagViewHolder viewHolder = (TagViewHolder) holder;
            viewHolder.tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return Tag.count;
        }

        private void clear() {
            for (int i = 0; i < mSelectTags.size(); i++) {
                int key = mSelectTags.keyAt(i);
                mSelectTags.remove(key);
                notifyItemRemoved(mSelectTags.keyAt(i));
            }
        }
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_tag)
        public TextView tag;
        private TagViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}