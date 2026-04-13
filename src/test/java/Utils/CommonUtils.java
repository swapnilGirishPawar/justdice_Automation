package Utils;

import BaseClass.Hooks;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    public static String reportFolderPath = "reports";
    public static ExtentReports extent = new ExtentReports();
    public static ExtentSparkReporter spark;
    ExtentTest test = Hooks.getTest();


    public static void extentReports(String platform, String appName) {
//        spark.config().setTimelineEnabled(true);
        spark = new ExtentSparkReporter(reportFolderPath + "/" + platform + "_" + appName + "_" + getCurrentDateTime() + ".html");
        spark.config().setCss(
                ".header { animation: zoomIn 0.5s; }" +
                        ".test.pass { animation: fadeIn 0.5s ease-in-out; }" +
                        ".test.fail { animation: shake 0.5s; }" +
                        ".test-summary { animation: pulse 1.5s infinite; }" +
                        ".test:hover { background-color: #f0f8ff; transform: scale(1.02); transition: 0.3s ease-in-out; }" +
                        "@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }" +
                        "@keyframes shake { 0%, 100% { transform: translateX(0); } 25% { transform: translateX(-5px); } 50% { transform: translateX(5px); } 75% { transform: translateX(-5px); } }" +
                        "@keyframes zoomIn { from { transform: scale(0.5); } to { transform: scale(1); } }" +
                        "@keyframes pulse { 0% { transform: scale(1); } 50% { transform: scale(1.05); } 100% { transform: scale(1); } }"
        );
        spark.config().setDocumentTitle(appName + "Automation Report");
        spark.config().setReportName(platform + appName);
        extent.attachReporter(spark);
    }
    public static String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a custom date and time format if needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
        // Format the current date and time using the formatter
        return currentDateTime.format(formatter);
    }

    public void passMessage(String msg){
        Hooks.getTest().pass(msg);
    }
    public void infoMessage(String msg){
        Hooks.getTest().info(msg);
    }
    public void failMessage(Throwable e) {
        Hooks.getTest().fail("Test failed: " + e.getMessage());
    }
}
