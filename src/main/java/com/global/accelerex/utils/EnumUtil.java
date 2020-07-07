package com.global.accelerex.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tobi.oladimeji on 06/24/2019
 */
public class EnumUtil {

    public static Object[] getEnumConstants(Class<? extends Enum> e) {
        try {
            Method expected = e.getMethod("expected");
            // If method exists, this is an Arbiter defined enum
            return (Object[]) expected.invoke(e);
        } catch (NoSuchMethodException | SecurityException |
                IllegalAccessException | InvocationTargetException ex) {
            return e.getEnumConstants();
        }
    }

    public static Enum getEnumValue(Class<? extends Enum> e, String o) {
        try {
            Method expected = o.getClass().getMethod("from");
            // If method exists, this is an Arbiter defined enum
            return (Enum) expected.invoke(o);
        } catch (NoSuchMethodException | SecurityException |
                IllegalAccessException | InvocationTargetException ex) {
            try {
                return Enum.valueOf(e, o);
            } catch (IllegalArgumentException exc) {
                return null;
            }
        }
    }
}
