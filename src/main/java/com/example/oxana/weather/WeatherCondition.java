package com.example.oxana.weather;

/**
 * Created by Oxana on 17/04/2015.
 */
public class WeatherCondition {
    String mainCondition;
    String description;
    String icon;

    public WeatherCondition (String mCon,String desc,String ico){
        mainCondition = mCon;
        description =desc;
        icon = ico;

    }

    public String getMainCondition(){
        return mainCondition;
    }

    public String getDescription(){
        return description;
    }

    public String getIcon(){
        return icon;
    }

    public String toString(){
        return mainCondition + ": " + description;
    }

}
