package com.gmail.user0abc.smartsort;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

public class TextFileReaderPlugin implements FileReaderPlugin
{
    private static int lettersRangeStart = 33;
    private static int lettersRangeEnd = 128;

    @Override
    public Set<String> readWordsFromFile(File doc)
    {
        try {
            FileInputStream inputStream = new FileInputStream(doc);
            byte[] buffer = new byte[1024];
            int len = -1;
            Set<String> words = new HashSet<>();
            while ((len = inputStream.read(buffer)) > 0) {
                StringBuilder cache = new StringBuilder();
                for (int i = 0; i < len; i++) {
                    byte letter = buffer[i];
                    if (letter <= lettersRangeEnd && letter >= lettersRangeStart) {
                        cache.append((char)letter);
                    } else {
                        words.add(cache.toString().trim().toLowerCase());
                        cache = new StringBuilder();
                    }
                }
            }
            return words;
        } catch (Exception e) {
            Log.log("Error " + e);
        }
        return null;
    }

    @Override
    public boolean isFileSupported(File doc)
    {
        return doc.getPath().trim().endsWith(".txt");
    }
}
