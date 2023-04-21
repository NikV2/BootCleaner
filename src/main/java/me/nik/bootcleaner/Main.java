package me.nik.bootcleaner;

import me.nik.bootcleaner.config.ConfigurationBuilder;
import me.nik.bootcleaner.utils.FileUtils;
import me.nik.bootcleaner.utils.MiscUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //Load the configuration file
        try {
            new ConfigurationBuilder(Config.class, new File("config.yml")).build(true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Configuration Error, " + e.getLocalizedMessage(),
                    "BootCleaner",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }

	File recycleBinPath = new File("%systemdrive%\\$RECYCLE.BIN"), windowsTempPath = new File("%systemdrive%\\Windows\\Temp"),
	     localTempPath = new File(MiscUtils.USER + "Appdata\\Local\\Temp"), prefetchPath = new File("%systemdrive%\\Windows\\Prefetch"),
	     thumbnailCachePath = new File(MiscUtils.USER + "Appdata\\Local\\Microsoft\\Windows\\Explorer"), cbsTempPath = new File("%systemdrive%\\Windows\\CbsTemp"),
	     windowsLogsPath = new File("%systemdrive%\\Windows\\Logs"), fileExplorerCachePath = new File(MiscUtils.USER + "Appdata\\Roaming\\Microsoft\\Windows\\Recent\\AutomaticDestinations"),
	     netCachePath = new File(MiscUtils.USER + "Appdata\\Local\\Microsoft\\Windows\\INetCache\\IE"), DXShaderCachePath = new File(MiscUtils.USER + "Appdata\\Local\\D3DSCache"),
	     windowsUpdateCachePath = new File("%systemdrive%\\Windows\\SoftwareDistribution\\Download");

        if (Config.emptyRecycleBin) FileUtils.deleteFilesFromDirectory(recycleBinPath);

        if (Config.cleanDNSCache) MiscUtils.executeCmdCommand("cmd /C start ipconfig /flushdns");

	if (Config.clearWindowsTemp) FileUtils.deleteFilesFromDirectory(windowsTempPath);

	if (Config.cleanLocalTemp) FileUtils.deleteFilesFromDirectory(localTempPath);

	if (Config.cleanPrefetch) FileUtils.deleteFilesFromDirectory(prefetchPath);

	if (Config.cleanThumbnailCache) FileUtils.deleteFilesFromDirectory(thumbnailCachePath);

	if (Config.cleanCBSTemp) FileUtils.deleteFilesFromDirectory(cbsTempPath);

	if (Config.cleanWindowsLogs) FileUtils.deleteFilesFromDirectory(windowsLogsPath);

	if (Config.cleanFileExplorerCache) FileUtils.deleteFilesFromDirectory(fileExplorerCachePath);

	if (Config.cleanNetCache) FileUtils.deleteFilesFromDirectory(netCachePath);

	if (Config.cleanDXShaderCache) FileUtils.deleteFilesFromDirectory(DXShaderCachePath);

	if (Config.cleanWindowsUpdateCache) FileUtils.deleteFilesFromDirectory(windowsUpdateCachePath);
    }
}
