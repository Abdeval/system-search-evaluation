package org.tpOne;

import java.util.HashSet;
import java.util.Set;

import static org.tpOne.ActivityOne.getRetrievedDocs;
import static org.tpOne.ActivityOne.getRelevantDocs;

public class ActivityTwo {
    // todo: implement the method of the precision at k
    public static double precisionAtK(String result_file, Set<String> relevantDocs, int k) {
        // todo: read the result file and the assessment file
        // 1- load retrieved documents
        Set<String> retrievedDocs = getRetrievedDocs(result_file, k);

        // 2- compute precision at k
        Set<String> intersection = new HashSet<>(retrievedDocs);

        intersection.retainAll(relevantDocs);
        if (retrievedDocs.isEmpty()) return 0.0;
        double precision = (double) intersection.size() / retrievedDocs.size();
        return precision;
    }

    // todo: implement the method rPrecision
    public static double rPrecision(String result_file, Set<String> relevantDocs) {
        return precisionAtK(result_file, relevantDocs, relevantDocs.size());
    }

    // todo: implement the method average precision (AP)
    public static double averagePrecision(String result_file, Set<String> relevantDocs) {
        double sumPrecision = 0.0;
        int rank = 0;
        Set<String> retrievedDocs = getRetrievedDocs(result_file, -1);
        for (String doc : retrievedDocs) {
            rank++;
            if (relevantDocs.contains(doc)) {
                sumPrecision += (double) precisionAtK(result_file, relevantDocs, rank);
            }
        }

        if (relevantDocs.isEmpty()) return 0.0;
        return sumPrecision / relevantDocs.size();
    }

    // todo: implement the method mean average precision (MAP)
    public static double meanAveragePrecision(String systemOut, String relevantFile, int queryCount) {
        if (queryCount == 0) return 0.0;
        double sumAP = 0.0;
        for (int i = 1; i <= queryCount; i++) {
            String result_file = systemOut + "\\" + "q" + i + ".results";
            Set<String> relevantDocs = getRelevantDocs(relevantFile, i);
            sumAP += averagePrecision(result_file, relevantDocs);
        }
        return sumAP / queryCount;
    }

    // todo: implement the run method to test the above methods
    public static void main(String[] args) {
        System.out.println("ActivityTwo methods are implemented.");
    }
}
