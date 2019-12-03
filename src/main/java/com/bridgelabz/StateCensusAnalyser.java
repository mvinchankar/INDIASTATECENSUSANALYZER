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
    private static String CsvFilePath = "/home/slot1/IdeaProjects/Indian State Census Analyzer/src/main/resources/BindCsv.csv";

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
        Map<String, CSVStateCensus> sortedContainer = new HashMap<>();
        List<CSVStateCensus> container = new ArrayList<>();
        try {
            CsvToBean<CSVStateCensus> csvToBean1 = OpenCSVBuilder(SAMPLE_CSV_FILE_PATH1, classname1);
            Iterator<CSVStateCensus> myUserIterator = csvToBean1.iterator();
            while (myUserIterator.hasNext()) {
                CSVStateCensus csvStateCensus = myUserIterator.next();
                container.add(csvStateCensus);
                sortedContainer.put(csvStateCensus.getState(), csvStateCensus);
                count++;
            }
            Map<String, CSVStateCensus> sortedMap = sortedbyKeyValue((HashMap<String, CSVStateCensus>) sortedContainer);
            for (Map.Entry<String, CSVStateCensus> entry : sortedMap.entrySet())
                System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
            Boolean status = StateCensusAnalyser.writeToGson(sortedMap);
            if (expected == count)
                return "HAPPY";
            else
                return "SAD";
        } catch (RuntimeException e) {
            throw new CustomException(CustomException.ExceptionType.INCORRECT_TYPE, "ERROR IN FILE TYPE OR IN FILE DELIMI" +
                    "TER OR IN FILE HEADER", e);
        }
    }

    private static Boolean writeToGson(Map<String, CSVStateCensus> sortedMap) throws IOException {
        String SAMPLE_JSON_FILE_PATH = "/home/slot1/IdeaProjects/Indian State Census Analyzer/src/main/resources/Output.json";
        Gson gson = new Gson();
        String json = gson.toJson(sortedMap);
        FileWriter writer = new FileWriter(SAMPLE_JSON_FILE_PATH);
        writer.write(json);
        writer.close();
        return true;
    }

    private static Map<String, CSVStateCensus> sortedbyKeyValue(HashMap<String, CSVStateCensus> sortedContainer) throws IOException {
        List<Map.Entry<String, CSVStateCensus>> list =
                new LinkedList<Map.Entry<String, CSVStateCensus>>(sortedContainer.entrySet());

// Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, CSVStateCensus>>() {
            public int compare(Map.Entry<String, CSVStateCensus> o1,
                               Map.Entry<String, CSVStateCensus> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });

// put data from sorted list to hashmap
        HashMap<String, CSVStateCensus> temp = new LinkedHashMap<String, CSVStateCensus>();
        for (Map.Entry<String, CSVStateCensus> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
