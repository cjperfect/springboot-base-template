package com.cj.snippets.util;


import com.cj.snippets.common.enums.ResponseCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CopyUtil {

    /* 单个类拷贝 */
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }

        T obj = null;

        try {
            obj = clazz.newInstance(); // 传过来类的实例
        } catch (Exception e) {
            throw new RuntimeException(ResponseCodeEnum.RC500.getMsg());
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    /* 多个类拷贝 */

    public static <T> List<T> copyList(List source, Class<T> clazz) {

        List<T> resultList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(source)) {
            for (Object obj : source) {
                resultList.add(copy(obj, clazz));
            }
        }

        return resultList;
    }
}