package com.chatBot.openNplCardChatBot.reader;


import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.lemmatizer.LemmatizerModel;

public class LemmatizerFront {

    private LemmatizerME lemmatizerME;

    public LemmatizerFront() {
        try {
            FileConvert fileConvert = new FileConvert();
            this.lemmatizerME = new LemmatizerME(new LemmatizerModel(fileConvert.getFile("en-lemmatizer.bin")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getWordsIncluded(String[] tokens,String[] tags){
        return this.lemmatizerME.lemmatize(tokens,tags);
    }

}
