package me.nik.bootcleaner.config;

import me.nik.bootcleaner.config.types.ConfigurationBoolean;
import me.nik.bootcleaner.config.types.ConfigurationDouble;
import me.nik.bootcleaner.config.types.ConfigurationFloat;
import me.nik.bootcleaner.config.types.ConfigurationInteger;
import me.nik.bootcleaner.config.types.ConfigurationLong;
import me.nik.bootcleaner.config.types.ConfigurationString;
import me.nik.bootcleaner.config.types.ConfigurationStringArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationBuilder {

    private final File configFile;
    private final ConfigurationProperties properties;
    private final Class<?> configclass;
    private final Map<Class<?>, IConfigurationParser<?>> configurationParsers = new HashMap<>();

    public ConfigurationBuilder(Class<?> configclass, File configFile) {
        this.configFile = configFile;
        this.configclass = configclass;
        this.properties = new ConfigurationProperties();
        loadParsers();
    }

    /**
     * loads the configuration parsers for each type
     */
    private void loadParsers() {
        List<Class<? extends IConfigurationParser<?>>> classes = new ArrayList<>();

        classes.add(ConfigurationBoolean.class);
        classes.add(ConfigurationFloat.class);
        classes.add(ConfigurationInteger.class);
        classes.add(ConfigurationLong.class);
        classes.add(ConfigurationString.class);
        classes.add(ConfigurationDouble.class);
        classes.add(ConfigurationStringArray.class);

        configurationParsers.put(int.class, new ConfigurationInteger());
        configurationParsers.put(boolean.class, new ConfigurationBoolean());
        configurationParsers.put(double.class, new ConfigurationDouble());
        configurationParsers.put(long.class, new ConfigurationLong());
        configurationParsers.put(float.class, new ConfigurationFloat());
        configurationParsers.put(String[].class, new ConfigurationStringArray());

        for (Class<? extends IConfigurationParser<?>> parserclass : classes) {
            try {
                Class<?> parserType = (Class<?>) ((ParameterizedType) parserclass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
                IConfigurationParser<?> parserInstance = parserclass.getConstructor().newInstance();
                configurationParsers.put(parserType, parserInstance);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the configClass's variables with the configFile's values
     *
     * @param cleanfile clear the File of all undefined variables
     * @throws IOException file can't be accessed
     */
    public void build(boolean cleanfile) throws IOException {
        if (configFile == null) throw new IllegalStateException("File not initialized");

        if (configFile.exists()) {
            properties.load(new FileInputStream(configFile));
        }

        ConfigurationProperties cleanProperties = new ConfigurationProperties();

        for (Field field : configclass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(ConfigurationOption.class)) {
                continue;
            }
            try {
                boolean isPrivate = !field.isAccessible();
                if (isPrivate) {
                    field.setAccessible(true);
                }
                String variableName = field.getName().toLowerCase();
                Object defaultValue = field.get(null);
                Object value = configFile.exists() ? properties.getOrDefault(variableName, defaultValue) : defaultValue;
                if (configurationParsers.containsKey(field.getType())) {
                    field.set(null, configurationParsers.get(field.getType()).parse(String.valueOf(value)));
                    properties.setProperty(variableName, configurationParsers.get(field.getType()).toStringValue(field.get(null)));
                    cleanProperties.setProperty(variableName, properties.getProperty(variableName));
                } else {
                    throw new IOException("Unknown Configuration Type. Variable name: '" + field.getName() + "'; Unknown Class: " + field.getType().getName());
                }
                if (isPrivate) {
                    field.setAccessible(false);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (cleanfile) {
            cleanProperties.store(new FileOutputStream(configFile), null);
        } else {
            properties.store(new FileOutputStream(configFile), null);
        }
    }
}