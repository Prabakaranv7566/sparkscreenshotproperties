package check1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SparkScreen {
	
	public static void main(String[] args) throws IOException 
	{
		File f=new File("C:\\Users\\RAMAC\\eclipse-workspace\\test1\\src\\test\\resources\\prop.properties");
		FileReader fr=new FileReader(f);
		Properties prop=new Properties();
		prop.load(fr);
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get(prop.getProperty("url"));
		driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(prop.getProperty("name"));
		
		
		TakesScreenshot scr=((TakesScreenshot)driver);
		File scrfile=scr.getScreenshotAs(OutputType.FILE);
		File destfile=new File("C:\\Users\\RAMAC\\eclipse-workspace\\test1\\screenshot\\name.jpeg");
		FileUtils.copyFile(scrfile, destfile);
		
		
		
		WebElement frames = driver.findElement(By.id("singleframe"));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[text()='SwitchTo']")).click();
		driver.findElement(By.xpath("//a[text()='Windows']")).click();
		
		String parentwindow = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//button[@class=\"btn btn-info\"]")).click();
		
		driver.switchTo().window(parentwindow);
		driver.findElement(By.xpath("//a[text()='SwitchTo']")).click();
		driver.findElement(By.xpath("//a[text()='Frames']")).click();
		driver.switchTo().frame(1);
		
		driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("hi");
		

	}

}
