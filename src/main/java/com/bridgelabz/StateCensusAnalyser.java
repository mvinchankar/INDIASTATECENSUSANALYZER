package com.bridgelabz;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;


public class StateCensusAnalyser {
    private static final String SAMPLE_CSV_FILE_PATH="/home/slot1/StateCode.csv";
    public static int getCountOfRecords() throws IOException {
        int count=0;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder(reader)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(CSVStates.class)
                    .build();


            Iterator<CSVStates> myUserIterator = csvToBean.iterator();

            while (myUserIterator.hasNext()) {
                CSVStates csvStates = myUserIterator.next();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

}
