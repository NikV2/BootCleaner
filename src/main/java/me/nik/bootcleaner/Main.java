package me.nik.bootcleaner;

import me.nik.bootcleaner.enums.Directories;
import me.nik.bootcleaner.utils.FileUtils;
import me.nik.bootcleaner.utils.MiscUtils;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {


        MiscUtils.executeCmdCommand("cmd /C start ipconfig /flushdns");

        int cleanedFiles;

        try {

            cleanedFiles = CompletableFuture
                    .supplyAsync(() -> Arrays.stream(Directories.values())
                            .mapToInt(directory -> FileUtils.deleteFilesFromDirectory(new File(directory.getDirectory())))
                            .sum()).get();

        } catch (ExecutionException | InterruptedException e) {

            JOptionPane.showMessageDialog(null,
                    "Couldn't clean temporary files asynchronously",
                    "BootCleaner by Nik",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        JOptionPane.showMessageDialog(null,
                "Cleaned " + cleanedFiles + " temporary files",
                "BootCleaner by Nik",
                JOptionPane.PLAIN_MESSAGE);
    }
}