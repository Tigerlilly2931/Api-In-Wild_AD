package com.example.api_in_wild_ad;

public class HearthstoneAPILabelModal {

    String Labels;

    public HearthstoneAPILabelModal(String Labels){
        setLabels(Labels);
    }

    public String getLabels(){return Labels;}
    public void setLabels(final String Labels){this.Labels = Labels;}
}
