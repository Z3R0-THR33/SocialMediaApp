package com.app.z;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONConvertor{
    @JsonProperty("Error")
    private String Error;
    public JSONConvertor(String s){
        this.Error= s;
    }
}