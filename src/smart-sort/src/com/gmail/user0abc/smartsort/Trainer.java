package com.gmail.user0abc.smartsort;

import java.io.File;
import java.util.*;

public class Trainer
{
    PluginsManager manager = SmartSorterContext.getPluginsManager();

    public void train(SmartSortSettings settings)
    {
        clearOutputVocabularies(settings);
        Map<File, Set<String>> allWords = loadTargetWords(settings);
        keepUniqueWordsOnly(allWords);
        saveVocabularies(settings, allWords);
    }

    private void clearOutputVocabularies(SmartSortSettings settings)
    {
        if (!settings.outputFolder.isDirectory()) {
            throw new IllegalArgumentException("Output is not a folder: " + settings.outputFolder.getPath());
        }
        File[] outputContent = settings.outputFolder.listFiles();
        for (File topic : outputContent) {
            if (topic.isDirectory()) {
                Log.log("clearing " + topic.getPath());
                File[] vs = topic.listFiles((dir, name) -> name == "s-s.voc.txt");
                for (File v : vs) {
                    v.delete();
                }
            }
        }
    }

    private Map<File, Set<String>> loadTargetWords(SmartSortSettings settings)
    {
        Map<File, Set<String>> words = new HashMap<>();
        File[] outputContent = settings.outputFolder.listFiles();
        for (File topic : outputContent) {
            if (topic.isDirectory()) {
                Log.log("reading words from " + topic.getPath());
                words.put(topic, readWordsFromFolder(topic));
            }
        }
        return words;
    }

    private Set<String> readWordsFromFolder(File topic)
    {
        Set<String> words = new TreeSet<>();
        File[] docs = topic.listFiles();
        for (File doc : docs) {
            Log.log("reading words from " + doc.getPath());
            words.addAll(readWordsFromFile(doc));
        }
        Log.log("got " + words.size() + " words from " + topic.getPath());
        return words;
    }

    private Set<String> readWordsFromFile(File doc)
    {
        Set<String> words = new HashSet<>();
        FileReaderPlugin plugin = manager.getPluginForType(doc);
        if (plugin != null) {
            words = plugin.readWordsfromFile(doc);
        }
        return words;
    }

    private void keepUniqueWordsOnly(Map<File, Set<String>> allWords)
    {

    }

    private void saveVocabularies(SmartSortSettings settings, Map<File, Set<String>> allWords)
    {

    }
}
