package com.urt1rs.donkeywiki.util;

import com.urt1rs.donkeywiki.constant.employee.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * 2019/9/2
 *
 * @author Hongzhi.Liu
 */
public class TagUtils {

    public static List<String> getAllTags() {
        List<String> tags = new ArrayList<>(Tag.count);
        tags.add(Tag.REMOTE_ATTACK);

        return tags;
    }
}
