package net.masterthought.cucumber.json;

import com.googlecode.totallylazy.Sequence;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;

public class Scenario {

    LinkedHashMap scenario;

    public Scenario(LinkedHashMap scenario) {
        this.scenario = option(scenario).getOrElse(new LinkedHashMap());
    }

    public String name() {
        return option((String)scenario.get("name")).getOrElse("");
    }

    public String description() {
        return option((String)scenario.get("description")).getOrElse("");
    }

    public String keyword() {
        return option((String)scenario.get("keyword")).getOrElse("");
    }

    public Sequence<Step> steps() {
        return sequence((ArrayList) scenario.get("steps")).map(s -> new Step((LinkedHashMap) s));
    }

    public Boolean isScenario(){
        return keyword().startsWith("Scenario");
    }


}
