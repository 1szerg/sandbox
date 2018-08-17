package com.gmail.user0abc.smartsort;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class TextFileReaderPlugin implements FileReaderPlugin
{
    private static int lettersRangeStart = 33;
    private static int lettersRangeEnd = 128;

    @Override
    public Set<String> readWordsFromFile(File doc)
    {
        try {
            Set<String> words = new TreeSet<>();
            words.addAll(
                    Arrays.asList(
                            new String( Files.readAllBytes(doc.toPath())).toLowerCase().split("[^a-z]")
                    )
            );
            return words;
        } catch (Exception e) {
            Log.log("Error: " + e);
        }
        return null;
    }

    @Override
    public boolean isFileSupported(File doc)
    {
        return doc.getPath().trim().endsWith(".txt");
    }
}
