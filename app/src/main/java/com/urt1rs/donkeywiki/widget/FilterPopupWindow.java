package com.urt1rs.donkeywiki.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.urt1rs.donkeywiki.DonkeyWikiApplication;
import com.urt1rs.donkeywiki.R;
import com.urt1rs.donkeywiki.constant.employee.Tag;
import com.urt1rs.donkeywiki.util.TagUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        mTagList.setLayoutManager(new GridLayoutManager(mActivity, TagListAdapter.COLUMN_COUNT));
        mTagList.setAdapter(mAdapter);
        mTagList.addItemDecoration(new TagItemDecoration());
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

        public static final int MAX_SELECT_TAGS = 5;

        private List<String> tags = TagUtils.getAllTags();

        //选中tag的index
        private Queue<Integer> mSelectTags;

        public TagListAdapter(Context context) {
            mSelectTags = new LinkedList<Integer>();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TagViewHolder holder = new TagViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.holder_tag, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TagViewHolder viewHolder = (TagViewHolder) holder;
            viewHolder.tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectTags.contains(position)) {
                        //删除标签
                        mSelectTags.remove(position);
                        notifyItemChanged(position);
                    } else {
                        //添加标签，超过5个时删除头一个，保持5个
                        if (mSelectTags.size() >= MAX_SELECT_TAGS) {
                            int removeTag = mSelectTags.remove();
                            notifyItemChanged(removeTag);
                        }
                        mSelectTags.add(position);
                        notifyItemChanged(position);
                    }
                }
            });
            viewHolder.tag.setSelected(mSelectTags.contains(position));
            viewHolder.tag.setText(tags.get(position));
        }

        @Override
        public int getItemCount() {
            return tags.size();
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int spanSize;
                        String tag = tags.get(position);
                        if (tag.equals(Tag.NEARBY_ATTACK) || tag.equals(Tag.FEMALE)) {
                            spanSize = 3;
                        } else if (tag.equals(Tag.SUPER_SENIOR)) {
                            spanSize = 2;
                        } else {
                            spanSize = 1;
                        }
                        return spanSize;
                    }

                });

            }

        }

//        @Override
//        public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
//            super.onViewAttachedToWindow(holder);
//            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
//                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager
//                        .LayoutParams) lp;
//                p.setFullSpan(holder.getLayoutPosition() == 0);
//            }
//        }

        private void clear() {
            while (mSelectTags.size() > 0) {
                int key = mSelectTags.remove();
                notifyItemChanged(key);
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

    public class TagItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
                state) {
            outRect.top = outRect.bottom = DonkeyWikiApplication.getAppContext().getResources().getDimensionPixelOffset(R.dimen.x15);
            outRect.left = DonkeyWikiApplication.getAppContext().getResources().getDimensionPixelOffset(R.dimen.x30);
        }

    }
}