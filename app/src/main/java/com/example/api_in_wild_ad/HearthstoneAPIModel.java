package com.example.api_in_wild_ad;

import com.google.gson.annotations.SerializedName;

public class HearthstoneAPIModel {

    String apiPatch;
    //The below are technically arrays.. that have ints inside of them.
    //May need to make a secondary list of ints for the things...
    //Unsure at the moment
    @SerializedName("classes")
    String ClassName;
    @SerializedName("sets")
    String SetName;
    /**@SerializedName("types")
    String Types;
    @SerializedName("factions")
    String Factions;
    @SerializedName("qualities")
    String Qualities;*/
    @SerializedName("races")
    String Races;
    //String Locales;




    /**public HearthstoneAPIModel(final String ClassName){
        setClassName(ClassName);
    }*/
    public HearthstoneAPIModel(final String ClassName, final String SetName, final String Races){
        setClassName(ClassName);
        setSetName(SetName);
        setRaces(Races);
        //setLocales(Locales);
    }

    public String getClassName(){return ClassName;}
    public String getSetName(){return SetName;}
   /** public String getTypes(){return Types;}
    public String getFactions(){return Factions;}
    public String getQualities(){return Qualities;}*/
    public String getRaces(){return Races;}
    //public String getLabels(){return Labels;}
    //public String getLocales(){return Locales;}

    public void setClassName(final String ClassName){this.ClassName = ClassName;}
    public void setSetName(final String SetName){this.SetName = SetName;}
    /**public void setTypes(final String Types){this.Types = Types;}
   public void setFactions(final String Factions){this.Factions = Factions;}
    public void setQualities(final String Qualities){this.Qualities = Qualities;}*/
    public void setRaces(final String Races){this.Races = Races;}
    //public void setLabels(final String Labels){this.Labels = Labels;}
    //public void setLocales(final String Locales){this.Locales = Locales;}
}
