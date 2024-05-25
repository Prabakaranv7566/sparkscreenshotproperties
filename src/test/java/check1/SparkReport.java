package check1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SparkReport {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		File f=new File("C:\\Users\\RAMAC\\eclipse-workspace\\test1\\src\\test\\resources\\prop.properties");
		FileReader fr=new FileReader(f);
		Properties prop=new Properties();
		prop.load(fr);
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get(prop.getProperty("url"));
		
		
		
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\src\\test\\resources\\Reports\\extentdemo.html");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(spark);
		ExtentTest test=extent.createTest("Checking the login").assignAuthor("PrabaTest").assignDevice("chrome").assignCategory("Regression");
		test.log(Status.INFO,"Capturing the title");
		String title = driver.getTitle();
		if(title.equals("Index"))
		{
			test.pass("Title Matched");
			//Assert.assertTrue(true);
		}
		else
		{
			test.fail("Title mismatch");
		}
		extent.flush();
	}

}
