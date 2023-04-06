package me.nik.bootcleaner;

import me.nik.bootcleaner.config.ConfigurationOption;

public class Config {

    @ConfigurationOption
    public static boolean emptyRecycleBin = true;

    @ConfigurationOption
    public static boolean cleanDnsCache = true;

    @ConfigurationOption
    public static boolean showMessage = true;
}