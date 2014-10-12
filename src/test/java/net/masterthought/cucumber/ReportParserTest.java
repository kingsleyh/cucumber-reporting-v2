package net.masterthought.cucumber;

import net.masterthought.cucumber.json.Feature;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.masterthought.cucumber.util.Util.getAbsolutePathFromResource;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportParserTest {

    @Test
    public void shouldReturnAListOfFeaturesFromAJsonReport() throws IOException {
        ReportParser reportParser = new ReportParser(validJsonReports());
        assertThat(reportParser.reports().size(), is(2));
        assertThat(reportParser.reports().head().getKey(), endsWith("project1.json"));
        assertThat(reportParser.reports().head().getValue().features().size(), is(2));
        assertThat(reportParser.reports().head().getValue().features().head(), instanceOf(Feature.class));
    }

    @Test
    public void shouldContainFourFeatures() throws IOException {
        ReportParser reportParser = new ReportParser(validJsonReports());
        assertThat(reportParser.reports().flatMap(r -> r.getValue().features()).size(), is(4));
    }

    @Test
    public void shouldIgnoreEmptyJsonFiles() throws IOException {
        ReportParser reportParser = new ReportParser(withEmptyJsonReport());
        assertThat(reportParser.reports().size(), is(1));
    }

    @Test
    public void shouldIgnoreJsonFilesThatAreNotCucumberReports() throws IOException {
        ReportParser reportParser = new ReportParser(withNonCucumberJson());
        assertThat(reportParser.reports().size(), is(1));
    }

    @Test
    public void shouldProcessCucumberReportsWithNoSteps() throws IOException {
        ReportParser reportParser = new ReportParser(withNoStepsInJsonReport());
        assertThat(reportParser.reports().size(), is(2));
    }
//
//    @Test
//    public void shouldProcessCucumberReportsWithNoSteps2() throws IOException {
//        ReportParser reportParser = new ReportParser(withNoSteps2InJsonReport());
//        ReportInformation reportInformation = new ReportInformation(reportParser.getFeatures());
//
//        // Should not crash with NPE
//        assertThat(reportInformation.getFeatures().get(0), is(Feature.class));
//        assertThat(reportParser.getFeatures().entrySet().size(), is(1));
//    }
//
    @Test
    public void shouldProcessCucumberReportsWithNoScenarios() throws IOException {
        ReportParser reportParser = new ReportParser(withNoScenariosInJsonReport());
        assertThat(reportParser.reports().size(), is(2));
    }

    private List<String> validJsonReports() {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/project1.json"));
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/project2.json"));
        return jsonReports;
    }

    private List<String> withEmptyJsonReport() {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/project1.json"));
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/empty.json"));
        return jsonReports;
    }

    private List<String> withNonCucumberJson() {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/project1.json"));
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/somethingelse.json"));
        return jsonReports;
    }

    private List<String> withNoStepsInJsonReport() {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/project1.json"));
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/nosteps.json"));
        return jsonReports;
    }

    private List<String> withNoSteps2InJsonReport() {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/nosteps2.json"));
        return jsonReports;
    }

    private List<String> withNoScenariosInJsonReport() {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/project1.json"));
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/noscenario.json"));
        return jsonReports;
    }

}
