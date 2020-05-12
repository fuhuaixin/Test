package com.example.test0.utlis;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ListUtils {

    /**
     * 去list中重复
     *
     * @param list
     * @return
     */
    public static List<String> removeDuplicate(List<String> list) {
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

}
