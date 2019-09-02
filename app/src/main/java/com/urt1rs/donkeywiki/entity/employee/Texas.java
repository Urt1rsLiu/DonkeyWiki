package com.urt1rs.donkeywiki.entity.employee;


import com.urt1rs.donkeywiki.constant.employee.Position;
import com.urt1rs.donkeywiki.constant.employee.Tag;

import java.util.ArrayList;

/**
 * 德克萨斯
 */
public class Texas extends Employee {

    public Texas() {
        position = Position.PIONEER;
        tags = new ArrayList<>(5);
        tags.add(Tag.COST_RESTORE);
    }

}
