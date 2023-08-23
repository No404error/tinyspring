package com.zhangkie.tinyspring.beans.converter;

public class StringToPrimitiveConverter {
    public static <T> T convert(String str, Class<T> targetType) {
        if (targetType == Integer.class || targetType == int.class) {
            return (T) Integer.valueOf(str);
        } else if (targetType == Long.class || targetType == long.class) {
            return (T) Long.valueOf(str);
        } else if (targetType == Float.class || targetType == float.class) {
            return (T) Float.valueOf(str);
        } else if (targetType == Double.class || targetType == double.class) {
            return (T) Double.valueOf(str);
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            return (T) Boolean.valueOf(str);
        } else if(targetType == String.class)
            return (T) str;
        else {
            throw new IllegalArgumentException("Unsupported target type: " + targetType.getName());
        }
    }
}