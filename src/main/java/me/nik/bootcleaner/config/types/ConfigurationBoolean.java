package me.nik.bootcleaner.config.types;

import me.nik.bootcleaner.config.IConfigurationParser;

public class ConfigurationBoolean implements IConfigurationParser<Boolean> {
    @Override
    public Boolean parse(String value) {
        return Boolean.parseBoolean(value);
    }
}