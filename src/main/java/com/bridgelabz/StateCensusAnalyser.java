package com.bridgelabz;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class StateCensusAnalyser {

    public static <T> CsvToBean openCSVBuilder(String filename, String classname) {
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

    public static String getCountOfRecords(int expected, String SAMPLE_CSV_FILE_PATH1, String classname1) throws StateCensusException, IOException {
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
            bubbleSort(container);
            Boolean bool = StateCensusAnalyser.writeToGson(container);
            if (expected == count)
                return "HAPPY";
            else
                return "SAD";
        } catch (RuntimeException e) {
            throw new StateCensusException(StateCensusException.ExceptionType.INCORRECT_TYPE, "ERROR IN FILE TYPE OR IN FILE DELIMI" +
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
        Comparator<CSVStateCensus> c = (s1, s2) -> Integer.parseInt(s2.getPopulation()) - Integer.parseInt(s1.getPopulation());
        censusList.sort(c);
    }

    private static void sortThisListBasedOnStatePopulationPPerDensity(List<CSVStateCensus> censusList) {
        Comparator<CSVStateCensus> c = (s1, s2) -> Integer.parseInt(s2.getDensityPerSqKm()) - Integer.parseInt(s1.getDensityPerSqKm());
        censusList.sort(c);
    }

    private static void sortThisListBasedOnStateAreaPerSqKms(List<CSVStateCensus> censusList) {
        Comparator<CSVStateCensus> c = (s1, s2) -> Integer.parseInt(s2.getAreaInSqMs()) - Integer.parseInt(s1.getAreaInSqMs());
        censusList.sort(c);
    }

    private static void bubbleSort(List<CSVStateCensus> csvStateCensuses) {
        if (csvStateCensuses.size() > 1) {
            for (int i = 0; i < csvStateCensuses.size(); i++)
                for (int j = 1; j < csvStateCensuses.size() - i; j++)
                    if ((csvStateCensuses.get(j - 1).getDensityPerSqKm().toString()).compareTo(csvStateCensuses.get(j).getDensityPerSqKm().toString()) > 0) {
                        // swap temp and arr[i]
                        CSVStateCensus temp = csvStateCensuses.get(j - 1);
                        csvStateCensuses.set(j - 1, csvStateCensuses.get(j));
                        csvStateCensuses.set(j, temp);
                    }
        }
    }
}
