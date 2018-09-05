package com.gmail.user0abc.sandbox.performance;

import java.text.CharacterIterator;
import java.util.LinkedList;
import java.util.List;

public class AhoCorasickImpl
{
    Character key;
    List<AhoCorasickImpl> children = new LinkedList<>();

    public void add(String word){
        add(word.toCharArray());
    }

    public void add(char[] word){

    }
    public AhoCorasickImpl()
    {
        key = null;
    }

    private AhoCorasickImpl(Character c)
    {
        key = c;
    }

    private AhoCorasickImpl add(char[] word, int start)
    {
        if(start < word.length){
            char c = word[start];
            for(AhoCorasickImpl node:children){
                if(node.key == c){
                    return node.add(word, start+1);
                }
            }
            AhoCorasickImpl node = new AhoCorasickImpl(c);
            children.add(node);
            return node.add(word, start+1);

//            if(key == null){// I am the root, thus I am trying to add next suffix
//                add(word, start+1);
//            }
        }
        return null;
    }

    public int countSubstringMatches(CharacterIterator input)
    {
        return 0;
    }

}
