package com.example.api_in_wild_ad;

import com.google.gson.annotations.SerializedName;

public class HearthstoneAPIModel {

    String apiPatch;
    //The below are technically arrays.. that have ints inside of them.
    //May need to make a secondary list of ints for the things...
    //Unsure at the moment
    String ClassName;
    String SetName;
    String Types;
    String Factions;
    String Qualities;
    String Races;
    String Locales;


    public HearthstoneAPIModel(final String ClassName, final String SetName, final String Types, final String Factions, final String Qualities, final String Races, final String Locales){
        setClassName(ClassName);
        setSetName(SetName);
        setTypes(Types);
        setFactions(Factions);
        setQualities(Qualities);
        setRaces(Races);
        setLocales(Locales);
    }

    public String getClassName(){return ClassName;}
    public String getSetName(){return SetName;}
    public String getTypes(){return Types;}
    public String getFactions(){return Factions;}
    public String getQualities(){return Qualities;}
    public String getRaces(){return Races;}
    public String getLocales(){return Locales;}

    public void setClassName(final String ClassName){this.ClassName = ClassName;}
    public void setSetName(final String SetName){this.SetName = SetName;}
    public void setTypes(final String Types){this.Types = Types;}
    public void setFactions(final String Factions){this.Factions = Factions;}
    public void setQualities(final String Qualities){this.Qualities = Qualities;}
    public void setRaces(final String Races){this.Races = Races;}
    public void setLocales(final String Locales){this.Locales = Locales;}
}
