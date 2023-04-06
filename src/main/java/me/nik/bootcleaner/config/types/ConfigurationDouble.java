package me.nik.bootcleaner.config.types;

import me.nik.bootcleaner.config.IConfigurationParser;

public class ConfigurationDouble implements IConfigurationParser<Double> {
    @Override
    public Double parse(String value) {
        return Double.parseDouble(value);
    }
}