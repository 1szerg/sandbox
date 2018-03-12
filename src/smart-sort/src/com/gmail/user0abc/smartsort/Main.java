package com.gmail.user0abc.smartsort;

import java.io.File;

public class Main
{
    /*
    * Usage: smart-sort <-t|-s> <inputFolderPath> <outputFolderPath>
    *     D:/my/sandbox/src/smart-sort/test/output
    *     D:/my/sandbox/src/smart-sort/test/input
    * */
    public static void main(String[] arg)
    {
        SmartSortSettings settings = null;
        try {
            settings = readParams(arg);
        } catch (IllegalArgumentException e) {
            printError(e);
            printUsage();
            return;
        }
        if (settings.mode == SmartSortSettings.Mode.TRAIN) {
            new Trainer().train(settings);
        } else {
            new Sorter().sort(settings);
        }
    }

    private static void printUsage()
    {
        prn("Usage: smart-sort <-t|-s> <inputFolderPath> <outputFolderPath>");
    }

    private static void printError(IllegalArgumentException e)
    {
        prn(e.getLocalizedMessage());
    }

    private static void prn(String message)
    {
        System.out.println(message);
    }

    private static SmartSortSettings readParams(String[] arg)
    {
        if (arg == null || arg.length < 1 || !"-t-s".contains(arg[0])) {
            throw new IllegalArgumentException("Mode should be -s for sorting or -t for training");
        }
        if (arg.length < 2 || arg[1] == null || !new File(arg[1]).exists()) {
            throw new IllegalArgumentException("Input folder is not found");
        }
        if (arg.length < 3 || arg[2] == null || !new File(arg[1]).exists()) {
            throw new IllegalArgumentException("Output folder is not found");
        }
        SmartSortSettings settings = new SmartSortSettings();
        if (arg[0].contains("-t")) {
            settings.mode = SmartSortSettings.Mode.TRAIN;
        } else {
            settings.mode = SmartSortSettings.Mode.SORT;
        }
        settings.inputFolder = new File(arg[1]);
        settings.outputFolder = new File(arg[2]);
        return settings;
    }
}
