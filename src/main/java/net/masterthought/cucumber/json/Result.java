package net.masterthought.cucumber.json;

import com.googlecode.totallylazy.Sequence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.sequence;

public class Result {

    LinkedHashMap result;

    public Result(LinkedHashMap result) {
        this.result = option(result).getOrElse(new LinkedHashMap());
    }

    public String status() {
        return option((String)result.get("status")).getOrElse("");
    }

    public String errorMessage() {
        return option((String)result.get("error_message")).getOrElse("");
    }

    public BigDecimal duration() {
        return option(new BigDecimal((String)result.get("duration"))).getOrElse(new BigDecimal(0));
    }


}
