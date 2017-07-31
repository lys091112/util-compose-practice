package com.github.mock.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class NameUtils {

    public String upperFirstLetter(String field) {
        if (Character.isUpperCase(field.charAt(0))) {
            return field;
        }
        return String.valueOf(Character.toUpperCase(field.charAt(0))) + field.substring(1);
    }

    public String setMethoodName(String fieldName) {
        return "set" + NameUtils.upperFirstLetter(fieldName);
    }

    public Object convertValueType(Class<?> type, String property) {
        if (type == String.class) {
            return property;
        } else if (type == Integer.class) {
            return Integer.valueOf(property);
        } else if (type == Long.class) {
            return Long.valueOf(property);
        }
        throw new IllegalArgumentException("暂不支持的数据类型");
    }
}
