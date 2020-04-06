package com.chatBot.openNplCardChatBot.reader;

import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;

public class LanguageDetectNpl {
    private LanguageDetector languageDetector;

    public LanguageDetectNpl() {
        try {
            FileConvert fileConvert = new FileConvert();
            LanguageDetectorModel languageDetectorModel = new LanguageDetectorModel(fileConvert.getFile("langdetect-183.bin"));
            this.languageDetector = new LanguageDetectorME(languageDetectorModel);
        } catch (Exception e) {

        }
    }

    public void function(String textTest) {
       // String textTest = "feen a sat cv?";

        try {

            Language[] languages = this.languageDetector.predictLanguages(textTest);
            for (Language language : languages) {
                System.out.println(language.getLang() + "  confidence:" + language.getConfidence());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isEnglish(String text) {

        Language[] languages = this.languageDetector.predictLanguages(text);
        if (languages[0].getLang().equals("eng") || languages[1].getLang().equals("eng") || languages[2].getLang().equals("eng")|| languages[3].getLang().equals("eng")) {
            return true;
        }
        return false;

    }
}
