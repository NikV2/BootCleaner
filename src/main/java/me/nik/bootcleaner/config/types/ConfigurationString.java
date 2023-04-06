package me.nik.bootcleaner.config.types;

import me.nik.bootcleaner.config.IConfigurationParser;

public class ConfigurationString implements IConfigurationParser<String> {
    @Override
    public String parse(String value) {
        return value;
    }
}