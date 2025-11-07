# Information Retrieval System Evaluation

## Project Overview

This project is an **Information Retrieval (IR) System Evaluation Tool** developed for evaluating and comparing the performance of multiple document retrieval systems. It implements standard IR evaluation metrics to assess the quality of search results against known relevant documents.

## Author

**MY NAME - IASD**

## Purpose

The main purpose of this project is to:
- Evaluate the effectiveness of different information retrieval systems
- Calculate standard IR metrics including Precision, Recall, F-Measure, Precision@K, R-Precision, Average Precision (AP), and Mean Average Precision (MAP)
- Compare multiple retrieval systems based on their performance across various queries
- Generate comprehensive evaluation reports for analysis

## Features

### Activity One: Basic IR Metrics
- **Precision**: Measures the fraction of retrieved documents that are relevant
- **Recall**: Measures the fraction of relevant documents that are retrieved
- **F-Measure**: Harmonic mean of Precision and Recall

### Activity Two: Advanced IR Metrics
- **Precision@K**: Precision calculated at the top K retrieved documents
- **R-Precision**: Precision at R documents (where R = number of relevant documents)
- **Average Precision (AP)**: Average of precision values at each relevant document position
- **Mean Average Precision (MAP)**: Mean of average precision across all queries

## Project Structure

```
AUTHOR'S-NAME-IASD-TP3/
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── tpOne/
│                   ├── Main.java           # Main execution entry point
│                   ├── ActivityOne.java    # Basic metrics implementation
│                   └── ActivityTwo.java    # Advanced metrics implementation
├── pom.xml                                 # Maven configuration
└── README.md                               # This file
```

## Prerequisites

- **Java 21** or higher
- **Maven** (for dependency management and building)
- Input files:
  - Assessment file containing relevant documents for each query
  - Retrieval results from systems to be evaluated

## Installation

1. Clone or download this repository
2. Navigate to the project directory:
   ```cmd
   cd D:\Year-2\S1\RI\TPS\REZIGA-Abdelatif-IASD-TP3
   ```

3. Build the project using Maven:
   ```cmd
   mvn clean compile
   ```

## Usage

### Input File Format

#### Assessment File (`assessments.txt`)
The assessment file should contain relevant documents for each query:
```
q1 7
doc1.txt
doc2.txt
doc3.txt
...

q2 10
doc5.txt
doc8.txt
...
```

#### Retrieval Results Files
Each system's results should be organized in separate directories with query result files:
```
Retrieval_Results/
├── S1_results/
│   ├── q1.results
│   ├── q2.results
│   └── q3.results
├── S2_results/
│   └── ...
└── S3_results/
    └── ...
```

Each `.results` file contains retrieved documents (one per line) in ranked order.

### Running the Evaluation

1. **Update file paths** in `Main.java` to match your input/output directories:
   ```java
   String outputDir = "path/to/output";
   String assessment_file = "path/to/assessments.txt";
   ```

2. **Compile and run** the project:
   ```cmd
   mvn clean compile
   mvn exec:java -Dexec.mainClass="org.tpOne.Main"
   ```

   Or run directly from your IDE (IntelliJ IDEA, Eclipse, etc.)

### Output

The program generates evaluation result files in the specified output directory:
```
Evaluation_Results/
├── S1_results.txt
├── S2_results.txt
└── S3_results.txt
```

Each result file contains:
- Per-query metrics (Precision, Recall, F-Measure, Precision@6, R-Precision, Average Precision)
- Overall Mean Average Precision (MAP) for the system

### Example Output Format

```
=== System S1 Evaluation ===
-------------------------
Results for query : q1
Precision: 0.75
Recall: 0.857
F-Measure: 0.8
Precision@6: 0.833
R-Precision: 0.857
Average Precision: 0.891
-------------------------
Results for query : q2
...
-------------------------
Mean Average Precision: 0.845
=========================
```

## Metrics Explanation

- **Precision**: `|Retrieved ∩ Relevant| / |Retrieved|`
- **Recall**: `|Retrieved ∩ Relevant| / |Relevant|`
- **F-Measure**: `2 × (Precision × Recall) / (Precision + Recall)`
- **Precision@K**: Precision calculated using only the top K retrieved documents
- **R-Precision**: Precision@R where R is the total number of relevant documents
- **Average Precision**: `Σ(Precision@k × rel(k)) / |Relevant|` where rel(k) = 1 if document at rank k is relevant
- **MAP**: Average of AP values across all queries

## Customization

To evaluate different systems or queries:
1. Modify the loop ranges in `Main.java` (currently set for 3 systems and 3 queries)
2. Update file paths to point to your data
3. Adjust the K value in Precision@K calculation (currently set to 6)

## Technologies Used

- **Language**: Java 21
- **Build Tool**: Maven
- **Dependencies**: Standard Java libraries (java.io, java.util)

## License

This project is developed for academic purposes as part of the Information Retrieval course.

## Contact

For questions or issues, please contact: **REZIGA Abdelatif**

