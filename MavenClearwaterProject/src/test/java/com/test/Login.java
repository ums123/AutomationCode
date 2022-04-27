package com.test;

import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.*;

public class Login extends WrapperClass {

	            @BeforeSuite(alwaysRun = true)
				public void login() throws Throwable {
				String[][] data = getlogin();
				String emailId= data[0][0];
				String password= data[0][1];
				browser = data [0][2];
				String Url = data [0][3];
				
				//LaunchBrowser 		
				launchBroswer(browser,Url);
				Reporter.log("Broser Launched successfully | ");
				Thread.sleep(5000);
				Thread.sleep(5000);
				Thread.sleep(5000);
							    
				//Enter EmailID in Textbox
				assertTextXpath("//input[@id='lEmail']");
				sendvaluebyxpath("//input[@id='lEmail']", emailId);
				Reporter.log("Enter EmailID successfully | ");
				Thread.sleep(3000);
				
				//Enter Password in Textbox
				assertTextXpath("//input[@id='lPass']");
				sendvaluebyxpath("//input[@id='lPass']", password);
				Reporter.log("Enter Password successfully | ");
				Thread.sleep(3000);
			
				//Click on SignIN button
				try {
				assertTextXpath("//a[@id='signIn']");
				clickByXpath("//a[@id='signIn']");  
				Reporter.log("Click on SignIN button successfully | ");
				}catch(Exception e)
				{
					   e.printStackTrace();
					  Reporter.log("SignIN doesn't work | ");
					}
				Thread.sleep(3000);
				Thread.sleep(3000);
				//Thread.sleep(5000);
				//Thread.sleep(5000);

  }

	            @Test(priority=1, enabled = true)
	            public void NavigatePage_AssetInventoryList() throws IOException, InterruptedException {
	          	  
	          	  Thread.sleep(3000);
	          	  Thread.sleep(3000);
	          	System.out.println("Test fine");
				
	          	  
	            }


				@AfterSuite
				public void cleanupSuite() throws InterruptedException, IOException {
					System.out.println("After suite---->Works fine");
					
						   
						   Thread.sleep(3000);
						  						 
						 //Click on Account drop-down
						   assertTextXpath("//header/div[2]/div[1]/ul[2]/li[6]/a[1]/span[1]");
						   clickByXpath("//header/div[2]/div[1]/ul[2]/li[6]/a[1]/span[1]");
						   Reporter.log("Click on Account drop-down  | ");
						   Thread.sleep(5000);
							 
						   //click on Logout button
						   try {
						   assertTextXpath("//header/div[2]/div[1]/ul[2]/li[6]/ul[1]/li[3]/a[1]/i[1]");
						   clickByXpath("//header/div[2]/div[1]/ul[2]/li[6]/ul[1]/li[3]/a[1]/i[1]");
						   Reporter.log("Click on Logout button  | ");
						   }catch(Exception e)
							{
								   e.printStackTrace();
								  Reporter.log("SignIN doesn't work | ");
								}
						   Thread.sleep(5000);
						   quitBrowser();
						   Reporter.log("Browser Quit | ");
						   Thread.sleep(5000);
					 }

}
