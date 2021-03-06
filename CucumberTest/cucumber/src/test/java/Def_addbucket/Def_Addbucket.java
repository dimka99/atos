
package Def_addbucket;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class Def_Addbucket {
	WebDriver driver;
	
	@Given("^click on catalog menu$")
	public void click_on_catalog_menu() throws Throwable {
		driver=Utils.CommonScripts.DoLogin();
		PageObjects.LandingPage.lbl_ManageStacks(driver).click();
		Thread.sleep(2000);
	    PageObjects.ManageStacksPage.menu_catalog(driver).click();
	    Thread.sleep(2000);
		
	}

	@Given("^Opens Bucket Resource$")
	public void opens_Bucket_Resource() throws Throwable {
		
	    PageObjects.CatalogPage.link_bucketresource(driver).click();
	}

	@Given("^Addning new bucket$")
	public void addning_new_bucket() throws Throwable {
		
	   try {
		WebDriverWait wait=new WebDriverWait(driver, 300);
		   wait.until(ExpectedConditions.elementToBeClickable(PageObjects.BucketResourcePage.cmb_cloudaccount(driver)));
		   wait.until(ExpectedConditions.elementToBeClickable(PageObjects.BucketResourcePage.txt_bucketname(driver)));
		   wait.until(ExpectedConditions.invisibilityOf(PageObjects.BucketResourcePage.div_loading(driver)));
		   JavascriptExecutor js = (JavascriptExecutor) driver; 
		   js.executeScript("arguments[0].click();", PageObjects.BucketResourcePage.link_cloudaccount(driver));
		   wait.until(ExpectedConditions.invisibilityOf(PageObjects.BucketResourcePage.div_loading(driver)));
		   Select Location = new Select(PageObjects.BucketResourcePage.cmb_location(driver));
		   Location.selectByVisibleText("AWS Datacenter - eu-west-1");
		   wait.until(ExpectedConditions.invisibilityOf(PageObjects.BucketResourcePage.div_loading(driver)));
		   PageObjects.BucketResourcePage.txt_stackname(driver).sendKeys("addbuckettest-"+Utils.CommonScripts.GetDateTime());
		   wait.until(ExpectedConditions.invisibilityOf(PageObjects.BucketResourcePage.div_loading(driver)));
		   js.executeScript("arguments[0].click();", PageObjects.BucketResourcePage.cmbval_usergroup(driver));
		   wait.until(ExpectedConditions.invisibilityOf(PageObjects.BucketResourcePage.div_loading(driver)));
		   PageObjects.BucketResourcePage.txt_bucketname(driver).sendKeys("addbuckettest-"+Utils.CommonScripts.GetDateTime());
		   wait.until(ExpectedConditions.invisibilityOf(PageObjects.BucketResourcePage.div_loading(driver)));
		   js.executeScript("arguments[0].click();",PageObjects.BucketResourcePage.btn_bucketsubmit(driver));
		   wait.until(ExpectedConditions.invisibilityOf(PageObjects.BucketResourcePage.div_loading(driver)));
		   wait.until(ExpectedConditions.visibilityOf(PageObjects.ActivitiesPage.img_stackgreentick(driver)));
		   
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	}
	   
	   
	}

	@Given("^Check the Status and print message$")
	public void check_the_Status_and_print_message() throws Throwable {
		
	 
			try {
				WebDriverWait wait=new WebDriverWait(driver, 300);
				Thread.sleep(3000);
				wait.until(ExpectedConditions.invisibilityOf(PageObjects.LandingPage.div_HeadLoader(driver)));
				
				
				//wait.until(ExpectedConditions.textToBePresentInElement(PageObjects.ActivitiesPage.lbl_stackmessage(driver), "response"));
				wait.until(ExpectedConditions.textToBePresentInElement(PageObjects.ActivitiesPage.lbl_requestmessages(driver), "details"));
				Thread.sleep(3000);
				String RequestStatusMessage=PageObjects.ActivitiesPage.lbl_requestmessage(driver).getText();
				String StackStatusMessage=PageObjects.ActivitiesPage.lbl_stackmessage(driver).getText();
				
				
				if (RequestStatusMessage.contains("Failed") || StackStatusMessage.contains("Error"))
				{
					System.out.println("Operation failed");
					System.out.println(StackStatusMessage);
					
				}
				else if (RequestStatusMessage.contains("Success")  && StackStatusMessage.contains("Completed"))
				{
					System.out.println("Bucket Created ");
					
				}
				else
				{
					System.out.println("Operation failed");
					System.out.println(StackStatusMessage);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
			//e.printStackTrace();
			}
	    
	}
	
	@Given("^close the browser$")
	public void close_the_browser() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.quit();
	}

	@Given("^Print test finished$")
	public void print_test_finished() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Test Completed");
	}


}
