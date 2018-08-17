package com.gmail.user0abc.puzzles;

import org.junit.Test;

import java.util.Random;

public class CountSubstrings
{
    @Test
    public void testCountLongestSubstrings()
    {
        String in = "abcdeiejduhryfdljhabcdeoefiojefimklbmgknjh";
        String longest = findLongestRepeatingSubstring(in);
        System.out.println("Longest = "+longest);
    }

    @Test
    public void testCountLongestSubstringsLongString1000()
    {
        String in = generateLongString(1000, "abcde");
        String longest = findLongestRepeatingSubstring(in);
        System.out.println("Longest = "+longest);
    }

    @Test
    public void testCountLongestSubstringsLongString2000()
    {
        String in = generateLongString(2000, "abcde");
        String longest = findLongestRepeatingSubstring(in);
        System.out.println("Longest = "+longest);
    }

    @Test
    public void testCountLongestSubstringsLongString4000()
    {
        String in = generateLongString(4000, "abcde");
        String longest = findLongestRepeatingSubstring(in);
        System.out.println("Longest = "+longest);
    }

    private String generateLongString(int l, String abcde)
    {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < l;){
            int c = r.nextInt(128);
            if(c < 32){
                sb.append(abcde);
                i = i + abcde.length();
            }else {
                sb.append((char)c);
                i++;
            }
        }
        return sb.toString();
    }

    private String findLongestRepeatingSubstring(String in)
    {
        String result = "";
        for (int i = 0; i < in.length(); i++) {
            for (int j = i+2; j < in.length(); j++) {
                String sub = in.substring(i, j);
                if (in.substring(j).contains(sub) && sub.length() > result.length()) {
                    result = sub;
                }
            }
        }
        return result;
    }
}
