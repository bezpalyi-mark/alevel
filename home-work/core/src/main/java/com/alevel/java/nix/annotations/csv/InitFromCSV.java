package com.alevel.java.nix.annotations.csv;

import com.alevel.java.nix.ionio.HandlerCSV;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitFromCSV<T> {

    private HandlerCSV handlerCSV;

    private Class<T> tClass;

    public InitFromCSV(HandlerCSV handlerCSV, Class<T> tClass) {
        this.handlerCSV = handlerCSV;
        this.tClass = tClass;
    }

    public List<T> getList() {
        List<T> tList = new ArrayList<>();
        for (int i = 0, size = handlerCSV.size(); i < size; i++) {
            tList.add(getInstance(i));
        }
        return tList;
    }

    private T getInstance(int row) {
        try {
            Constructor<T> constructor = tClass.getConstructor();
            T target = constructor.newInstance();

            for (Field field : tClass.getFields()) {
                field.setAccessible(true);

                FillByCSV key = field.getAnnotation(FillByCSV.class);
                if (key == null) continue;

                Class<?> type = field.getType();
                String value = handlerCSV.get(row, key.value());
                if (value.isEmpty()) {
                    value = "0";
                }
                if (type == String.class) {
                    field.set(target, value);
                } else if (type == float.class) {
                    field.setFloat(target, Float.parseFloat(value));
                } else if (type == int.class) {
                    field.setInt(target, Integer.parseInt(value));
                } else if (type == double.class) {
                    field.setDouble(target, Double.parseDouble(value));
                } else if (type == char.class) {
                    field.setChar(target, value.charAt(0));
                } else if (type == long.class) {
                    field.setLong(target, Long.parseLong(value));
                } else if (type == boolean.class) {
                    field.setBoolean(target, Boolean.parseBoolean(value));
                }
            }
            return target;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new UnsupportedOperationException(e);
        }
    }

}
