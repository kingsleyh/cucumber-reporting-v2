package net.masterthought.cucumber.json;

import com.googlecode.totallylazy.Sequence;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.sequence;

public class Step {

    LinkedHashMap step;

    public Step(LinkedHashMap step) {
        this.step = option(step).getOrElse(new LinkedHashMap());
    }

    public String name() {
        return option((String) step.get("name")).getOrElse("");
    }

    public String keyword() {
        return option((String) step.get("keyword")).getOrElse("");
    }

    public String line() {
        return option((String) step.get("line")).getOrElse("");
    }

    public Result result() {
        return new Result((LinkedHashMap) step.get("result"));
    }

    public Sequence<Row> rows() {
        return sequence((ArrayList) step.get("rows")).map(s -> new Row((LinkedHashMap) s));
    }

    public Boolean hasPassed() {
        return result().status().equals("passed");
    }

    public Boolean hasFailed() {
        return result().status().equals("failed");
    }

    public Boolean wasSkipped() {
        return result().status().equals("skipped");
    }

    public Boolean wasPending() {
        return result().status().equals("pending");
    }

    public Boolean wasMissing() {
        return result().status().equals("missing");
    }


}
