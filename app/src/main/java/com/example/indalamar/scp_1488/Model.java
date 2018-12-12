package com.example.indalamar.scp_1488;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Model {

    @SerializedName("response")
    @Expose
    private GlobalItem globalItem;

    public GlobalItem getGlobalItem() {
        return globalItem;
    }

    public void setResponse(GlobalItem globalItem) {
        this.globalItem = globalItem;
    }

}