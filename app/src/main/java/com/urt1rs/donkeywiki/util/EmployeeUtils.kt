package com.urt1rs.donkeywiki.util

import android.util.SparseArray

import androidx.collection.ArraySet

import com.urt1rs.donkeywiki.entity.employee.Employee

/**
 * 2019/9/5
 *
 * @author Hongzhi.Liu
 */
object EmployeeUtils {

    // TODO: 2019/9/5 add all employee
    val allEmployee: SparseArray<Set<Employee>>
        get() {
            val result = SparseArray<Set<Employee>>()
            val rank1 = ArraySet<Employee>()
            val rank2 = ArraySet<Employee>()
            val rank3 = ArraySet<Employee>()
            val rank4 = ArraySet<Employee>()
            val rank5 = ArraySet<Employee>()
            val rank6 = ArraySet<Employee>()

            result.append(1, rank1)
            result.append(2, rank2)
            result.append(3, rank3)
            result.append(4, rank4)
            result.append(5, rank5)
            result.append(6, rank6)
            return result
        }

}
