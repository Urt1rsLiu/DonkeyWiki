package com.urt1rs.donkeywiki.entity.employee


import com.urt1rs.donkeywiki.constant.employee.Position
import com.urt1rs.donkeywiki.constant.employee.Tag

import java.util.ArrayList

/**
 * 德克萨斯
 */
class Texas private constructor() : Employee() {

    init {
        position = Position.PIONEER
        tags = ArrayList(5)
        tags!!.add(Tag.COST_RESTORE)
    }

    companion object {

        private var instance: Texas? = null

        private fun getInstance(): Texas {
            if (null == instance) {
                synchronized(Texas::class.java) {
                    if (null == instance) {
                        instance = Texas()
                    }
                }
            }
            return instance!!
        }
    }


}
