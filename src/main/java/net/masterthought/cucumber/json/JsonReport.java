package net.masterthought.cucumber.json;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.json.Json;

import java.io.StringReader;
import java.util.LinkedHashMap;

public class JsonReport {

    private Sequence<Feature> features;

    public JsonReport(String reportContent) {
        this.features = Json.sequence(new StringReader(reportContent)).map(f -> new Feature((LinkedHashMap) f));
    }

    public Sequence<Feature> features(){
      return features;
    }
}
