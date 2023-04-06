package me.nik.bootcleaner.config.types;

import me.nik.bootcleaner.config.IConfigurationParser;

public class ConfigurationLong implements IConfigurationParser<Long> {
    @Override
    public Long parse(String value) {
        return Long.parseLong(value);
    }
}