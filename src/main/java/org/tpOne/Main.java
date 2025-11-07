package org.tpOne;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import static org.tpOne.ActivityOne.getRelevantDocs;
import static org.tpOne.ActivityOne.getRetrievedDocs;

public class Main {
    public static void main(String[] args) {
        String outputDir = "D:\\Year-2\\S1\\RI\\TPS\\tp-3-assessments\\Evaluation_Results";
        String assessment_file = "D:\\Year-2\\S1\\RI\\TPS\\tp-3-assessments\\assessments.txt";

        // Ensure output directory exists
        new File(outputDir).mkdirs();

        for (int i = 1; i <= 3; i++) {
            System.out.println("Results for system : " + i);

            String systemDir = "D:\\Year-2\\S1\\RI\\TPS\\tp-3-assessments\\Retrieval_Results\\S" + i + "_results";
            String outputFile = outputDir + "\\" + "S" + i + "_results.txt";

            // Clear file before appending
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write("=== System S" + i + " Evaluation ===\n");
                writer.write("-------------------------\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Loop through queries
            for (int j = 1; j <= 3; j++) {
                String result_file = systemDir + "\\" + "q" + j + ".results";
                // System.out.println(result_file);
                Set<String> relevantDocs = getRelevantDocs(assessment_file, j);
                Set<String> retrievedDocs = getRetrievedDocs(result_file, -1);

                double precision = ActivityOne.precision(retrievedDocs, relevantDocs);
                double recall = ActivityOne.recall(retrievedDocs, relevantDocs);
                double fMeasure = ActivityOne.fMeasure(retrievedDocs, relevantDocs);
                double precisionAtK = ActivityTwo.precisionAtK(result_file, relevantDocs, 6);
                double rPrecision = ActivityTwo.rPrecision(result_file, relevantDocs);
                double averagePrecision = ActivityTwo.averagePrecision(result_file, relevantDocs);

                // Append query results
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
                    writer.write("Results for query : q" + j + "\n");
                    writer.write("Precision: " + precision + "\n");
                    writer.write("Recall: " + recall + "\n");
                    writer.write("F-Measure: " + fMeasure + "\n");
                    writer.write("Precision@6: " + precisionAtK + "\n");
                    writer.write("R-Precision: " + rPrecision + "\n");
                    writer.write("Average Precision: " + averagePrecision + "\n");
                    writer.write("-------------------------\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            double meanAveragePrecision = ActivityTwo.meanAveragePrecision(systemDir, assessment_file, 3);

            // Append MAP at the end
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
                writer.write("Mean Average Precision: " + meanAveragePrecision + "\n");
                writer.write("=========================\n\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
