package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;


public class StateCensusAnalyser {
    private static final String SAMPLE_CSV_FILE_PATH="/home/slot1/StateCensusData.csv";
    public static int getCountOfRecords() throws CustomException, IOException {
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
        }catch (NoSuchFileException e) {
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE,"Please Enter Proper File Path or Type",e);
        } catch (RuntimeException e){
            throw new CustomException(CustomException.ExceptionType.INCORRECT_TYPE,"ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER",e);
        }
        System.out.println(count);
        return count;
    }
public static int getCountOfRecordsForStateCensusCsv() throws CustomException {
        int count=0;
        try {

            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(CSVStateCensus.class)
                    .build();


            Iterator<CSVStateCensus> myUserIterator = csvToBean.iterator();
            while (myUserIterator.hasNext()) {
                CSVStateCensus csvStates = myUserIterator.next();
                count++;
            }
        }catch (NoSuchFileException e) {
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE,"Please Enter Proper File Path or Type",e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    System.out.println(count);
        return count;
    }

}
