package me.nik.bootcleaner.utils;

import javax.swing.*;
import java.io.IOException;

public final class MiscUtils {

    public static final String ROOT = System.getenv("SystemDrive") + "\\";
    public static final String USER = System.getProperty("user.home") + "\\";

    private MiscUtils() {
    }

    public static String capitalizeFirstLetter(final String data) {

        final char firstLetter = Character.toTitleCase(data.substring(0, 1).charAt(0));

        final String restLetters = data.substring(1).toLowerCase();

        return firstLetter + restLetters;
    }

    public static void executeCmdCommand(final String value) {

        try {

            Runtime.getRuntime().exec(value);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Couldn't execute cmd command: " + value,
                    "BootCleaner by Nik",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
