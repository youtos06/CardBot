package com.chatBot.openNplCardChatBot.reader;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;

import java.util.ArrayList;
import java.util.List;

public class ChunkerFront {
    private ChunkerME chunkerME;

    public ChunkerFront(){
        try {
            FileConvert fileConvert = new FileConvert();
            this.chunkerME = new ChunkerME(new ChunkerModel(fileConvert.getFile("en-chunker.bin")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getChunks(String[] tokens,String[] tags){
        return this.chunkerME.chunk(tokens,tags);
    }

    public String[] getChunksAsSentence(String[] tokens,String[] tags){
        String[] chunks = chunkerME.chunk(tokens,tags);
        List<String> resultSentences = new ArrayList<>();
        String broker = null;
        for (int i = 0; i<chunks.length;i++){
            if (chunks[i].charAt(0) == 'B'){
                if (broker !=null){
                    resultSentences.add(broker);
                }
                broker = tokens[i];
            }else {
                broker = broker + " " + tokens[i];
            }
        }
        String[] sentenceArray = new String[resultSentences.size()];
        return resultSentences.toArray(sentenceArray);
    }
}
