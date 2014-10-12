package net.masterthought.cucumber;

import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.numbers.Numbers;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.JsonReport;
import net.masterthought.cucumber.json.Scenario;

import static com.googlecode.totallylazy.numbers.Numbers.*;

public class ReportInformation {

    private Sequence<Feature> features;


    public ReportInformation(Sequence<Pair<String,JsonReport>> reports) {
        this.features = reports.flatMap(r -> r.getValue().features());
    }

    public Sequence<Feature> features(){
        return features;
    }


    public Integer totalNumberOfScenarios() {
       return features.map(Feature::scenarios).flatMap(s -> s).filter(Scenario::isScenario).size();
    }
}
