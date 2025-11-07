package org.tpOne;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

// todo: calculate the precision and the recall of the the results of the systems
// D:\Year-2\S1\RI\TPS\tp-3-assessments\Retrieval_Results
public class ActivityOne {

    // todo: implement the recall method
    public static double recall(Set<String> retrievedDocs, Set<String> relevantDocs) {
        // todo: read the result file and the assessment file
        // compute recall
        Set<String> intersection = new HashSet<>(retrievedDocs);
        intersection.retainAll(relevantDocs);
//        System.out.println("Retrieved Docs: " + retrievedDocs);
//        System.out.println("Relevant Docs: " + relevantDocs);
//        System.out.println("Intersection: " + intersection);
        if (relevantDocs.isEmpty()) return 0.0;
        double recall = (double) intersection.size() / relevantDocs.size();
        return recall;
    }


    // todo: implement the precision method
    public static double precision(Set<String> retrievedDocs, Set<String> relevantDocs) {
        // todo: read the result file and the assessment file
        // compute precision
        Set<String> intersection = new HashSet<>(retrievedDocs);
        intersection.retainAll(relevantDocs);
        if (retrievedDocs.isEmpty()) return 0.0;
        double precision = (double) intersection.size() / retrievedDocs.size();
        return precision;
    }

    // todo: implement the method of f-measure
    public static double fMeasure(Set<String> retrievedDocs, Set<String> relevantDocs) {
        double precision = precision(retrievedDocs, relevantDocs);
        double recall = recall(retrievedDocs, relevantDocs);
        if (precision + recall == 0) return 0.0;
        double fMeasure = 2 * (precision * recall) / (precision + recall);
        return fMeasure;
    }

    // implement the run method to test the above methods
    public static void main(String[] args) {
        System.out.println("ActivityOne methods are implemented.");
    }

    public static Set<String> getRelevantDocs(String assessment_file, int queryNumber) {
        Set<String> relevantDocs = new HashSet<>();
        String query = "q" + queryNumber;
//        System.out.println("Reading relevant docs for " + query);

        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(assessment_file))) {
            String line;
            boolean readingDocs = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // When we reach the desired query (e.g., q1 7)
                if (line.startsWith(query)) {
                    readingDocs = true;
                    continue;
                }

                // If we find another query (like q2 10), stop reading
                if (readingDocs && line.startsWith("q")) {
                    break;
                }

                // Collect document names only while inside the right query section
                if (readingDocs) {
                    relevantDocs.add(line);
                    count++;
                }
            }

//            System.out.println("Number of docs in " + query + " = " + count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return relevantDocs;
    }


    public static Set<String> getRetrievedDocs(String result_file, int k) {
        if (k <= 0) {
            Set<String> retrievedDocs = new HashSet<>();
            try (BufferedReader br = new BufferedReader(new FileReader(result_file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        retrievedDocs.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return retrievedDocs;
        } else {
            int i = 0;
            Set<String> retrievedDocs = new HashSet<>();
            try (BufferedReader br = new BufferedReader(new FileReader(result_file))) {
                String line;
                while ((line = br.readLine()) != null && i < k) {
                    i++;
                    line = line.trim();
                    if (!line.isEmpty()) {
                        retrievedDocs.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return retrievedDocs;
        }

    }
}
