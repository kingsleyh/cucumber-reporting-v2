package net.masterthought.cucumber.util;

import net.masterthought.cucumber.ReportParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String readFileAsString(String filePath) throws java.io.IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null) try {
                f.close();
            } catch (IOException ignored) {
            }
        }
        return new String(buffer);
    }

    public static boolean isValidCucumberJsonReport(String fileContent) {
        return fileContent.contains("\"keyword\":");
    }

    public static String U2U(String s) {
        final Pattern p = Pattern.compile("\\\\u\\s*([0-9(A-F|a-f)]{4})", Pattern.MULTILINE);
        String res = s;
        Matcher m = p.matcher(res);
        while (m.find()) {
            res = res.replaceAll("\\" + m.group(0),
                    Character.toString((char) Integer.parseInt(m.group(1), 16)));
        }
        return res;
    }

    public static String getAbsolutePathFromResource(String resource) {
        try {
            return new File(ReportParser.class.getClassLoader().getResource(resource).toURI()).getAbsolutePath();
        } catch (URISyntaxException use) {
            throw new RuntimeException("could not read resource " + resource, use);
        }
    }

}
