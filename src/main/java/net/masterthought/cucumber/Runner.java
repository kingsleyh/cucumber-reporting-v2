package net.masterthought.cucumber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws Exception {
        File rd = new File("/Users/kings/development/projects/cucumber-reporting-v2/src/test/resources");
        List<String> list = new ArrayList<String>();
        list.add("/Users/kings/development/projects/cucumber-reporting-v2/src/test/resources/net/masterthought/cucumber/project1.json");
        ReportParser reportParser = new ReportParser(list);
        System.out.println(reportParser.reports().head().getValue().features().head().scenarios().head().keyword());
    }

}
