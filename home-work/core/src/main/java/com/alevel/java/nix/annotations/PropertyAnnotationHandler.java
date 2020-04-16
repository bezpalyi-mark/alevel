package com.alevel.java.nix.annotations;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertyAnnotationHandler {
    public static void initializeProperties(Object instance, Class<?> clazz, String configName) throws IllegalAccessException {
        if (instance == null) return;
        Properties properties = loadProperties(configName);
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                field.setAccessible(true);
                PropertyKey annotation = field.getAnnotation(PropertyKey.class);
                if (field.getType() == int.class) {
                    field.setInt(instance, Integer.parseInt(properties.getProperty(annotation.value())));
                }
                if (field.getType() == String.class) {
                    field.set(instance, properties.getProperty(annotation.value()));
                }
            }
        }

    }

    private static Properties loadProperties(String configName) {
        var props = new Properties();

        try (InputStream input = PropertyAnnotationHandler.class.getResourceAsStream(configName)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

}
