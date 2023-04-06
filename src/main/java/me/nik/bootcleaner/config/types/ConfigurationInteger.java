package me.nik.bootcleaner.config.types;

import me.nik.bootcleaner.config.IConfigurationParser;

public class ConfigurationInteger implements IConfigurationParser<Integer> {
    @Override
    public Integer parse(String value) {
        return Integer.parseInt(value);
    }
}