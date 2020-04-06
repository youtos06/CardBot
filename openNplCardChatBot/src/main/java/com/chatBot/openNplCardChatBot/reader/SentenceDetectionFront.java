package com.chatBot.openNplCardChatBot.reader;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetectionFront {
    private SentenceDetectorME sentenceDetectorME;

    public SentenceDetectionFront(){
        try {
            FileConvert fileConvert = new FileConvert();
            this.sentenceDetectorME = new SentenceDetectorME(new SentenceModel(fileConvert.getFile("en-sent.bin")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getSentences(String text){
        return this.sentenceDetectorME.sentDetect(text);
    }


}
