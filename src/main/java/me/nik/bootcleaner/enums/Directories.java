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

    DIRECTX_SHADER_CACHE(MiscUtils.USER + "AppData\\Local\\D3DSCache"),

    WINDOWS_UPDATE_CACHE(MiscUtils.ROOT + "Windows\\SoftwareDistribution\\Download"),

    DISCORD_CACHE(MiscUtils.USER + "AppData\\Roaming\\discord\\Cache"),
    DISCORD_CODE_CACHE(MiscUtils.USER + "AppData\\Roaming\\discord\\Code Cache"),
    DISCORD_GPU_CACHE(MiscUtils.USER + "AppData\\Roaming\\discord\\GPUCache"),
    DISCORD_DAWN_CACHE(MiscUtils.USER + "AppData\\Roaming\\discord\\DawnCache"),

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