package net.masterthought.cucumber.json;

import com.googlecode.totallylazy.Sequence;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.sequence;

public class Row {

    LinkedHashMap row;

    public Row(LinkedHashMap row) {
        this.row = option(row).getOrElse(new LinkedHashMap());
    }

    public Sequence cells() {
        return sequence((ArrayList) row.get("cells"));
    }


}
