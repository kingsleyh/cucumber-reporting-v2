package net.masterthought.cucumber.json;

import com.googlecode.totallylazy.Sequence;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.sequence;

public class Feature {

    LinkedHashMap feature;

    public Feature(LinkedHashMap feature) {
        this.feature = option(feature).getOrElse(new LinkedHashMap());
    }

    public String name() {
        return option((String)feature.get("name")).getOrElse("");
    }

    public String uri() {
        return option((String)feature.get("uri")).getOrElse("");
    }

    public String description() {
        return option((String)feature.get("description")).getOrElse("");
    }

    public String keyword() {
        return option((String)feature.get("keyword")).getOrElse("");
    }

    public Sequence<Scenario> scenarios() {
        return sequence((ArrayList) feature.get("elements")).map(s -> new Scenario((LinkedHashMap) s));
    }


}
