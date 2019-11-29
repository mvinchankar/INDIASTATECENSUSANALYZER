package com.bridgelabz;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StateCensusAnalyser {
    private static final String SAMPLE_CSV_FILE_PATH="/home/slot1/StateCode.csv";
    public static void main(String[] args) throws IOException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVReader csvReader = new CSVReader(reader);

            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                System.out.println("Sr No. : " + record[0]);
                System.out.println("State Name : " + record[1]);
                System.out.println("State Code : " + record[2]);
                System.out.println("TIN : " + record[3]);
                System.out.println("---------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
