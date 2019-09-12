package com.urt1rs.donkeywiki.fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.urt1rs.donkeywiki.R;
import com.urt1rs.donkeywiki.entity.employee.Employee;
import com.urt1rs.donkeywiki.widget.FilterPopupWindow;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HireFragment extends Fragment {

    @BindView(R.id.rl_search_bar)
    public RelativeLayout rlBar;

    private FilterPopupWindow mFilterWindow;


    private SparseArray<Set<Employee>> mEmployees;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_hire, container, false);
        ButterKnife.bind(this, content);
        return content;
    }

    @OnClick(R.id.tv_filter)
    public void onClickFilter() {
        Log.e(getClass().getSimpleName(), "click filter");
        if (null != mFilterWindow && mFilterWindow.isShowing()) {
            mFilterWindow.dismiss();
        } else {
            if (null == mFilterWindow) {
                View filterView = LayoutInflater.from(this.getContext()).inflate(R.layout.popup_filter, null, false);
                mFilterWindow = new FilterPopupWindow(HireFragment.this.getActivity(), filterView,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mFilterWindow.setOnClickOk(tags -> {

                });

            }
            mFilterWindow.showAsDropDown(rlBar);
        }
    }


    @Override
    public void onDestroy() {
        mFilterWindow.destroy();
        super.onDestroy();
    }


}
