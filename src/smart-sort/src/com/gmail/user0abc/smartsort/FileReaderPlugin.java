package com.gmail.user0abc.smartsort;

import java.io.File;
import java.util.Set;

public interface FileReaderPlugin
{
    Set<String> readWordsFromFile(File doc);

    boolean isFileSupported(File doc);
}
