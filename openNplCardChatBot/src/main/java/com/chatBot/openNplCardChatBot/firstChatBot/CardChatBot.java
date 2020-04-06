package com.chatBot.openNplCardChatBot.firstChatBot;


import com.chatBot.openNplCardChatBot.beans.CardBean;
import com.chatBot.openNplCardChatBot.dataElement.ContinentList;
import com.chatBot.openNplCardChatBot.dataElement.CountriesList;
import com.chatBot.openNplCardChatBot.dataElement.basic.Data;
import com.chatBot.openNplCardChatBot.proxy.MicroServiceCardProxy;
import com.chatBot.openNplCardChatBot.reader.*;
import opennlp.tools.doccat.*;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.*;
import opennlp.tools.util.model.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class CardChatBot {


    private static FileConvert fileConvert = new FileConvert();

    private static LanguageDetectNpl languageDetectNpl = new LanguageDetectNpl();

    private static TokinizerFront tokinizerFront = new TokinizerFront();

    private static SentenceDetectionFront sentenceDetectionFront = new SentenceDetectionFront();

    private static PosTaggerFront posTaggerFront = new PosTaggerFront();

    private static ChunkerFront chunkerFront = new ChunkerFront();

    private static LemmatizerFront lemmatizerFront = new LemmatizerFront();

    private static ContinentList continentList = new ContinentList();

    private static CountriesList countriesList = new CountriesList();

    private static DsProbability dsProbability = new DsProbability();

    private  DoccatModel model ;

    @Autowired
    private MicroServiceCardProxy microServiceCardProxy;

    public CardChatBot() {
        try {
            this.model = trainCategorizerModel();
        }catch (Exception e){
            e.getMessage();
        }

    }/*
    public CardChatBot(MicroServiceCardProxy microServiceCardProxy){
        try {
            this.model = trainCategorizerModel();
            this.microServiceCardProxy = microServiceCardProxy;
        }catch (Exception e){
            e.getMessage();
        }
    }*/

    public Map<String,String[]> getDataFromUserInput(String sentence){
        Map<String,String[]> data = new HashMap<>();
        String[] tokens = tokinizerFront.getTokens(sentence);
        String[] tags = posTaggerFront.getTags(tokens);
        String[] lemmas = lemmatizerFront.getWordsIncluded(tokens, tags);
        data.put("tokens",tokens);
        data.put("tags",tags);
        data.put("lemmas",lemmas);
        return data;
    }

    public String getCategoryOfUserInput(String userInput){
        if (languageDetectNpl.isEnglish(userInput)) {
            try {
                System.out.println("-------------------------------------------");
                System.out.println("recieved text : " + userInput);
                DoccatModel model = this.model;
                Map<String, String[]> data = this.getDataFromUserInput(userInput);
                String[] tokens = data.get("tokens");
                String[] tags = data.get("tags");
                String[] lemmas = data.get("lemmas");
                String category = detectCategory(model, lemmas);
                return category;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            return "Not enough data to say it s English ! probability of english is so small!";
        }
        return "no data";
    }


    public Data getDataInsideInput(String userInput){


        String continent = continentList.getContinent(userInput);
        System.out.println("Continent : " + continent);

        String  country = countriesList.getCountries(userInput);
        System.out.println("Country  :" + country);

        String type = getCardType(userInput);
        System.out.println("Card Type :" + type);

        boolean ds = dsProbability.getDsProbility(userInput.replaceAll("[^A-Za-z]", " ").split(" "));
        System.out.println("3Ds enabled :" + ds);

        return new Data(continent,country,type,ds);
    }


    public String getResponseForInput(String userInput) throws IOException {
        // Train categorizer model to the training data we created.
        DoccatModel model = this.model;
        System.out.println("-------------------------------------------");
        System.out.println("recieved text : " + userInput);
        userInput = userInput.toLowerCase();
        if (languageDetectNpl.isEnglish(userInput)) {
            String[] sentences = breakSentences(userInput);
            for (String sentence : sentences) {

                String[] tokens = tokinizerFront.getTokens(sentence);
                String[] tags = posTaggerFront.getTags(tokens);
                String[] lemmas = lemmatizerFront.getWordsIncluded(tokens, tags);
                String category = detectCategory(model, lemmas);
                System.out.println("Category: " + category);
                if (category.equals("greeting")) {
                    return "hey! this is a  bot about credit cards! my understanding is basic so please be specific";
                } else if (category.equals("getCard")) {
                    String response = "";
                    String continent = continentList.getContinent(userInput);
                    System.out.println("Continent : " + continent);
                    response = response+"Continent : " + continent;
                    String  country = countriesList.getCountries(userInput);
                    System.out.println("Country : " + country);
                    response = response+"\nCountry : " + country;
                    String type = getCardType(userInput);
                    System.out.println("Card Type :" + type);
                    response = response+"\nCard Type :" + type;
                    boolean ds = true;
                    if (dsProbability.getDsProbility(userInput.replaceAll("[^A-Za-z]", " ").split(" "))) {
                        System.out.println("3Ds enabled :" + true);
                        response = response+"\n3Ds enabled :" + true;
                    } else {
                        System.out.println("3Ds enabled :" + false);
                        ds = false;
                        response = response+"\n3Ds enabled :" + false;
                    }
                    System.out.println(response);
                    //List<CardBean> cards = microServiceCardProxy.getCardsAccordingToData(continent,country,type,ds);
                    List<CardBean> cards = microServiceCardProxy.getAllCards();
                    System.out.println("cards : "+cards.size());
                    if(cards.size()>0){
                        response = response + " CARDS : \n";
                        for(CardBean cardBean : cards){
                            response = response + cardBean.toString();
                        }
                        return response;
                    }else{
                        return response+" sadly database has no card with requested parameters";
                    }

                    //return "as you can see its a get card";

                } else if (category.equals("conversation-complete")) {
                    return "goodbye";

                } else {
                    return "no data";
                }
            }
        } else {
            return "Not enough data to say it s English ! probability of english is so small!";

        }
        return "no data";


    }


    private static String detectCategory(DoccatModel model, String[] finalTokens) throws IOException {
        // Initialize document categorizer tool
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);

        // Get best possible category.
        double[] probabilitiesOfOutcomes = myCategorizer.categorize(finalTokens);
        String category = myCategorizer.getBestCategory(probabilitiesOfOutcomes);

        for (int i = 0; i < myCategorizer.getNumberOfCategories(); i++) {
            System.out.println("Category : " + myCategorizer.getCategory(i) + "  |  probability : " + probabilitiesOfOutcomes[i]);
        }

        return category;
    }

    private static DoccatModel trainCategorizerModel() throws FileNotFoundException, IOException {
        // faq-categorizer.txt is a custom training data with categories as per our chat
        // requirements.
        InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(fileConvert.getFile("card-categorizer.txt"));
        ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
        ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

        DoccatFactory factory = new DoccatFactory(new FeatureGenerator[]{new BagOfWordsFeatureGenerator()});

        TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
        params.put(TrainingParameters.CUTOFF_PARAM, 0);

        // Train a model with classifications from above file.
        DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, factory);
        return model;
    }

    private static String[] breakSentences(String data) throws FileNotFoundException, IOException {
        // Better to read file once at start of program & store model in instance
        // variable. but keeping here for simplicity in understanding.
        try (InputStream modelIn = new FileInputStream(fileConvert.getPath("en-sent.bin"))) {

            SentenceDetectorME myCategorizer = new SentenceDetectorME(new SentenceModel(modelIn));

            String[] sentences = myCategorizer.sentDetect(data);
            System.out.println("Sentence Detection: " + Arrays.stream(sentences).collect(Collectors.joining(" | ")));

            return sentences;
        }
    }

    private static String getCardType(String text) {

        text = text.toLowerCase();
        if (text.contains("visa")) {
            return "visa";
        }
        if (text.contains("master card") || text.contains("mastercard")) {
            return "master card";
        }
        if (text.contains("american express") || text.contains("americanexpress")) {
            return "american express";
        }
        return "all";
    }
}
