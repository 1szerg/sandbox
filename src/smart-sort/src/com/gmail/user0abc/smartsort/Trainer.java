package com.gmail.user0abc.smartsort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Trainer
{
    PluginsManager manager = SmartSorterContext.getPluginsManager();

    public void train(SmartSortSettings settings)
    {
        clearOutputVocabularies(settings);
        Map<File, Set<String>> allWords = loadTargetWords(settings);
        allWords = filterUniqueWordsOnly(allWords);
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
                File[] vs = topic.listFiles((dir, name) -> name == SmartSortSettings.dicName);
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
        Set<String> words = new TreeSet<>();
        FileReaderPlugin plugin = manager.getPluginForType(doc);
        if (plugin != null) {
            words = new TreeSet<>(plugin.readWordsFromFile(doc));
        }
        return words;
    }

    private Map<File, Set<String>> filterUniqueWordsOnly(Map<File, Set<String>> allWords)
    {
        Map<File, Set<String>> filteredWords = new HashMap<>(allWords.size());
        for (File f1 : allWords.keySet()) {
            Set<String> filteredTopic = new TreeSet<>();
            for (String w1 : allWords.get(f1)) {
                w1 = w1.toLowerCase().replaceAll("[^a-z]","").trim();
                if(w1.length() < 1) continue;
                for (File f2 : allWords.keySet()) {
                    if (f2.equals(f1)) continue;
                    if (!allWords.get(f2).contains(w1)) {
                        filteredTopic.add(w1);
                    }
                }
            }
            filteredWords.put(f1,filteredTopic);
        }
        return filteredWords;
    }

    private void saveVocabularies(SmartSortSettings settings, Map<File, Set<String>> allWords)
    {
        for (File f : allWords.keySet()) {
            String vocName = f.getAbsolutePath() + "/"+SmartSortSettings.dicName;
            File voc = new File(vocName);
            if (voc.exists()) {
                voc.delete();
            }
            try {
                Files.createFile(voc.toPath());
            } catch (IOException e) {
                Log.log(e.getLocalizedMessage());
                continue;
            }
            try {
                FileOutputStream fs = new FileOutputStream(voc);
                Log.log("saving " + allWords.get(f).size() + " words into " + voc);
                for (String w : allWords.get(f)) {
                    fs.write(w.getBytes());
                    fs.write(SmartSortSettings.delimeter.getBytes());
                }
                fs.close();
            } catch (FileNotFoundException e) {
                Log.log(e.getLocalizedMessage());
                continue;
            } catch (IOException e) {
                Log.log(e.getLocalizedMessage());
            }
        }
    }
}
