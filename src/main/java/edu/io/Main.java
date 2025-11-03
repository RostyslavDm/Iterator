package edu.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1 || args.length > 2){
            System.out.println("Correct usage: itx <mode> [<filename>]");
            return;
        }

        String filename;
        String mode = args[0];
        if(args.length == 2){
            filename = args[1];
        } else filename = null;

        try{
            TextSource textSource = getTextSource(filename);
            switch (mode) {
                case "c":
                    iterateCharacters(textSource);
                    break; 
                case "w":
                    iterateWords(textSource);
                    break;
            
                default:
                    break;
            }
        } catch(IOException e){
            System.err.println(e.getMessage());
        }

    }

    private static TextSource getTextSource(String filename) throws IOException{
        if(filename == null){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line != null){
                sb.append(line).append(System.lineSeparator());
                line = br.readLine();
            }
            return new TextSource(sb.toString());
        } else {
            BufferedReader br = new BufferedReader(new FileReader(filename));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while(line != null){
                    sb.append(line).append(System.lineSeparator());
                    line = br.readLine();
                }

                return new TextSource(sb.toString());
            
        }
    }

    private static void iterateCharacters(TextSource textSource){
        for(String character : textSource){
            System.out.println(character);
        }
    }

    private static void iterateWords(TextSource textSource){
        Iterator<String> wordIterator = textSource.wordIterator();
        while(wordIterator.hasNext()){
            System.out.println(wordIterator.next());
        }
    }


}