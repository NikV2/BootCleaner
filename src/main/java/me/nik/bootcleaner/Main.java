package me.nik.bootcleaner;

import me.nik.bootcleaner.config.ConfigurationBuilder;
import me.nik.bootcleaner.enums.Directories;
import me.nik.bootcleaner.utils.FileUtils;
import me.nik.bootcleaner.utils.MiscUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

        if (Config.cleanDnsCache) MiscUtils.executeCmdCommand("cmd /C start ipconfig /flushdns");

        if (Config.emptyRecycleBin) MiscUtils.executeCmdCommand("cmd /C rd /s /q %systemdrive%\\$RECYCLE.BIN");

        int cleanedFiles;

        //Execute the cleaning asynchronously
        try {

            cleanedFiles = CompletableFuture
                    .supplyAsync(() -> Arrays.stream(Directories.values())
                            .mapToInt(directory -> FileUtils.deleteFilesFromDirectory(new File(directory.getDirectory())))
                            .sum()).get();

        } catch (ExecutionException | InterruptedException e) {

            JOptionPane.showMessageDialog(null,
                    "Couldn't clean temporary files asynchronously",
                    "BootCleaner",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        if (Config.showMessage) {

            JOptionPane.showMessageDialog(null,
                    "Cleaned " + cleanedFiles + " temporary files",
                    "BootCleaner",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}