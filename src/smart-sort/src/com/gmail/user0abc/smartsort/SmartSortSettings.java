package com.gmail.user0abc.smartsort;

import java.io.File;

public class SmartSortSettings
{

    static String dicName = "_abc.voc";
    static String delimeter ="\n";

    public enum Mode
    {
        TRAIN, SORT;
    }

    Mode mode;
    File inputFolder;
    File outputFolder;
}
