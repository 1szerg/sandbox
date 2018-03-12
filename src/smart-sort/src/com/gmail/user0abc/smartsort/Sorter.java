package com.gmail.user0abc.smartsort;

import com.sun.deploy.util.OrderedHashSet;

import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Sorter
{
    public void sort(SmartSortSettings settings)
    {
        File in = settings.inputFolder;
        File out = settings.outputFolder;
        File[] topics = out.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return dir.isDirectory();
            }
        });
        Map<File, Set<String>> dics = readDics(topics);
        Set<FileReaderPlugin> plugins = SmartSorterContext.getPluginsManager().registeredPlugins;
        for(File inFile : in.listFiles()){
            for(FileReaderPlugin plugin : plugins){
                if(plugin.isFileSupported(inFile)){
                    int maxScore = 0;
                    File target = null;
                    for (File dicKey : dics.keySet()){
                        int tempScore = getScore(plugin.readWordsFromFile(inFile), dics.get(dicKey));
                        if(tempScore > maxScore){
                            maxScore = tempScore;
                            target = dicKey;
                        }
                    }
                    if(maxScore > 0 && target != null){
                        moveFile(inFile, target);
                    }
                }
            }
        }
    }

    private void moveFile(File inFile, File target)
    {
        try {
            Log.log("Moving "+inFile.getName()+" to "+target.getPath());
            File newName = new File(target.getAbsolutePath()+"/"+inFile.getName());
            Files.move(inFile.toPath(), newName.toPath());
        } catch (IOException e) {
            Log.log("ERROR: Cant move file "+inFile+" to "+target+" due to "+e.getLocalizedMessage());
        }
    }

    private Integer getScore(Set<String> s1, Set<String> s2)
    {
        Iterator<String> i1 = s1.iterator();
        Iterator<String> i2 = s2.iterator();
        int score = 0;
        if(i1.hasNext()){
            String w1 = i1.next().toLowerCase();
            if(i2.hasNext()){
                String w2 = i2.next().toLowerCase();
                while (i1.hasNext() && i2.hasNext()){
                    if(w1.equals(w2)){
                        score++;
                        w1=i1.next();
                        w2=i2.next();
                    }else {
                        if(w1.compareTo(w2) > 0){
                            w2=i2.next();
                        }else{
                            w1=i1.next();
                        }
                    }
                }
            }
        }
        return score;
    }

    private Map readDics(File[] topics)
    {
        Map<File, Set<String>> dics = new HashMap<>(topics.length);
        for(File dir:topics){
            try {
                dics.put(dir, readDic(dir));
            }catch (DirNotFound e){
                Log.log("ERROR: Vocabulary not found in "+dir);
            }
        }
        return dics;
    }

    private Set<String> readDic(File dir) throws DirNotFound
    {
        File dic = getDicForDir(dir);
        if(dic.exists()){
            try {
                byte[] dicContent = Files.readAllBytes(dic.toPath());
                Set<String> dicSet = new TreeSet<>();
                dicSet.addAll(Arrays.asList(new String(dicContent).split(SmartSortSettings.delimeter)));
                return dicSet;
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new DirNotFound();
    }

    private File getDicForDir(File dir)
    {
        return new File(dir.getAbsolutePath()+"/"+SmartSortSettings.dicName);
    }
}
