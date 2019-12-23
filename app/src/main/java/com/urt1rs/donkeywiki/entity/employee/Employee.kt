package com.urt1rs.donkeywiki.entity.employee

import com.urt1rs.donkeywiki.constant.employee.Position

/**
 * @author Hongzhi.Liu
 * @date 2019/8/23
 */
open class Employee {

    protected var name: String? = null

    /**
     * 攻击
     */
    protected var attack: Int = 0
    protected var defend: Int = 0
    protected var block: Int = 0
    /**
     * 星级
     */
    protected var star: Int = 0
    /**
     * 招募标签
     */
    protected var tags: MutableList<String>? = null
    /**
     * 定位
     */
    protected var position: Position? = null

    override fun equals(o: Any?): Boolean {
        return if (o !is Employee) {
            false
        } else name == o.name
    }

    override fun hashCode(): Int {
        return name!!.hashCode()
    }


}
