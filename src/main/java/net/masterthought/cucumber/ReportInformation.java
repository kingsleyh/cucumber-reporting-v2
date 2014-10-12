package net.masterthought.cucumber;

import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.JsonReport;

public class ReportInformation {

    private Sequence<Feature> features;


    public ReportInformation(Sequence<Pair<String,JsonReport>> reports) {
       this.features = reports.flatMap(r -> r.getValue().features());
    }

    public Sequence<Feature> features(){
        return this.features;
    }



}
