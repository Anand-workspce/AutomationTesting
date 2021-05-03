package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Laptops {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.opencart.com/index.php?route=common/home");
		
		//Click on ‘Phones & PDAs’ menu	
		System.out.println("clicking on Phones & PDAs");
		driver.findElement(By.xpath("//a[text()='Phones & PDAs']")).click();
		
		//Clicking on compare this product 1st mobile
		System.out.println("clicking on compare option mobile1");
		driver.findElement(By.xpath("(//div[@class='button-group']/button[3])[1]")).click();
		
		//Getting selected mobile name from alert message
		String mobName1=driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']/a")).getText();
		System.out.println("mobile name taken from alert:"+mobName1);
		
		Thread.sleep(1000);
		//Clicking on compare this product 2nd mobile
		System.out.println("clicking on compare option mobil2");
		driver.findElement(By.xpath("(//div[@class='button-group']/button[3])[2]")).click();
		Thread.sleep(3000);
		
		//Getting selected mobile name from alert message
		String mobName2=driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']/a")).getText();		
		System.out.println("mobile name taken from alert:"+mobName2);
		
		//Clicking product compare hyperlink
		System.out.println("clicking on product compare hyperlink");
		driver.findElement(By.xpath("//a[@id='compare-total']")).click();
		
		//Getting first mobile name from table and compare
		String mob1selected=driver.findElement(By.xpath("//tr[1]//td[2]")).getText();
		System.out.println("mobile1 name from comprision table:"+mob1selected);		
		Assert.assertEquals(mobName1,mob1selected,"Mobile names not matching");
		
		//Getting second mobile name from table and compare
		String mob2selected=driver.findElement(By.xpath("//tr[1]//td[3]")).getText();
		System.out.println("mobile2 name from comprision table:"+mob2selected);
		Assert.assertEquals(mobName2,mob2selected,"Mobile names not matching");
		System.out.println(" ");
		//Product summary
		System.out.println("Product Summary");
		System.out.println("-------------------");
		
		//Summary of mobile1
		List rowSize=driver.findElements(By.xpath("//tbody//tr"));
		for(int i=1;i<rowSize.size();i++) {
			String rowdata=driver.findElement(By.xpath("(//tbody//tr["+i+"]/td[1])[1]")).getText();
			String mob1data=driver.findElement(By.xpath("(//tbody//tr["+i+"]/td[2])[1]")).getText();
			System.out.println(rowdata +":"+mob1data);
			
		}
		System.out.println("-------------------");
		////Summary of mobile2
		for(int j=1;j<rowSize.size();j++) {
			String rowdata=driver.findElement(By.xpath("(//tbody//tr["+j+"]/td[1])[1]")).getText();
			String mob2data=driver.findElement(By.xpath("(//tbody//tr["+j+"]/td[3])[1]")).getText();
			System.out.println(rowdata +":"+mob2data);			
		}
		System.out.println("");
		
		//currency converter
		driver.findElement(By.xpath("//i[@class='fa fa-home']")).click();
		System.out.println("Click on ‘iPhone’ under Featured section");
		driver.findElement(By.xpath("//a[text()='iPhone']")).click();
		String iphoneDollarPrice=driver.findElement(By.xpath("//li/h2")).getText();
		System.out.println("Current price in Dollar:"+iphoneDollarPrice);
		driver.findElement(By.xpath("//div[@class='btn-group']/button")).click();
		
		System.out.println("Selecting pound currency");
		driver.findElement(By.name("GBP")).click();
		String iphonePoundPrice=driver.findElement(By.xpath("//li/h2")).getText();
		System.out.println("Pound value of mobile:"+iphonePoundPrice);
		
		Thread.sleep(2000);
		System.out.println("Selecting Euro currency");
		driver.findElement(By.xpath("//div[@class='btn-group']/button")).click();
		driver.findElement(By.name("EUR")).click();
		String iphoneEuroPrice=driver.findElement(By.xpath("//li/h2")).getText();
		System.out.println("Euro value of mobile:"+iphoneEuroPrice);
		System.out.println("");
		
		//Verify the selected/chosen currency is displayed across the page.
		System.out.println("Verify the selected/chosen currency is displayed across the page");
		System.out.println("Selecting pound currency");
		driver.findElement(By.xpath("//div[@class='btn-group']/button")).click();
		driver.findElement(By.name("GBP")).click();		
		String currencySymbol=driver.findElement(By.xpath("//strong")).getText();
		System.out.println("currencySymbol:"+currencySymbol);
		
		
		
		List<WebElement> pricesofApplecinema =driver.findElements(By.xpath("//p[@class='price']/span"));
		for(WebElement f:pricesofApplecinema) {
			String pricevalues= f.getText();
			System.out.println(pricevalues);
			System.out.println("Do price values contain currency symbol:"+pricevalues.contains(currencySymbol));
			
		}	
		System.out.println("");
		
		
		//Searching mac in search bar
		System.out.println("Searching mac in search bar");
		WebElement search=driver.findElement(By.name("search"));
		search.sendKeys("mac");
		search.sendKeys(Keys.ENTER);
		
		//sorting product value High-low
		System.out.println("sorting product value High-low");
		WebElement sort=driver.findElement(By.id("input-sort"));
		Select s= new Select(sort);
		s.selectByVisibleText("Price (High > Low)");
		
		

		//Verify prices are in decreasing order
		
		List<WebElement> mobprice=driver.findElements(By.xpath("//p[@class='price']"));
		ArrayList<String> prices= new ArrayList<String>();	
		
		for(WebElement e:mobprice) {			 
			prices.add(e.getText());			
		}	
		for(int l=0;l<mobprice.size();l++) {
			String S=(String) prices.get(l);
			//System.out.println(prices.get(l));
			String m[]=S.split("E");
			//System.out.println(m[0].substring(1).replace(",", ""));
			 S=m[0].substring(1).replace(",", "");
			// Integer.parseInt(S);
			prices.set(l, S.trim());
			
		}
		System.out.println(prices);
		ArrayList<Double> resultList = getIntegerArray(prices);
		System.out.println(resultList);
		
		
		List<Double> sortedPrices = new ArrayList<Double>(resultList);
		
		// sort the list
		Collections.sort(sortedPrices);	
		Collections.reverse(sortedPrices);
		System.out.println(sortedPrices);
		// true if the prices are sorted
		System.out.println(sortedPrices.equals(resultList));
	
	}
		
		
		static ArrayList<Double> getIntegerArray(ArrayList<String> prices) {
	        ArrayList<Double> result = new ArrayList<Double>();
	        for(String stringValue : prices) {
	            try {
	                //Convert String to Integer, and store it into integer array list.
	            Double d=	Double.parseDouble(stringValue);
	            				
	                result.add(d);
	            } catch(NumberFormatException nfe) {
	               System.out.println("Could not parse " + nfe);
	                
	            } 
	        }       
	        return result;
	    }
	
			
		
					
	
		
	}



