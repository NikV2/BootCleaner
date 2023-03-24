package me.nik.bootcleaner.utils;

import java.io.File;

public final class FileUtils {

    public FileUtils() {
    }

    public static int deleteFilesFromDirectory(final File directory) {
        if (!directory.exists()) return 0;

        int count = 0;

        File[] files = directory.listFiles();

        if (files != null) {

            for (File file : files) {

                if (file.isDirectory()) deleteFilesFromDirectory(file);

                count += file.delete() ? 1 : 0;
            }
        }

        return count;
    }
}