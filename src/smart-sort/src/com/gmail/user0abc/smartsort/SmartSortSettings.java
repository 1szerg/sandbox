package com.gmail.user0abc.smartsort;

import java.io.File;

public class SmartSortSettings
{
    public enum Mode
    {
        TRAIN, SORT;
    }

    Mode mode;
    File inputFolder;
    File outputFolder;
}
