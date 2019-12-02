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

    public static <T> CsvToBean OpenCSVBuilder(String filename, String classname) {
        int count = 0;
        Iterator<T> csvDataIterator = null;
        CsvToBean<T> csvToBean;
        try {

            Class classTemp = Class.forName(classname);
            Reader reader = Files.newBufferedReader(Paths.get(filename));

            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            //csvDataIterator = csvToBean.iterator();
            return csvToBean;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCountOfRecords(String SAMPLE_CSV_FILE_PATH, String classname) throws CustomException, IOException {
        int count = 0;
        try {

            Iterator<CSVStates> myUserIterator = (Iterator<CSVStates>) OpenCSVBuilder(SAMPLE_CSV_FILE_PATH, classname);
            while (myUserIterator.hasNext()) {
                CSVStates csvStates = myUserIterator.next();
                count++;
            }
        } catch (RuntimeException e) {
            throw new CustomException(CustomException.ExceptionType.INCORRECT_TYPE, "ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e);
        }
        System.out.println(count);
        return count;
    }


}
