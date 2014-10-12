package net.masterthought.cucumber;

import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import net.masterthought.cucumber.json.JsonReport;

import java.io.IOException;
import java.util.List;

import static com.googlecode.totallylazy.Pair.pair;
import static com.googlecode.totallylazy.Sequences.sequence;
import static net.masterthought.cucumber.util.Util.*;

public class ReportParser {

    private Sequence<Pair<String,JsonReport>> reports;

    public ReportParser(List<String> jsonReportFiles) throws IOException {
        this.reports = sequence(jsonReportFiles).map(r -> pair(r, U2U(readFileAsString(r))))
                .filter(r -> isValidCucumberJsonReport(r.second()))
                .map(r -> pair(r.first(), new JsonReport(r.second())));
    }

    public Sequence<Pair<String,JsonReport>> reports() {
        return reports;
    }

}
