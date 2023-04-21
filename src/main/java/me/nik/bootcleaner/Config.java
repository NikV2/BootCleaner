package me.nik.bootcleaner;

import me.nik.bootcleaner.config.ConfigurationOption;

public class Config {

    @ConfigurationOption
    public static boolean emptyRecycleBin = true, cleanDNSCache = true, clearWindowsTemp = true, cleanLocalTemp = true,
	   cleanPrefetch = true, cleanThumbnailCache = true, cleanCBSTemp = true, cleanWindowsLogs = true,
	   cleanFileExplorerCache = true, cleanNetCache = true, cleanDXShaderCache = true, cleanWindowsUpdateCache = true;
}
