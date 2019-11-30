package com.bridgelabz;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
    @CsvBindByName(column = "Sr.No")
    private String stateName;
    @CsvBindByName(column = "StateName")
    private String srNo;
    @CsvBindByName(column = "StateCode")
    private String stateCode;
    @CsvBindByName(column = "TIN")
    private String stateTIN;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateTIN() {
        return stateTIN;
    }

    public void setStateTIN(String stateTIN) {
        this.stateTIN = stateTIN;
    }
}
