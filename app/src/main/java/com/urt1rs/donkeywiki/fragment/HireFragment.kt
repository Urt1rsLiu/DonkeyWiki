package com.urt1rs.donkeywiki.fragment

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment

import com.urt1rs.donkeywiki.R
import com.urt1rs.donkeywiki.entity.employee.Employee
import com.urt1rs.donkeywiki.widget.FilterPopupWindow

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class HireFragment : Fragment() {

    @JvmField
    @BindView(R.id.rl_search_bar)
    var rlBar: RelativeLayout? = null

    var mFilterWindow: FilterPopupWindow? = null


    private val mEmployees: SparseArray<Set<Employee>>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val content = inflater.inflate(R.layout.fragment_hire, container, false)
        ButterKnife.bind(this, content)
        return content
    }

    @OnClick(R.id.tv_filter)
    public fun onClickFilter() {
        if (null != mFilterWindow && mFilterWindow!!.isShowing) {
            mFilterWindow!!.dismiss()
        } else {
            if (null == mFilterWindow) {
                val filterView = LayoutInflater.from(this.context).inflate(R.layout.popup_filter, null, false)
                mFilterWindow = FilterPopupWindow(activity!!, filterView,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
                mFilterWindow!!.setOnClickOk(object : FilterPopupWindow.OnClickOkListener {
                    override fun onClickFilterOk(tags: MutableList<String>) {

                    }
                })

            }
            mFilterWindow!!.showAsDropDown(rlBar!!)
        }
    }


}
