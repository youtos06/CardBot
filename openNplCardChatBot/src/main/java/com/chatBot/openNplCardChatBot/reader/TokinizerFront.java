package com.chatBot.openNplCardChatBot.reader;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class TokinizerFront {
    private TokenizerME tokenizerME;

    public TokinizerFront(){
        try {
        FileConvert fileConvert = new FileConvert();
        this.tokenizerME = new TokenizerME(new TokenizerModel(fileConvert.getFile("en-token.bin")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getTokens(String text){
        String[] tokens = this.tokenizerME.tokenize(text);
        return tokens;
    }

    public double[] getTokenProb(String text){
        double[] tokensProbability = this.tokenizerME.getTokenProbabilities();
        return tokensProbability;
    }
}
