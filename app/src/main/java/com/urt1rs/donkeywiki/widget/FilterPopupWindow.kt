package com.urt1rs.donkeywiki.widget

import android.app.Activity
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.urt1rs.donkeywiki.DonkeyWikiApplication
import com.urt1rs.donkeywiki.R
import com.urt1rs.donkeywiki.constant.employee.Tag
import com.urt1rs.donkeywiki.util.TagUtils

import java.util.ArrayList
import java.util.LinkedList
import java.util.Queue

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class FilterPopupWindow(private var mActivity: Activity, contentView: View, width: Int, height: Int, focusable: Boolean) : PopupWindow(contentView, width, height, focusable) {
    private val mAdapter: TagListAdapter

    @JvmField
    @BindView(R.id.rv_tag_list)
    var mTagList: RecyclerView? = null

    private var mOnClickOk: OnClickOkListener? = null

    init {
        setOnDismissListener {
            val lp = mActivity.window.attributes
            lp.alpha = 1f
            mActivity.window.attributes = lp
        }
        ButterKnife.bind(this, getContentView())
        mAdapter = TagListAdapter()
        mTagList!!.layoutManager = GridLayoutManager(mActivity, COLUMN_COUNT)
        mTagList!!.adapter = mAdapter
        mTagList!!.addItemDecoration(TagItemDecoration())
    }


    override fun showAsDropDown(view: View) {
        val lp = mActivity.window.attributes
        lp.alpha = 0.7f
        mActivity.window.attributes = lp
        isTouchable = true
        isOutsideTouchable = true
        super.showAsDropDown(view)
    }

    @OnClick(R.id.tv_ok)
    fun onClickFilterOk() {
        dismiss()
        if (null != mOnClickOk) {
            mOnClickOk!!.onClickFilterOk(mAdapter.selectTags)
        }
    }

    fun setOnClickOk(onClickOk: OnClickOkListener) {
        mOnClickOk = onClickOk
    }

    interface OnClickOkListener {
        /**
         * 点击确定后回调
         * @param tags tag
         */
        fun onClickFilterOk(tags: MutableList<String>)
    }


    @OnClick(R.id.tv_reverse_tags)
    fun onClickReverse() {
        mAdapter.clear()
    }

    private inner class TagListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val tags = TagUtils.allTags

        //选中tag的index
        private val mSelectTags: Queue<Int>

        internal val selectTags: MutableList<String>
            get() {
                val tags = ArrayList<String>(mSelectTags.size)
                for (i in mSelectTags) {
                    tags.add(tags[i!!])
                }
                return tags
            }

        init {
            mSelectTags = LinkedList()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return TagViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.holder_tag, parent, false))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewHolder = holder as TagViewHolder
            viewHolder.tag!!.setOnClickListener {
                if (mSelectTags.contains(position)) {
                    //删除标签
                    mSelectTags.remove(position)
                    notifyItemChanged(position)
                } else {
                    //添加标签，超过5个时删除头一个，保持5个
                    if (mSelectTags.size >= MAX_SELECT_TAGS) {
                        val removeTag = mSelectTags.remove()
                        notifyItemChanged(removeTag)
                    }
                    mSelectTags.add(position)
                    notifyItemChanged(position)
                }
            }
            viewHolder.tag!!.isSelected = mSelectTags.contains(position)
            viewHolder.tag!!.text = tags[position]
        }

        override fun getItemCount(): Int {
            return tags.size
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            val manager = recyclerView.layoutManager
            if (manager is GridLayoutManager) {
                val gridManager = manager as GridLayoutManager?
                gridManager?.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val spanSize: Int
                        val tag = tags[position]
                        spanSize = if (tag == Tag.NEARBY_ATTACK || tag == Tag.FEMALE) {
                            3
                        } else if (tag == Tag.SUPER_SENIOR) {
                            2
                        } else {
                            1
                        }
                        return spanSize
                    }
                }
            }
        }


        internal fun clear() {
            while (mSelectTags.size > 0) {
                val key = mSelectTags.remove()
                notifyItemChanged(key)
            }
        }


    }

    inner class TagViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @JvmField
        @BindView(R.id.tv_tag)
        var tag: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
        }

    }

    inner class TagItemDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = DonkeyWikiApplication.appContext!!.resources.getDimensionPixelOffset(R.dimen.x15)
            outRect.top = outRect.bottom
            outRect.left = DonkeyWikiApplication.appContext!!.resources.getDimensionPixelOffset(R.dimen.x30)
        }

    }

    companion object {

        const val COLUMN_COUNT = 4

        const val MAX_SELECT_TAGS = 5
    }
}