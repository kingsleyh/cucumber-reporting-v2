package net.masterthought.cucumber;

import net.masterthought.cucumber.json.Feature;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReportInformationTest {

    ReportInformation reportInformation;
    ReportParser reportParser;

    @Before
    public void setUpReportInformation() throws IOException, URISyntaxException {
        ConfigurationOptions.setSkippedFailsBuild(false);
        ConfigurationOptions.setUndefinedFailsBuild(false);
        List<String> jsonReports = new ArrayList<String>();
        //will work iff the resources are not jarred up, otherwise use IOUtils to copy to a temp file.
        jsonReports.add(new File(ReportInformationTest.class.getClassLoader().getResource("net/masterthought/cucumber/project1.json").toURI()).getAbsolutePath());
        jsonReports.add(new File(ReportInformationTest.class.getClassLoader().getResource("net/masterthought/cucumber/project2.json").toURI()).getAbsolutePath());
        reportParser = new ReportParser(jsonReports);
        reportInformation = new ReportInformation(reportParser.reports());
    }


    @Test
    public void shouldListAllFeatures() throws IOException {
        assertThat(reportInformation.features().head(), instanceOf(Feature.class));
    }

//    @Test
//    public void shouldListAllTags() {
//        assertThat(reportInformation.getTags().get(0), is(TagObject.class));
//    }
//
//    @Test
//    public void shouldListFeaturesInAMap() {
//	//not really needed now -- have type safety with generics in object usage and would have failed had we not found the resource.
//        assertThat(reportInformation.getProjectFeatureMap().keySet(), hasItem(containsString("project1.json")));
//        assertThat(reportInformation.getProjectFeatureMap().entrySet().iterator().next().getValue().get(0), is(Feature.class));
//    }
//
    @Test
    public void shouldReturnTotalNumberOfScenarios() {
        assertThat(reportInformation.totalNumberOfScenarios(), is(10));
    }

    @Test
    public void shouldReturnTotalNumberOfFeatures() {
        assertThat(reportInformation.totalNumberOfFeatures(), is(4));
    }

    @Test
    public void shouldReturnTotalNumberOfSteps() {
        assertThat(reportInformation.totalNumberOfSteps(), is(98));
    }

    @Test
    public void shouldReturnTotalNumberPassingSteps() {
        assertThat(reportInformation.totalNumberPassingSteps(), is(90));
    }

    @Test
    public void shouldReturnTotalNumberFailingSteps() {
        assertThat(reportInformation.totalNumberFailingSteps(), is(2));
    }

    @Test
    public void shouldReturnTotalNumberSkippedSteps() {
        assertThat(reportInformation.totalNumberSkippedSteps(), is(6));
    }

    @Test
    public void shouldReturnTotalNumberPendingSteps() {
        assertThat(reportInformation.totalNumberPendingSteps(), is(0));
    }

    @Test
    public void shouldReturnTotalNumberMissingSteps() {
        assertThat(reportInformation.totalNumberMissingSteps(), is(0));
    }

    @Test
    public void shouldReturnTotalDuration() {
        assertThat(reportInformation.totalDuration(), is(new BigDecimal(236050000)));
    }
//
//    @Test
//    public void shouldReturnTotalDurationAsString() {
//        assertThat(reportInformation.getTotalDurationAsString(), is("236 ms"));
//    }
//
    @Test
    public void shouldReturnTimeStamp() {
        assertThat(reportInformation.timeStamp(), instanceOf(Instant.class));
    }
//
//    @Test
//    public void shouldReturnReportStatusColour() {
//        assertThat(reportInformation.getReportStatusColour(reportInformation.getFeatures().get(0)), is("#C5D88A"));
//    }
//
//    @Test
//    public void shouldReturnTagReportStatusColour() {
//        assertThat(reportInformation.getTagReportStatusColour(reportInformation.tagMap.get(0)), is("#C5D88A"));
//    }
//
//    @Test
//    public void shouldReturnTotalTags() {
//        assertThat(reportInformation.getTotalTags(), is(3));
//    }
//
//    @Test
//    public void shouldReturnTotalTagScenarios() {
//        assertThat(reportInformation.getTotalTagScenarios(), is(10));
//    }
//
//    @Test
//    public void shouldReturnTotalPassingTagScenarios() {
//        assertThat(reportInformation.getTotalPassingTagScenarios(), is(10));
//    }
//
//    @Test
//    public void shouldReturnTotalFailingTagScenarios() {
//        assertThat(reportInformation.getTotalFailingTagScenarios(), is(0));
//    }
//
//    @Test
//    public void shouldReturnTotalTagSteps() {
//        assertThat(reportInformation.getTotalTagSteps(), is(70));
//    }
//
//    @Test
//    public void shouldReturnTotalTagPasses() {
//        assertThat(reportInformation.getTotalTagPasses(), is(70));
//    }
//
//    @Test
//    public void shouldReturnTotalTagFails() {
//        assertThat(reportInformation.getTotalTagFails(), is(0));
//    }
//
//    @Test
//    public void shouldReturnTotalTagSkipped() {
//        assertThat(reportInformation.getTotalTagSkipped(), is(0));
//    }
//
//    @Test
//    public void shouldReturnTotalTagPending() {
//        assertThat(reportInformation.getTotalTagPending(), is(0));
//    }
//
//    @Test
//    public void shouldReturnTotalTagDuration() {
//        assertThat(reportInformation.getTotalTagDuration(), containsString("ms"));
//    }
//
//    @Test
//    public void shouldReturnTotalScenariosPassed() {
//        assertThat(reportInformation.getTotalScenariosPassed(), is(8));
//    }
//
//    @Test
//    public void shouldReturnTotalScenariosFailed() {
//        assertThat(reportInformation.getTotalScenariosFailed(), is(2));
//    }


}
