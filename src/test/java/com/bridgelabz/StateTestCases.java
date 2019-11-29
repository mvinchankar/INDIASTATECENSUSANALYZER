package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateTestCases {

    @Test
    public void when_Read_CSV_File_Count_Records_Should_Return_True() throws IOException {
        StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
        Assert.assertEquals(37,stateCensusAnalyser.getCountOfRecords());
    }
}
