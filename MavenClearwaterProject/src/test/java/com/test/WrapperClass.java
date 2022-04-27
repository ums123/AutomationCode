package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WrapperClass {
	public static WebDriver driver;
	String strng = null;
	String part = null;
	String product = null;
	String text = null;
	String trimtext = null;
	String text1 = null;
	String refvalue = null;
	String actvalue = null;
	String a = null;
	String p = null;
	String name = null;
	String title = null;
	String time = null;

	public static String downloadPath = "D:\\eclipse-workspace\\MavenClearwaterProject\\src\\test\\resources\\WebDriverDownloads";

	/**
	 * Launch the desired browser and click the URL
	 * 
	 * @param Browser
	 * @param URL
	 * @throws InterruptedException
	 */
	@SuppressWarnings("deprecation")
	public void launchBroswer(String Browser, String URL) {
		if (Browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			FirefoxProfile profile = new FirefoxProfile();

			// Set Location to store files after downloading.
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.dir", downloadPath);

			// Set Preference to not show file download confirmation dialogue using MIME
			// types Of different file extension types.
			profile.setPreference("browser.helperApps.neverAsk.openFile",
					"application/msword, application/x-unknown, text/comma-separated-values, application/excel, application/vnd.ms-excel, application/vnd.msexcel, text/anytext, application/pdf, application/csv, text/csv, image/png , image/jpeg, text/html, text/plain, application/octet-stream, image/svg+xml");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/msword, application/x-unknown, text/comma-separated-values, application/excel, application/vnd.ms-excel, application/vnd.msexcel, text/anytext, application/pdf, application/csv, text/csv, image/png , image/jpeg, text/html, text/plain, application/octet-stream, image/svg+xml");
			// profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
			// "text/x-csv,application/x-csv,text/x-comma-separated-values,text/comma-separated-values,application/csv,text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/pdf");
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.setPreference("browser.download.manager.focusWhenStarting", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			profile.setPreference("browser.download.manager.closeWhenDone", false);
			profile.setPreference("pdfjs.disabled", true);

			driver = new FirefoxDriver();

		} else if (Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();

			//System.setProperty("webdriver.chrome.driver", "D:\\eclipse-workspace\\MavenClearwaterProject\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (Browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		// driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		// driver.get("https://software-stage.clearwatercompliance.com/");

	}

	/**
	 * Function used to pass the Web element ID and Value to entered in text box
	 * 
	 * @param id        = Web Element ID
	 * @param sendvalue = Value entered in Text Box
	 * @return
	 * @throws IOException
	 */
	String browser = null;
	private boolean acceptNextAlert = true;

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	public void sendvaluebyid(String id, String sendvalue) throws IOException {
		try {
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(sendvalue);

		} catch (NoSuchElementException expType) {

			expType.printStackTrace();

		} catch (NotFoundException expType) {

			expType.printStackTrace();
		} catch (WebDriverException expType) {

			expType.printStackTrace();
		} catch (Exception expType) {

			expType.printStackTrace();
		} finally {
			takesnapshot(id);
		}
	}

	public void refreshpage() throws IOException {
		try {
			driver.navigate().refresh();

		} catch (Exception expType) {

			expType.printStackTrace();
		}
	}

	public void sendvaluebyeid(String id, String sendvalue) throws IOException {
		try {
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(sendvalue);
			driver.findElement(By.id(id)).sendKeys(Keys.ENTER);

		} catch (NoSuchElementException expType) {

			expType.printStackTrace();

		} catch (NotFoundException expType) {

			expType.printStackTrace();
		} catch (WebDriverException expType) {

			expType.printStackTrace();
		} catch (Exception expType) {

			expType.printStackTrace();
		} finally {
			takesnapshot(id);
		}
	}

	public void sendvaluebyidnum(String id, Integer i) throws IOException {
		try {
			String t = i.toString();
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(t);

		} catch (NoSuchElementException expType) {

			expType.printStackTrace();

		} catch (NotFoundException expType) {

			expType.printStackTrace();
		} catch (WebDriverException expType) {

			expType.printStackTrace();
		} catch (Exception expType) {

			expType.printStackTrace();
		} finally {
			takesnapshot(id);
		}
	}

	public void sendvaluebyidbignum(String id, long i) throws IOException {
		try {
			String t = Long.toString(i);
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(t);

		} catch (NoSuchElementException expType) {

			expType.printStackTrace();

		} catch (NotFoundException expType) {

			expType.printStackTrace();
		} catch (WebDriverException expType) {

			expType.printStackTrace();
		} catch (Exception expType) {

			expType.printStackTrace();
		} finally {
			takesnapshot(id);
		}
	}

	/**
	 * Function used to pass the Web element Name and Value to entered in text box
	 * 
	 * @param name=     Web Element name
	 * @param sendvalue = Value entered in Text Box
	 * @throws IOException
	 */
	public void sendvaluebyname(String name, String sendvalue) throws IOException {
//		try {
		driver.findElement(By.name(name)).clear();
		driver.findElement(By.name(name)).sendKeys(sendvalue);
//		} catch (NoSuchElementException expType) {
//
//			expType.printStackTrace();
//
//		} catch (NotFoundException expType) {
//
//			expType.printStackTrace();
//		} catch (WebDriverException expType) {
//
//			expType.printStackTrace();
//		} catch (Exception expType) {
//
//			expType.printStackTrace();
//		} finally {
//			takesnapshot(name);
//		}
	}

	/**
	 * Function used to pass the Web element X-path and Value to entered in text
	 * box
	 * 
	 * @param xpath=
	 *            Web Element's X-path
	 * @param sendvalue
	 *            = Value entered in Text Box
	 * @throws IOException
	 */
	public void sendvaluebyxpath(String xpath, String sendvalue) throws IOException {
		try {
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(sendvalue);
		} 
//			catch (NoSuchElementException expType) {
//
//			expType.printStackTrace();
//
//		} catch (NotFoundException expType) {
//
//			expType.printStackTrace();
//		} catch (WebDriverException expType) {
//
//			expType.printStackTrace();
//		} 
			catch (Exception expType) {
			System.out.println();
		} 
	//		finally {
//			takesnapshot(sendvalue);
//		}
	}

	public void sendwoutclvaluebyxpath(String xpath, String sendvalue) throws IOException {
//		try {
		driver.findElement(By.xpath(xpath)).sendKeys(sendvalue);
//		} catch (NoSuchElementException expType) {
//
//			expType.printStackTrace();
//
//		} catch (NotFoundException expType) {
//
//			expType.printStackTrace();
//		} catch (WebDriverException expType) {
//
//			expType.printStackTrace();
//		} catch (Exception expType) {
//
//			expType.printStackTrace();
//		} finally {
//			takesnapshot(sendvalue);
//		}
	}

	public void sendvalueenterbyxpath(String xpath, String sendvalue) throws IOException {
//		try {
		WebElement se = driver.findElement(By.xpath(xpath));
		se.clear();
		se.sendKeys(sendvalue);
		se.sendKeys(Keys.ENTER);
//		} catch (NoSuchElementException expType) {
//
//			expType.printStackTrace();
//
//		} catch (NotFoundException expType) {
//
//			expType.printStackTrace();
//		} catch (WebDriverException expType) {
//
//			expType.printStackTrace();
//		} catch (Exception expType) {
//
//			expType.printStackTrace();
//		} finally {
//			takesnapshot(sendvalue);
//		}
	}

	public void sendpartvaluebyxpathanddownclick(String xpath, String sendvalue)
			throws IOException, InterruptedException {
//		try {
		WebElement we = driver.findElement(By.xpath(xpath));
		we.sendKeys(sendvalue);
		Actions builder = new Actions(driver);
		Thread.sleep(2000);
		Action mouseOverHome = builder.click(we).pause(2000).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build();
		mouseOverHome.perform();
//		} catch (NoSuchElementException expType) {
//
//			expType.printStackTrace();
//
//		} catch (NotFoundException expType) {
//
//			expType.printStackTrace();
//		} catch (WebDriverException expType) {
//
//			expType.printStackTrace();
//		} catch (Exception expType) {
//
//			expType.printStackTrace();
//		} finally {
//			takesnapshot(sendvalue);
//		}
	}

	public void sendproductvaluebyxpathanddownclick(String xpath, String sendvalue)
			throws IOException, InterruptedException {
//		try {
		WebElement we = driver.findElement(By.xpath(xpath));
		we.sendKeys(sendvalue);
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		Action mouseOverHome = builder.click(we).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build();
		mouseOverHome.perform();
//		} catch (NoSuchElementException expType) {
//
//			expType.printStackTrace();
//
//		} catch (NotFoundException expType) {
//
//			expType.printStackTrace();
//		} catch (WebDriverException expType) {
//
//			expType.printStackTrace();
//		} catch (Exception expType) {
//
//			expType.printStackTrace();
//		} finally {
//			takesnapshot(sendvalue);
//		}
	}

	public void sendvaluebyxpathand2downclick(String xpath, String sendvalue) throws IOException {
//		try {
		WebElement we = driver.findElement(By.xpath(xpath));
		we.sendKeys(sendvalue);
		Actions builder = new Actions(driver);
		Action mouseOverHome = builder.click(we).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ENTER).build();
		mouseOverHome.perform();
//		} catch (NoSuchElementException expType) {
//
//			expType.printStackTrace();
//
//		} catch (NotFoundException expType) {
//
//			expType.printStackTrace();
//		} catch (WebDriverException expType) {
//
//			expType.printStackTrace();
//		} catch (Exception expType) {
//
//			expType.printStackTrace();
//		} finally {
//			takesnapshot(sendvalue);
//		}
	}

	/**
	 * Function used to pass the Web element Tag name and Value to entered in text
	 * box
	 * 
	 * @param Tagname            = Tag Name of the Web Element
	 * @param sendvaluesendvalue = Value entered in Text Box
	 * @throws IOException
	 */
	public void sendvaluebyTagname(String Tagname, String sendvalue) throws IOException {
		try {
			driver.findElement(By.tagName(Tagname)).clear();
			driver.findElement(By.tagName(Tagname)).sendKeys(sendvalue);
		} catch (NoSuchElementException expType) {

			expType.printStackTrace();

		} catch (NotFoundException expType) {

			expType.printStackTrace();
		} catch (WebDriverException expType) {

			expType.printStackTrace();
		} catch (Exception expType) {

			expType.printStackTrace();
		} finally {
			takesnapshot(Tagname);
		}
	}

	/**
	 * Function used to pass the Web element Class Name and Value to entered in text
	 * box
	 * 
	 * @param classname=         Class Name of the Web Element
	 * @param sendvaluesendvalue = Value entered in Text Box
	 * @throws IOException
	 */
	public void sendvaluebyclassname(String classname, String sendvalue) throws IOException {
		try {
			driver.findElement(By.className(classname)).clear();
			driver.findElement(By.className(classname)).sendKeys(sendvalue);
		} catch (NoSuchElementException expType) {

			expType.printStackTrace();

		} catch (NotFoundException expType) {

			expType.printStackTrace();
		} catch (WebDriverException expType) {

			expType.printStackTrace();
		} catch (Exception expType) {

			expType.printStackTrace();
		} finally {
			takesnapshot(classname);
		}
	}

	/**
	 * Function used to pass the Web element Class Name and Value to entered in text
	 * box
	 * 
	 * @param selector
	 * @param sendvalue
	 * @throws IOException
	 */
	public void sendvaluebyCssselector(String selector, String sendvalue) throws IOException {
		try {
			driver.findElement(By.cssSelector(selector)).clear();
			driver.findElement(By.cssSelector(selector)).sendKeys(sendvalue);
		} catch (NoSuchElementException expType) {

			expType.printStackTrace();

		} catch (NotFoundException expType) {

			expType.printStackTrace();
		} catch (WebDriverException expType) {

			expType.printStackTrace();
		} catch (Exception expType) {

			expType.printStackTrace();
		} finally {
			takesnapshot(selector);
		}
	}

	// To close the single opened browser which opened by Selenium
	public void closeBrowser() {
		driver.close();
	}

	// To close all the opened browser which opened by Selenium
	public void closeAllBrowsers() {
		driver.close();
	}

	// Wait Until the Browser page loads
	public void waitTillBrowserLoads(int limit) {
		driver.manage().timeouts().implicitlyWait(limit, TimeUnit.SECONDS);
	}

	/**
	 * Click the Web Element by possible locator
	 */
	public void iconClick(String xpath) {
//		  try{
		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
//		   }
//		  catch (NoSuchElementException expType) {
//
//		   expType.printStackTrace();
//
//		  } catch (NotFoundException expType) {
//
//		   expType.printStackTrace();
//		  } catch (WebDriverException expType) {
//
//		   expType.printStackTrace();
//		  } catch (Exception expType) {
//
//		   expType.printStackTrace();
//		  }		  
	}

	public void clickByXpath(String xpath) {
//		try {
		driver.findElement(By.xpath(xpath)).click();
//		 } catch (NoSuchElementException expType) {
//
//			expType.printStackTrace();
//
//		} catch (NotFoundException expType) {
//
//			expType.printStackTrace();
//		} catch (WebDriverException expType) {
//
//			expType.printStackTrace();
//		} catch (Exception expType) {
//
//			expType.printStackTrace();
//		}
	}

	public void clickwaitByXpath(String xpath) {
		try {
			WebElement w = driver.findElement(By.xpath(xpath));
			wait(3000);
			w.click();
		} catch (NoSuchElementException expType) {

			expType.printStackTrace();

		} catch (NotFoundException expType) {

			expType.printStackTrace();
		} catch (WebDriverException expType) {

			expType.printStackTrace();
		} catch (Exception expType) {

			expType.printStackTrace();
		}
	}

	public void clickByCategoryXpath(String xpath, String Cate) {
		try {
			WebElement we = driver.findElement(By.xpath(xpath));
			Actions builder = new Actions(driver);
			@SuppressWarnings("deprecation")
			Action mouseOverHome = builder.click(we).pause(2000).sendKeys(Cate).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER)
					.build();
			mouseOverHome.perform();

		} catch (Exception e) {
			System.out.println("Not able to recognize Webelement " + xpath + " here");
		}
	}

	public void clickByHDEmXpath(String xpath) {
//		try {
		WebElement we = driver.findElement(By.xpath(xpath));
		Actions builder = new Actions(driver);
		Action mouseOverHome = builder.click(we).sendKeys(Keys.ESCAPE).build();
		mouseOverHome.perform();
//			
//		} catch (Exception e) {
//			System.out.println("Not able to recognize Webelement " + xpath + " here");
//		}
	}

	public void clickByid(String id) {
		try {
			driver.findElement(By.id(id)).click();
		} catch (Exception e) {
			System.out.println("Not able to recognize Webelement " + id + " here");
		}
	}

	public void clickByname(String name) {
		try {
			driver.findElement(By.name(name)).click();
		} catch (Exception e) {
			System.out.println("Not able to recognize Webelement " + name + " here");
		}
	}

	public void clickBytagname(String tagname) {
		try {
			driver.findElement(By.tagName(tagname)).click();
		} catch (Exception e) {
			System.out.println("Not able to recognize Webelement " + tagname + " here");
		}
	}

	public void clickBycssSelector(String selector) {
		try {
			driver.findElement(By.cssSelector(selector)).click();
		} catch (Exception e) {
			System.out.println("Not able to recognize Webelement " + selector + " here");
		}
	}

	public void clickByclassname(String classname) {
		try {
			driver.findElement(By.className(classname)).click();
		} catch (Exception e) {
			System.out.println("Not able to recognize Webelement " + classname + " here");
		}
	}

	public void clickBylinktext(String text) {
		try {
			driver.findElement(By.linkText(text)).click();
		} catch (Exception e) {
			System.out.println("Not able to recognize Webelement " + text + " here");
		}
	}

	public void clickBypartiallinktext(String text) {
		try {
			driver.findElement(By.partialLinkText(text)).click();
		} catch (Exception e) {
			System.out.println("Not able to recognize Webelement " + text + " here");
		}
	}

	public List<WebElement> ByXpath(String xpath) {
		driver.findElement(By.xpath(xpath));
		return null;
	}

	public void uploadFile(String xpath, String sendvalue) {
		WebElement uploadElement = driver.findElement(By.xpath(xpath));
		uploadElement.sendKeys(sendvalue);
	}

	public void takesnapshot(String Snapshot) throws IOException {
		File snaps = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(snaps, new File("./Screenshots/" + Snapshot + ".png"), true);
	}

	public void quitBrowser() throws IOException, InterruptedException {
		driver.quit();
		Thread.sleep(3000);
	}

	public void selectByid_lastIndex(String element_id) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.id(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			int jobrolesize = jobrole_list.getOptions().size();
			jobrole_list.selectByIndex(jobrolesize - 1);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectById_Index(String element_id, int i) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.id(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByIndex(i);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index " + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByid_Value(String element_id, String Value) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.id(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByValue(Value);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + Value);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByid_Visibletext(String element_id, String text) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.id(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByVisibleText(text);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + text);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByXpath_lastIndex(String element_id) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.xpath(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			int jobrolesize = jobrole_list.getOptions().size();
			jobrole_list.selectByIndex(jobrolesize - 1);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByXpath_Index(String element_id, int i) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.xpath(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByIndex(i);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index " + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByXpath_Value(String element_id, String Value) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.xpath(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByValue(Value);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + Value);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByXpath_Visibletext(String element_id, String text) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.xpath(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByVisibleText(text);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + text);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} // finally {
			// takesnapshot(element_id);
			// }
	}

	public void selectByTag_lastIndex(String element_id) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.tagName(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			int jobrolesize = jobrole_list.getOptions().size();
			jobrole_list.selectByIndex(jobrolesize - 1);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByTag_Index(String element_id, int i) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.tagName(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByIndex(i);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index " + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByTag_Value(String element_id, String Value) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.tagName(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByValue(Value);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + Value);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByTag_Visibletext(String element_id, String text) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.tagName(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByVisibleText(text);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + text);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByName_lastIndex(String element_id) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.name(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			int jobrolesize = jobrole_list.getOptions().size();
			jobrole_list.selectByIndex(jobrolesize - 1);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByName_Index(String element_id, int i) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.name(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByIndex(i);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index " + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByName_Value(String element_id, String Value) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.name(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByValue(Value);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + Value);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByName_Visibletext(String element_id, String text) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.name(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByVisibleText(text);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + text);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByClass_lastIndex(String element_id) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.className(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			int jobrolesize = jobrole_list.getOptions().size();
			jobrole_list.selectByIndex(jobrolesize - 1);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByClass_Index(String element_id, int i) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.className(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByIndex(i);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(" Cannot locate option with index " + i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void selectByClass_Value(String element_id, String Value) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.className(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByValue(Value);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + Value);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	WebElement searchElement;

	public void Searchelement(String xpath) throws IOException {
		// try
		// {
		searchElement = driver.findElement(By.xpath(xpath));
		// }
		// catch(Exception e)
		// {
		// e.printStackTrace();
//		   searchElement = null;
//		   System.out.println("The Search element "+xpath+" is not found");
		// }
	}

	WebElement FilterElement;

	public void Filterelement(String xpath) throws IOException {
		try {
			FilterElement = driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			FilterElement = null;
		}
	}

	public void selectByClass_Visibletext(String element_id, String text) throws IOException {
		try {
			WebElement dropdown_jobrole = driver.findElement(By.className(element_id));
			Select jobrole_list = new Select(dropdown_jobrole);
			jobrole_list.selectByVisibleText(text);
		} catch (NoSuchElementException e) {
			System.out.println(" Cannot locate option with value: " + text);
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + element_id + " here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			takesnapshot(element_id);
		}
	}

	public void mouseHoverByXpath(String xpath) {
		try {
			Actions mouseAction = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(xpath));
			mouseAction.moveToElement(we).build().perform();
			System.out.println("The xpath:" + xpath + " is moused over successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mouseHoverdownByXpath(String xpath) {
		try {
			WebElement we = driver.findElement(By.xpath(xpath));
			we.sendKeys(Keys.ARROW_DOWN);
			System.out.println("The xpath:" + xpath + " is moused over successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public void mouseHoverlocXpath(String xpath) { try { WebElement we =
	 * driver.findElement(By.xpath(xpath)); Actions mouseAction = new
	 * Actions(driver); Actions seriesOfActions =
	 * mouseAction.click(we).pause(2000).moveByOffset(1272,
	 * 107).pause(2000).click(); seriesOfActions.build().perform(); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */
	public void doubleclickbyXpath(String xpath) {
		try {
			WebElement we = driver.findElement(By.xpath(xpath));
			Actions mouseAction = new Actions(driver);
			Actions seriesOfActions = mouseAction.doubleClick(we);
			seriesOfActions.build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void escape() {
		try {
			Actions mouseAction = new Actions(driver);
			Actions seriesOfActions = mouseAction.sendKeys(Keys.ESCAPE);
			seriesOfActions.build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void verifystatus(String xpath) {
//    try {
		WebElement element = driver.findElement(By.xpath(xpath));
		strng = element.getText();
		System.out.println(strng);
//  } catch (NoSuchElementException e) {
//   System.out.println("Not able to recognize Webelement " + xpath + " here");
//  } catch (NotFoundException e) {
//   System.out.println("Not able to recognize Webelement " + xpath + " here");
//  } catch (Exception e) {
//   e.printStackTrace();
//  }
	}

	public void gettext(String xpath) {
//	    try {
		WebElement element = driver.findElement(By.xpath(xpath));
		actvalue = element.getText();
//	    } catch (NoSuchElementException e) {
//	   System.out.println("Not able to recognize Webelement " + xpath + " here");
//	  } catch (NotFoundException e) {
//	   System.out.println("Not able to recognize Webelement " + xpath + " here");
//	  } catch (Exception e) {
//	   e.printStackTrace();
//	  }
	}

	public void comparetask(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			actvalue = element.getText();
		} catch (NoSuchElementException e) {
			System.out.println("Not able to recognize Webelement " + xpath + " here");
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + xpath + " here");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gettexttrim(String xpath) {
//	    try {
		WebElement element = driver.findElement(By.xpath(xpath));
		trimtext = element.getText();
		int spos = trimtext.indexOf("(");
		int epos = trimtext.lastIndexOf(")");
		a = trimtext.substring(spos + 1, epos);
		System.out.println("This Part or Product is under " + a);
//	    } catch (NoSuchElementException e) {
//	   System.out.println("Not able to recognize Webelement " + xpath + " here");
//	  } catch (NotFoundException e) {
//	   System.out.println("Not able to recognize Webelement " + xpath + " here");
//	  } catch (Exception e) {
//	   e.printStackTrace();
//	  }
	}

	public void gettitlenumtrim(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			trimtext = element.getText();
			p = trimtext.substring(0, 5);
			System.out.println(p);
		} catch (NoSuchElementException e) {
			System.out.println("Not able to recognize Webelement " + xpath + " here");
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + xpath + " here");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gettextprint(String xpath, String xpath1, String xpath2) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			WebElement element1 = driver.findElement(By.xpath(xpath1));
			WebElement element2 = driver.findElement(By.xpath(xpath2));
			name = element.getAttribute("innerHTML").trim();
			title = element1.getAttribute("innerHTML").trim();
			time = element2.getAttribute("innerHTML").trim();
			System.out.println(name + title + time);
		} catch (NoSuchElementException e) {
			System.out.println("Not able to recognize Webelement " + xpath + " here");
		} catch (NotFoundException e) {
			System.out.println("Not able to recognize Webelement " + xpath + " here");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void assertTextXpath(String xpath) {

		WebElement element1 = driver.findElement(By.xpath(xpath));
		Assert.assertTrue(element1.isDisplayed());

	}

	public void assertTextName(String name) {

		WebElement element3 = driver.findElement(By.name(name));
		// assertTrue(element3.isDisplayed());

	}

	public void assertTextLink(String text) {
		WebElement element2 = driver.findElement(By.linkText(text));
		// assertTrue(element2.isDisplayed());
	}

	public void verifytext(String xpath) {
//    try {
		WebElement element = driver.findElement(By.xpath(xpath));
		refvalue = element.getAttribute("innerHTML").trim();
		// Assert.assertEquals(text, refvalue);
		if ((actvalue.trim()).equalsIgnoreCase(refvalue)) {
			System.out.println("Created By : " + text + " Time : " + refvalue);
			System.out.println("Basic info not updated");
		} else {
			System.out.println("Updated By : " + text + " Time : " + refvalue);
			System.out.println("Basic info updated successfully");
		}

//  } catch (NoSuchElementException e) {
//   System.out.println("Not able to recognize Webelement " + xpath + " here");
//  } catch (NotFoundException e) {
//   System.out.println("Not able to recognize Webelement " + xpath + " here");
//  } catch (Exception e) {
//   e.printStackTrace();
//  }
	}

	public void assertEquals(String xpath, String Value) {
//  try {
		WebElement element = driver.findElement(By.xpath(xpath));
		refvalue = element.getText();
		System.out.println(refvalue);
		Assert.assertEquals(refvalue, Value);

		// Assert.assertEquals(text, refvalue);

	}

	public void partnumber(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			part = element.getText();
			System.out.println(part);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void productnumber(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			product = element.getText();
			System.out.println(product);
//		   Assert.assertEquals("Google Search", strng);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scroll(String scele) {
//		   try {
		// scroll down
		WebElement element = driver.findElement((By.xpath(scele)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
//		   } catch(Exception e){
//			   e.printStackTrace();
//		   }
	}

	public void enter() {
		try {
			Actions mouseAction = new Actions(driver);
			Actions seriesOfActions = mouseAction.sendKeys(Keys.ENTER);
			seriesOfActions.build().perform();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ArrowDown() {
		try {
			Actions mouseAction = new Actions(driver);
			Actions seriesOfActions = mouseAction.sendKeys(Keys.ARROW_DOWN);
			seriesOfActions.build().perform();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<WebElement> FindElements(By xpath) {
		// TODO Auto-generated method stub
		return null;
	}

	public void gettitle(String xpath) {
//	    try {
		WebElement element = driver.findElement(By.xpath(xpath));
		text = element.getAttribute("innerHTML").trim();
		System.out.println("Board Name :  " + text);
//	        } catch (NoSuchElementException e) {
//	       System.out.println("Not able to recognize Webelement " + xpath + " here");
//	      } catch (NotFoundException e) {
//	       System.out.println("Not able to recognize Webelement " + xpath + " here");
//	      } catch (Exception e) {
//	       e.printStackTrace();
//	      }
	}

	public void getnumber(String xpath) {
//	    try {
		WebElement element = driver.findElement(By.xpath(xpath));
		text = element.getAttribute("innerHTML").trim();
		// System.out.println("Board Number : " + text);
//	        } catch (NoSuchElementException e) {
//	       System.out.println("Not able to recognize Webelement " + xpath + " here");
//	      } catch (NotFoundException e) {
//	       System.out.println("Not able to recognize Webelement " + xpath + " here");
//	      } catch (Exception e) {
//	       e.printStackTrace();
//	      }
	}

	@SuppressWarnings("deprecation")
	public void dragAnddropByXpath(String abc, String def) {
//		     try {
		Actions act = new Actions(driver);
		WebElement ele_drag = driver.findElement(By.xpath(abc));
		WebElement ele_drop = driver.findElement(By.xpath(def));
		act.clickAndHold(ele_drag).moveToElement(ele_drop).pause(3000).release(ele_drop).build().perform();
//		     } catch (Exception e) {
//		      // TODO Auto-generated catch block
//		      e.printStackTrace();
//		     }
	}

	@SuppressWarnings("deprecation")
	public void clickandholdByXpath(String a, String b) {
		try {
			WebElement src = driver.findElement(By.xpath(a));
			WebElement des = driver.findElement(By.xpath(b));
			Actions builder = new Actions(driver);
			Actions seriesOfActions = builder.clickAndHold(src).pause(2000).moveToElement(des).pause(5000).release(des)
					.pause(2000);
			seriesOfActions.build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void FilterText(String xpath, String Text) {
		System.out.println("The textvalues is" + Text);
		List<WebElement> filters = driver.findElements(By.xpath(xpath));
		String key = null;
		for (int i = 0; i < filters.size(); i++) {
			key = filters.get(i).getText();
			System.out.println("The Keyvalues is" + key);
			Assert.assertTrue(key.equals(Text));
		}
		Reporter.log("Selected Option from Filter Pop-up are displayed/Filtered Correctly in the Main Page | ");
	}

	public void sortascen(String xpath) {

		ArrayList<String> obtainedList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(xpath));
		for (WebElement we : elementList) {
			obtainedList.add(we.getText());
			System.out.println("Before Sorting, Textvalues of Column header are\t" + obtainedList);

		}
		ArrayList<String> sortedList = new ArrayList<String>();
		for (String s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		System.out.println("After Sorting, Textvalues of Column header are\t" + sortedList);
		Assert.assertTrue(sortedList.equals(obtainedList));
		Reporter.log("Column Header is sorted in Ascending order successfully  | ");
	}

	public void sortdesc(String xpath) {

		// create an List Array and store the products (web elements) into the
		// linkedlist
		List<WebElement> listElement = driver.findElements(By.xpath(xpath));

		// Get the Size of the Webelement
		// listElement.size();
		// System.out.println("Column count is \t" + listElement.size());

		// create an LinkedList
		LinkedList<String> product_names = new LinkedList<String>();
		for (int i = 0; i < listElement.size(); i++) {

			// Store each Webelement Text and print
			String elementText = listElement.get(i).getText();
			// System.out.println("Before Sorting, Textvalues of Column header are \t" +
			// elementText);
			product_names.add(elementText); // Convert string into List<WebElement>
			System.out.println("Before Sorting, Textvalues of Column header are\t" + product_names);
		}
		// sort the list using Collections
		Collections.sort(product_names);
		Collections.reverse(product_names);
		System.out.println("After Sorting, Textvalues of Column header are\t" + product_names);
		// Assert.assertTrue(product_names.equals(elementText));
		// Collections.reverse(sortedList);
		Reporter.log("Column Header is sorted in descending order successfully  | ");
	}

	public static boolean chkalphabetical_order(LinkedList<String> product_names) {

		String previous = ""; // empty string

		for (final String current : product_names) {
			if (current.compareTo(previous) < 0)
				return false;
			previous = current;
		}

		return true;

	}

	public String[][] getlogin() throws IOException {
		// Create an input stream
		FileInputStream fis = new FileInputStream(new File("D:\\eclipse-workspace\\MavenClearwaterProject\\Testdata\\Book1.xlsx"));

		// Open the excel work sheet
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		// Move control to the first sheet
		XSSFSheet sheet = wb.getSheetAt(0);
		int rc = sheet.getLastRowNum();
		int cc = sheet.getRow(0).getLastCellNum();

		String data[][] = new String[rc][cc];

		for (int i = 1; i <= rc; i++) {

			// Move control to the specific row
			XSSFRow firstRow = sheet.getRow(i);

			for (int j = 0; j < cc; j++) {
				data[i - 1][j] = firstRow.getCell(j).getStringCellValue();
//				System.out.println(data[i - 1][j]);
			}
		}

		wb.close();
		fis.close();

		return data;
	}

	public String[][] getloginStage() throws IOException {
		// Create an input stream
		FileInputStream fis = new FileInputStream(new File(".\\Testdata\\Sample.xlsx"));

		// Open the excel work sheet
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		// Move control to the first sheet
		XSSFSheet sheet = wb.getSheetAt(0);
		int rc = sheet.getLastRowNum();
		int cc = sheet.getRow(0).getLastCellNum();

		String data[][] = new String[rc][cc];

		for (int i = 1; i <= rc; i++) {

			// Move control to the specific row
			XSSFRow firstRow = sheet.getRow(i);

			for (int j = 0; j < cc; j++) {
				data[i - 1][j] = firstRow.getCell(j).getStringCellValue();
//			System.out.println(data[i - 1][j]);
			}
		}

		wb.close();
		fis.close();

		return data;
	}
}
