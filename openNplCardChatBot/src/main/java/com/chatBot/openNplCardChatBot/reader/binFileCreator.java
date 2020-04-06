package com.chatBot.openNplCardChatBot.reader;

import opennlp.tools.langdetect.*;
import opennlp.tools.ml.perceptron.PerceptronTrainer;
import opennlp.tools.util.*;
import opennlp.tools.util.model.ModelUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class binFileCreator {

    public void bincreator() throws IOException {
        FileConvert fileConvert = new FileConvert();
        InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(fileConvert.getFile(("training-lang.txt")));


        ObjectStream<String> lineStream =
                new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
        ObjectStream<LanguageSample> sampleStream = new LanguageDetectorSampleStream(lineStream);

        TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
        params.put(TrainingParameters.ALGORITHM_PARAM,
                PerceptronTrainer.PERCEPTRON_VALUE);
        params.put(TrainingParameters.CUTOFF_PARAM, 0);

        LanguageDetectorFactory factory = new LanguageDetectorFactory();

        LanguageDetectorModel model = LanguageDetectorME.train(sampleStream, params, factory);
        model.serialize(new File("langdetect.bin"));
    }
}
