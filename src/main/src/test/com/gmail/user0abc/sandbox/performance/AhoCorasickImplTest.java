package com.gmail.user0abc.sandbox.performance;

import org.junit.Test;

import java.text.StringCharacterIterator;

import static org.junit.Assert.assertEquals;

public class AhoCorasickImplTest
{
    @Test
    public void addTest(){
        AhoCorasickImpl ahoCorasick = new AhoCorasickImpl(null);
        ahoCorasick.add(new StringCharacterIterator("cookoo"));
        assertEquals(1, ahoCorasick.countSubstringMatches(new StringCharacterIterator("oko")));
    }
}