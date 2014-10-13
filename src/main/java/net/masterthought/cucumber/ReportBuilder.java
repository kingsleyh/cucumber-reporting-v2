package net.masterthought.cucumber;


import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.MethodValueResolver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class ReportBuilder {

    private File reportDirectory;
    private String buildNumber;
    private String buildProject;
    private Boolean runWithJenkins;
    private ReportInformation ri;

    public ReportBuilder(List<String> jsonReports, File reportDirectory, String buildNumber, String buildProject, Boolean skippedFails, Boolean undefinedFails,
                         Boolean runWithJenkins) throws IOException {

        this.reportDirectory = reportDirectory;
        this.buildNumber = buildNumber;
        this.buildProject = buildProject;
        this.runWithJenkins = runWithJenkins;

        ConfigurationOptions.setSkippedFailsBuild(skippedFails);
        ConfigurationOptions.setUndefinedFailsBuild(undefinedFails);

        ReportParser reportParser = new ReportParser(jsonReports);
        ri = new ReportInformation(reportParser.reports());
    }

    public Boolean buildHasPassed() {
        return ri.totalNumberFailingSteps() > 0;
    }

    public void generateReports() throws Exception {
        generateFeatureOverview();
    }

    public void generateFeatureOverview() throws Exception {
        Handlebars handlebars = new Handlebars();
        Template template = handlebars.compile("templates/test");

        Context context = Context
           .newBuilder(ri)
           .resolver(MethodValueResolver.INSTANCE)
           .build();

       generateReport("feature-overview.html",template,context);
    }

    private void generateReport(String fileName, Template template, Context context) throws Exception {
          FileOutputStream fileStream = new FileOutputStream(new File(reportDirectory, fileName));
          OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");
          template.apply(context, writer);
          writer.flush();
          writer.close();
      }

}
