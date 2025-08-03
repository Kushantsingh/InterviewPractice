package Report;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.util.*;

public class CustomReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        File file = new File("." + "/custom-report.html");

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("<html><head><title>Custom API Test Report</title></head><body>");
            writer.println("<h1>Test Execution Log</h1>");

            for (ISuite suite : suites) {
                for (ISuiteResult suiteResult : suite.getResults().values()) {
                    ITestContext context = suiteResult.getTestContext();

                    logResults(writer, context.getPassedTests().getAllResults(), "PASSED");
                    logResults(writer, context.getFailedTests().getAllResults(), "FAILED");
                    logResults(writer, context.getSkippedTests().getAllResults(), "SKIPPED");
                }
            }

            writer.println("</body></html>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void logResults(PrintWriter writer, Set<ITestResult> results, String status) {
        for (ITestResult result : results) {
            writer.println("<h3 style='color:" + getColor(status) + "'>Test: " + result.getMethod().getMethodName() + " [" + status + "]</h3>");

            writer.println("<ul>");
            List<String> output = Reporter.getOutput(result);
            for (String log : output) {
                writer.println("<li>" + log + "</li>");
            }
            writer.println("</ul>");
        }
    }

    private String getColor(String status) {
        return switch (status) {
            case "PASSED" -> "green";
            case "FAILED" -> "red";
            case "SKIPPED" -> "orange";
            default -> "black";
        };
    }
}
