package com.urt1rs.donkeywiki.entity.employee;


import com.urt1rs.donkeywiki.constant.employee.Position;
import com.urt1rs.donkeywiki.constant.employee.Tag;

import java.util.ArrayList;

/**
 * 德克萨斯
 */
public class Texas extends Employee {

    private static Texas instance;

    private Texas() {
        position = Position.PIONEER;
        tags = new ArrayList<>(5);
        tags.add(Tag.COST_RESTORE);
    }

    private static Texas getInstance() {
        if (null == instance) {
            synchronized (Texas.class) {
                if (null == instance) {
                    instance = new Texas();
                }
            }
        }
        return instance;
    }



}
