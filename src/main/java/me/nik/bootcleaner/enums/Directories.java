package me.nik.bootcleaner.enums;

import me.nik.bootcleaner.utils.MiscUtils;

public enum Directories {
    PREFETCH(MiscUtils.ROOT + "Windows\\Prefetch"),
    THUMBNAIL_CACHE(MiscUtils.USER + "AppData\\Local\\Microsoft\\Windows\\Explorer"),
    CBS_TEMP(MiscUtils.ROOT + "Windows\\CbsTemp"),
    WINDOWS_LOGS(MiscUtils.ROOT + "Windows\\Logs"),
    WINDOWS_TEMP(MiscUtils.ROOT + "Windows\\temp"),
    FILE_EXPLORER_CACHE(MiscUtils.USER + "AppData\\Roaming\\Microsoft\\Windows\\Recent\\AutomaticDestinations"),
    NET_CACHE(MiscUtils.USER + "AppData\\Local\\Microsoft\\Windows\\INetCache\\IE"),
    TEMP(MiscUtils.USER + "AppData\\Local\\Temp");

    private final String directory;

    Directories(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    public String getDirectoryType() {
        return MiscUtils.capitalizeFirstLetter(this.toString()).replace("_", " ");
    }
}