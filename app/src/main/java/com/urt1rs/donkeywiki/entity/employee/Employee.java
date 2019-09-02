package com.urt1rs.donkeywiki.entity.employee;

import android.graphics.Bitmap;

import com.urt1rs.donkeywiki.constant.employee.Position;

import java.util.List;

/**
 * @author Hongzhi.Liu
 * @date 2019/8/23
 */
public class Employee {

    protected String name;

    /**
     * 攻击
     */
    protected int attack;
    protected int defend;
    protected int block;
    /**
     * 星级
     */
    protected int star;
    /**
     * 招募标签
     */
    protected List<String> tags;
    /**
     * 定位
     */
    protected Position position;
    /**
     * 头像
     */
    protected Bitmap avatar;

    public Employee() {

    }



}
