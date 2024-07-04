package com.ai.chatbot;

import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import java.io.*;

public class App {
    private DoccatModel model;

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.trainModel("trainingData.txt");
        app.startChat();
    }

    public void trainModel(String trainingDataFile) throws IOException {
        InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File(trainingDataFile));
        ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
        ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

        TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.ITERATIONS_PARAM, 100);
        params.put(TrainingParameters.CUTOFF_PARAM, 1);

        model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
        System.out.println("Model training complete");
    }

    public void startChat() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DocumentCategorizerME categorizer = new DocumentCategorizerME(model);
        System.out.println("Chatbot is ready to talk! (type 'exit' to quit)");

        while (true) {
            System.out.print("> ");
            String userInput = reader.readLine();
            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("Goodbye!");
                break;
            }

            // Convert the user input to a single-element string array
            double[] outcomes = categorizer.categorize(new String[]{userInput});
            String category = categorizer.getBestCategory(outcomes);

            // Debugging lines
            //System.out.println("Categorized as: " + category);
            //System.out.println("Outcomes:");
            /*for (int i = 0; i < categorizer.getNumberOfCategories(); i++) {
                System.out.println(categorizer.getCategory(i) + ": " + outcomes[i]);
                 
            }*/
            // as we have only 3 categories
            if (outcomes[0] == 0.3333333333333333 && outcomes[1] == 0.3333333333333333 && outcomes[2] == 0.3333333333333333 ){category = "unknown";}

            respond(category);
        }
    }

    public void respond(String category) {
        switch (category) {
            case "greeting":
                System.out.println("Hi there! How can I help you today?");
                break;
            case "goodbye":
                System.out.println("Goodbye! Have a great day!");
                break;
            case "question":
                System.out.println("Interesting question. I will come back to you once I find the answer.");
                break;
            case "unknown":    
                System.out.println("Unfortunately, I'm not trained to respond to that yet!.");
                break;
            default:
                System.out.println("I'm not sure how to respond to that.");
                break;    
        }
    }
}
