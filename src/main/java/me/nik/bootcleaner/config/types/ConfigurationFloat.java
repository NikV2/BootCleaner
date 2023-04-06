package me.nik.bootcleaner.config.types;

import me.nik.bootcleaner.config.IConfigurationParser;

public class ConfigurationFloat implements IConfigurationParser<Float> {
    @Override
    public Float parse(String value) {
        return Float.parseFloat(value);
    }
}