package com.chatBot.openNplCardChatBot.firstChatBot;

import com.chatBot.openNplCardChatBot.reader.FileConvert;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class DsProbability {

     private DocumentCategorizer documentCategorizer;

     public DsProbability(){
          try {
               // read the training data
               FileConvert fileConvert = new FileConvert();
               InputStreamFactory dataIn = new MarkableFileInputStreamFactory(fileConvert.getFile("ds-categorizer.train"));
               ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
               ObjectStream sampleStream = new DocumentSampleStream(lineStream);

               // define the training parameters
               TrainingParameters params = new TrainingParameters();
               params.put(TrainingParameters.ITERATIONS_PARAM, 10+"");
               params.put(TrainingParameters.CUTOFF_PARAM, 0+"");

               // create a model from traning data
               DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
               //System.out.println("\nModel is successfully trained.");

               // save the model to local
               BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream(fileConvert.getPath("en-movie-classifier-maxent.bin")));
               model.serialize(modelOut);
               //System.out.println("\nTrained Model is saved locally at : "+"model"+File.separator+"en-movie-classifier-maxent.bin");

               // test the model file by subjecting it to prediction
               this.documentCategorizer = new DocumentCategorizerME(model);



          }catch(Exception e) {
               System.out.println("An exception in reading the training file. Please check.");
               e.printStackTrace();
          }
     }


     public boolean getDsProbility(String[] docWords){
          // print the probabilities of the categories
          double[] aProbs = this.documentCategorizer.categorize(docWords);
          System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
          for(int i=0;i<this.documentCategorizer.getNumberOfCategories();i++){
               System.out.println(this.documentCategorizer.getCategory(i)+" : "+aProbs[i]);
          }
          System.out.println("---------------------------------");
          if(this.documentCategorizer.getBestCategory(aProbs).equals("ds-")){
               return false;
          }else {
               return true;
          }

     }
}
