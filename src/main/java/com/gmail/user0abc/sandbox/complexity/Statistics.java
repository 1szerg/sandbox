package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sergii Ivanov
 */
public class Statistics {
    List<Record> log = new ArrayList<>();

    void addRecord(Sorter sorter, DataGenerator dataGenerator, int n, long time){
        log.add(new Record(dataGenerator, n, sorter, time));
    }

    public String statsBySorter() {
        StringBuilder sb = new StringBuilder();
        Map<Class, List<Record>> pivot = new HashMap<>();
        for(Record r: log){
            if(!pivot.containsKey(r.sorter.getClass())){
                pivot.put(r.sorter.getClass(), new ArrayList<>());
            }
            pivot.get(r.sorter.getClass()).add(r);
        }
        for(Class k: pivot.keySet()){
            sb.append(k.getSimpleName()).append("\t");
            int totalTime = 0;
            for(Record r: pivot.get(k)){
                totalTime += r.time;
            }
            sb.append(totalTime).append("\t").append(totalTime / pivot.get(k).size()).append("\n");
        }
        return sb.toString();
    }

    private static class Record{
        Sorter sorter;
        DataGenerator dataGenerator;
        int n;
        long time;

        public Record(DataGenerator dataGenerator, int n, Sorter sorter, long time) {
            this.dataGenerator = dataGenerator;
            this.n = n;
            this.sorter = sorter;
            this.time = time;
        }
    }}
