package me.nik.bootcleaner.enums;

import me.nik.bootcleaner.utils.MiscUtils;

public enum Directories {

    AMD_LOGS(MiscUtils.ROOT + "Program Files\\AMD\\CIM\\Log"),

    PACKAGE_CACHE(MiscUtils.ROOT + "ProgramData\\Package Cache"),

    PREFETCH(MiscUtils.ROOT + "Windows\\Prefetch"),

    THUMBNAIL_CACHE(MiscUtils.USER + "AppData\\Local\\Microsoft\\Windows\\Explorer"),

    CRASH_DUMPS(MiscUtils.USER + "AppData\\Local\\CrashDumps"),

    CRYPTNET_URL_CACHE_CONTENT(MiscUtils.USER + "AppData\\LocalLow\\Microsoft\\CryptnetUrlCache\\Content"),
    CRYPTNET_URL_CACHE_METADATA(MiscUtils.USER + "AppData\\LocalLow\\Microsoft\\CryptnetUrlCache\\MetaData"),

    CBS_TEMP(MiscUtils.ROOT + "Windows\\CbsTemp"),

    WINDOWS_LOGS(MiscUtils.ROOT + "Windows\\Logs"),

    WINDOWS_TEMP(MiscUtils.ROOT + "Windows\\temp"),

    WINDOWS_KERNEL_REPORTS(MiscUtils.ROOT + "Windows\\LiveKernelReports"),

    WINDOWS_MINIDUMP(MiscUtils.ROOT + "Windows\\Minidump"),

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
