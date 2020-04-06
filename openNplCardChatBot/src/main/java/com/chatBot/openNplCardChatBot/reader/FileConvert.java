package com.chatBot.openNplCardChatBot.reader;

import opennlp.tools.langdetect.LanguageDetectorSampleStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileConvert {
    public InputStream fileConvertToInputStream(String name) throws IOException {
        LanguageDetectorSampleStream sampleStream = null;
        InputStream in;
        File file = new File(getClass().getClassLoader().getResource(name).getFile());


        in = new FileInputStream(file);
        //System.out.println(in.toString());
        return in;
    }

    public File getFile(String name){
        return new File(getClass().getClassLoader().getResource(name).getFile());
    }

    public String getPath(String name){
        //System.out.println(getClass().getClassLoader().getResource(name).getFile());
        return getClass().getClassLoader().getResource(name).getFile();
    }
}
