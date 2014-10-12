package net.masterthought.cucumber;

import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.numbers.Numbers;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.JsonReport;
import net.masterthought.cucumber.json.Scenario;
import net.masterthought.cucumber.json.Step;

import static com.googlecode.totallylazy.Predicates.is;

public class ReportInformation {

    private Sequence<Feature> features;


    public ReportInformation(Sequence<Pair<String, JsonReport>> reports) {
        this.features = reports.flatMap(r -> r.getValue().features());
    }

    public Sequence<Feature> features() {
        return features;
    }

    public Integer totalNumberOfScenarios() {
        return features.map(Feature::scenarios).flatMap(s -> s.map(Scenario::isScenario)).filter(is(true)).size();
    }

    public Integer totalNumberOfFeatures() {
        return features.size();
    }

    public Integer totalNumberOfSteps() {
        return (Integer) features.map(Feature::scenarios).flatMap(s -> s.map(sc -> sc.steps().size())).reduce(Numbers.sum);
    }

    public Integer totalNumberPassingSteps() {
        return features.map(Feature::scenarios).flatMap(s -> s.flatMap(Scenario::steps)).filter(Step::hasPassed).size();
    }

    public Integer totalNumberFailingSteps() {
        return features.map(Feature::scenarios).flatMap(s -> s.flatMap(Scenario::steps)).filter(Step::hasFailed).size();
    }

    public Integer totalNumberSkippedSteps() {
        return features.map(Feature::scenarios).flatMap(s -> s.flatMap(Scenario::steps)).filter(Step::wasSkipped).size();
    }

    public Integer totalNumberPendingSteps() {
        return features.map(Feature::scenarios).flatMap(s -> s.flatMap(Scenario::steps)).filter(Step::wasPending).size();
    }

    public Integer totalNumberMissingSteps() {
        return features.map(Feature::scenarios).flatMap(s -> s.flatMap(Scenario::steps)).filter(Step::wasMissing).size();
    }
}
