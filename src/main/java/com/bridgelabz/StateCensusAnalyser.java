package com.bridgelabz;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class StateCensusAnalyser {

    public static <T> CsvToBean OpenCSVBuilder(String filename, String classname) {
        CsvToBean<T> csvToBean;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCountOfRecords(int expected, String SAMPLE_CSV_FILE_PATH1, String classname1) throws CustomException, IOException {
        int count = 0;
        ArrayList<CSVStateCensus> container = new ArrayList<>();
        try {
            CsvToBean<CSVStateCensus> csvToBean1 = OpenCSVBuilder(SAMPLE_CSV_FILE_PATH1, classname1);
            Iterator<CSVStateCensus> myUserIterator = csvToBean1.iterator();
            while (myUserIterator.hasNext()) {
                CSVStateCensus csvStateCensus = myUserIterator.next();
                container.add(csvStateCensus);
                count++;
            }
            sortThisListBasedOnStatePopulation(container);
            Boolean bool = StateCensusAnalyser.writeToGson(container);
            if (expected == count)
                return "HAPPY";
            else
                return "SAD";
        } catch (RuntimeException e) {
            throw new CustomException(CustomException.ExceptionType.INCORRECT_TYPE, "ERROR IN FILE TYPE OR IN FILE DELIMI" +
                    "TER OR IN FILE HEADER", e);
        }
    }

    private static Boolean writeToGson(List container) throws IOException {
        String SAMPLE_JSON_FILE_PATH = "/home/slot1/IdeaProjects/Indian State Census Analyzer/src/main/resources/Output.json";
        Gson gson = new Gson();
        String json = gson.toJson(container);
        FileWriter writer = new FileWriter(SAMPLE_JSON_FILE_PATH);
        writer.write(json);
        writer.close();
        return true;
    }

    private static void toSort(ArrayList<CSVStateCensus> list) {
        Collections.sort(list);
    }

    private static void sortThisListBasedOnStateName(List<CSVStateCensus> censusList) {
        Comparator<CSVStateCensus> c = (s1, s2) -> s1.getState().compareTo(s2.getState());
        censusList.sort(c);
    }
    private static void sortThisListBasedOnStatePopulation(List<CSVStateCensus> censusList) {
        Comparator<CSVStateCensus> c = (s1, s2) -> Integer.parseInt(s2.getPopulation())- Integer.parseInt(s1.getPopulation());
        censusList.sort(c);
    }

}
