package com.chatBot.openNplCardChatBot.reader;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class NamedEntityFront {
    private NameFinderME nameFinderME;

    public NamedEntityFront(){
        try {
            FileConvert fileConvert = new FileConvert();
            //this.tokenizerME = new TokenizerME(new TokenizerModel(fileConvert.getFile("en-token.bin")));
            this.nameFinderME = new NameFinderME(new TokenNameFinderModel(fileConvert.getFile("en-ner-location.bin")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLocations(String text,String[] tokens){
        Span nameSpans[] = this.nameFinderME.find(tokens);
        // nameSpans contain all the possible entities detected
        for(Span s: nameSpans){
            System.out.print(s.toString());
            System.out.print("  :  ");
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            for(int index=s.getStart();index<s.getEnd();index++){
                System.out.print(tokens[index]+" ");
            }
            System.out.println();
        }
    }
}
