package net.masterthought.cucumber.json;

import java.util.LinkedHashMap;

import static com.googlecode.totallylazy.Option.option;

public class Feature {

    LinkedHashMap feature;

    public Feature(LinkedHashMap feature){
        this.feature = option(feature).getOrElse(new LinkedHashMap());
    }

    public String name(){
        return option(feature.get("name").toString()).getOrElse("");
    }

}
