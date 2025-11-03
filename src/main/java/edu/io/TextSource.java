package edu.io;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSource implements Iterable<String> {
    private final String text;

    public TextSource(String text){
        this.text = text;
    }

    @Override
    public Iterator<String> iterator(){
        return new CharIterator();
    }

    public Iterator<String> wordIterator(){
        return new WordIterator();
    }

    public Iterator<String> sentenceIterator(){
        return new SentenceIterator();
    }

    public Iterator<String> numberIterator(){
        return new NumberIterator();
    }

    public Iterator<String> regexIterator(){
        return new RegexIterator("\\w+");
    }

    public Iterator<String> regexIterator(String regex){
        return new RegexIterator(regex);
    }



    private class CharIterator implements Iterator<String>{
        private int index = 0;

        @Override
        public boolean hasNext(){
            return index < text.length();
        }

        @Override
        public String next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return String.valueOf(text.charAt(index++));
        }
    }

    private class WordIterator implements Iterator<String>{
        private final String[] words;
        private int index = 0;

        public WordIterator(){
            if(text.trim().isEmpty()){
                words = new String[0];
            } else
            words = text.trim().split("\\s+");
        }

        @Override
        public boolean hasNext(){
            return index < words.length;
        }

        @Override
        public String next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return words[index++];
        }
    }

    private class SentenceIterator implements Iterator<String>{
        private final String[] sentences;
        private int index = 0;

        public SentenceIterator(){
            if (text.trim().isEmpty()){
                sentences = new String[0];
            } else
            sentences = text.split("(?<=[.!?])\\s+");
        }

        @Override
        public boolean hasNext(){
            return index < sentences.length;
        }

        @Override
        public String next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return sentences[index++];
        }
    }

    private class NumberIterator implements Iterator<String>{
        private final Matcher matcher;

        public NumberIterator(){
            Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
            matcher = pattern.matcher(text);
        }

        @Override
        public boolean hasNext(){
            return matcher.find();
        }

        @Override
        public String next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return matcher.group();
        }
    }

    public class RegexIterator implements Iterator<String>{
        private final Matcher matcher;

        public RegexIterator(String regex){
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(text);
        }

        @Override
        public boolean hasNext(){
            return matcher.find();
        }

        @Override
        public String next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return matcher.group();
        }
    }
}


