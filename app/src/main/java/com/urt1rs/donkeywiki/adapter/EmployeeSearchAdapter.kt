package com.urt1rs.donkeywiki.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.urt1rs.donkeywiki.R

/**
 * 2019/9/5
 * @author Hongzhi.Liu
 *
 * 用于展示干员搜索结果的类
 */
class EmployeeSearchAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root:View = LayoutInflater.from(parent.context).inflate(R.layout.holder_search_result, parent, false);
        return SearchResultHolder(root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getItemCount(): Int {
        return 0
    }


    interface HolderType {
        companion object {
            val TITLE = 1
            val RESULT = 2
        }
    }

    class SearchResultHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }
}
