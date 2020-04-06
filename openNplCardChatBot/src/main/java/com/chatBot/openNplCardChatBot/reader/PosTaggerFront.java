package com.chatBot.openNplCardChatBot.reader;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PosTaggerFront {
    //private TokenizerME tokenizerME;
    private POSTaggerME posTaggerME;

    public PosTaggerFront(){
        try {
            FileConvert fileConvert = new FileConvert();
            //this.tokenizerME = new TokenizerME(new TokenizerModel(fileConvert.getFile("en-token.bin")));
            this.posTaggerME = new POSTaggerME(new POSModel(fileConvert.getFile("en-pos-maxent.bin")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getTokensAndTags(String text,String[] tokens){
        //String[] tokens = tokenizerME.tokenize(text);
        String[] tags   = this.posTaggerME.tag(tokens);

        HashMap<String,String> tokensAndTags = new LinkedHashMap<>();

        for (int i =0 ; i< tokens.length ;i++){
            tokensAndTags.put(tokens[i],tags[i]);
        }
        return tokensAndTags;
    }

    public String getTag(String word){
        String[] wordInArray = new String[1] ;
        wordInArray[0] = word;
        return this.posTaggerME.tag(wordInArray)[0];
    }

    public String[] getTags(String[] tokens){
        return this.posTaggerME.tag(tokens);
    }
}
