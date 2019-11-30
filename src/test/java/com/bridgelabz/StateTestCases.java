package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateTestCases {

    @Test
    public void when_Read_CSV_File_Count_Records_Should_Return_True() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            Assert.assertEquals(37, stateCensusAnalyser.getCountOfRecords());
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CustomException.ExceptionType.NO_SUCH_FILE, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void when_Incorrect_CSV_File_Should_Return_False() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            Assert.assertEquals(37, stateCensusAnalyser.getCountOfRecords());
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CustomException.ExceptionType.NO_SUCH_FILE, e.type);
        }

    }

    @Test
    public void when_InCorrect_CSV_FileType_Should_Return_False() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            Assert.assertEquals(37, stateCensusAnalyser.getCountOfRecords());
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CustomException.ExceptionType.INCORRECT_TYPE, e.type);
        }

    }

    @Test
    public void when_Correct_CSV_File_But_Delimiter_Incorrect_Should_Return_False() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            Assert.assertEquals(37, stateCensusAnalyser.getCountOfRecords());
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CustomException.ExceptionType.INCORRECT_TYPE, e.type);
        }

    }
    @Test
    public void when_Correct_CSV_File_But_Header_Incorrect_Should_Return_False() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            Assert.assertEquals(37, stateCensusAnalyser.getCountOfRecords());
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CustomException.ExceptionType.INCORRECT_TYPE, e.type);
        }

    }
    //    ******************************************************************************************************************
    @Test
    public void when_Read_State_Census_CSV_File_Count_Records_Should_Return_True() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        Assert.assertEquals(29, stateCensusAnalyser.getCountOfRecordsForStateCensusCsv());
    }


}
