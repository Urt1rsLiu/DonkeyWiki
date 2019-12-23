package com.urt1rs.donkeywiki.util

import android.util.SparseArray

import com.urt1rs.donkeywiki.constant.employee.Tag
import com.urt1rs.donkeywiki.entity.employee.Employee

import java.util.ArrayList

/**
 * 2019/9/2
 *
 * @author Hongzhi.Liu
 */
object TagUtils {

    val allTags: List<String>
        get() {
            val tags = ArrayList<String>(Tag.count)

            tags.add(Tag.REMOTE_ATTACK)
            tags.add(Tag.NEARBY_ATTACK)

            tags.add(Tag.ROOKIE)
            tags.add(Tag.SENIOR)
            tags.add(Tag.SUPER_SENIOR)

            tags.add(Tag.MALE)
            tags.add(Tag.FEMALE)

            tags.add(Tag.ADC)
            tags.add(Tag.APC)
            tags.add(Tag.PIONEER)
            tags.add(Tag.THERAPY)
            tags.add(Tag.GUARD)
            tags.add(Tag.SHIELD)
            tags.add(Tag.SPECIAL)
            tags.add(Tag.SUPPORT)

            tags.add(Tag.CURE)
            tags.add(Tag.DEFENSE)
            tags.add(Tag.EXPLODE)
            tags.add(Tag.SURVIVE)
            tags.add(Tag.ATTACK)
            tags.add(Tag.CALL)
            tags.add(Tag.CONTROL)
            tags.add(Tag.WEAKEN)
            tags.add(Tag.SHIFT)
            tags.add(Tag.REVIVAL)
            tags.add(Tag.RETARD)
            tags.add(Tag.HELP)
            tags.add(Tag.GROUP)
            tags.add(Tag.COST_RESTORE)

            return tags
        }


    /**
     * 获取任意满足tag的干员, 并按星级排序
     * @param tags
     * @return
     */
    fun sortEmployeeByTag(tags: List<String>?): SparseArray<Set<Employee>> {
        val employees = EmployeeUtils.allEmployee
        val result = SparseArray<Set<Employee>>()
        return result
        // TODO: 2019/9/12 先筛选出含至少一个tag的干员

    }
}
