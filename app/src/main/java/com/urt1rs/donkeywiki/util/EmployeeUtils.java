package com.urt1rs.donkeywiki.util;

import android.util.SparseArray;

import androidx.collection.ArraySet;

import com.urt1rs.donkeywiki.entity.employee.Employee;

import java.util.Set;

/**
 * 2019/9/5
 *
 * @author Hongzhi.Liu
 */
public class EmployeeUtils {

    public static SparseArray<Set<Employee>> getAllEmployee() {
        SparseArray<Set<Employee>> result = new SparseArray<>();
        Set<Employee> rank1 = new ArraySet<>();
        Set<Employee> rank2 = new ArraySet<>();
        Set<Employee> rank3 = new ArraySet<>();
        Set<Employee> rank4 = new ArraySet<>();
        Set<Employee> rank5 = new ArraySet<>();
        Set<Employee> rank6 = new ArraySet<>();

        // TODO: 2019/9/5 add all employee

        result.append(1, rank1);
        result.append(2, rank2);
        result.append(3, rank3);
        result.append(4, rank4);
        result.append(5, rank5);
        result.append(6, rank6);
        return result;
    }

}
