package com.alevel.java.nix.annotations.csv;

import com.alevel.java.nix.ionio.HandlerCSV;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InitFromCSV<T> {

    private final HandlerCSV handlerCSV;

    private final Class<T> tClass;

    private final Map<Class<?>, Setter> SETTERS = Map.ofEntries(
            Map.entry(String.class, (target, field, value) -> field.set(target, value)),

            Map.entry(int.class, ((target, field, value) -> field.setInt(target, Integer.parseInt(value)))),
            Map.entry(Integer.class, (target, field, value) -> field.setInt(target, Integer.parseInt(value))),

            Map.entry(float.class, (target, field, value) -> field.setFloat(target, Float.parseFloat(value))),
            Map.entry(Float.class, (target, field, value) -> field.setFloat(target, Float.parseFloat(value))),

            Map.entry(double.class, (target, field, value) -> field.setDouble(target, Double.parseDouble(value))),
            Map.entry(Double.class, (target, field, value) -> field.setDouble(target, Double.parseDouble(value))),

            Map.entry(boolean.class, (target, field, value) -> field.setBoolean(target, Boolean.parseBoolean(value))),
            Map.entry(Boolean.class, (target, field, value) -> field.setBoolean(target, Boolean.parseBoolean(value))),

            Map.entry(long.class, (target, field, value) -> field.setLong(target, Long.parseLong(value))),
            Map.entry(Long.class, (target, field, value) -> field.setLong(target, Long.parseLong(value))),

            Map.entry(char.class, (target, field, value) -> field.setChar(target, value.charAt(0))),
            Map.entry(Character.class, (target, field, value) -> field.setChar(target, value.charAt(0)))
    );

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
                    if (type == String.class) {
                        field.set(target, "");
                    }
                    continue;
                }
                SETTERS.get(type).set(target, field, value);
            }
            return target;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new UnsupportedOperationException(e);
        }
    }

}
