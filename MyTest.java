import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyTest {
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jsx;
	
	//Categories
	By AccountAndIdentity = By.xpath("//*[@id=\"accordion__heading-accountinfo\"]");
	By NetworkAndCustomerActivity = By.xpath("//*[@id=\"accordion__heading-networkactivity\"]");
	By ThirdPartyInsights = By.xpath("//*[@id=\"accordion__heading-collectedinferences\"]");
	By AddOns = By.xpath("//*[@id=\"accordion__heading-addons\"]");
	
	By ExpandedAccountAndIdentity = By.xpath("//*[@id=\"accordion__panel-accountinfo\"]");
	By ExpandedNetworkAndCustomerActivity = By.xpath("//*[@id=\"accordion__panel-networkactivity\"]");
	By ExpandedThirdPartyInsights = By.xpath("//*[@id=\"accordion__panel-collectedinferences\"]");
	By ExpandedAddOns = By.xpath("//*[@id=\"accordion__panel-addons\"]");;
	
	@Test
	public void test() throws InterruptedException{
		login();
		vPdLand();
		terminate();
	}
	
	public void login() {
		int timeOutInSeconds = 120; //2 minutes
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, timeOutInSeconds);
		jsx = (JavascriptExecutor) driver;
		driver.get("https://www98.verizon.com/privacy/your-data");
		
		WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gnavAccountMenu\"]")));
		
		Actions action = new Actions(driver);
		action.moveToElement(we).build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() =\"My account\"]"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login\"]"))).sendKeys("sankaso");
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("Welcome19$");
		driver.findElement(By.xpath("//*[@id=\"Log_On\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"IDToken1\"]"))).sendKeys("8312623289");
		driver.findElement(By.xpath("//*[@id=\"IDToken2\"]")).sendKeys("abcd1234");
		driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
	}
	
	public void vPdLand() throws InterruptedException {
		List<String> subCategoryAccountAndIdentity = new ArrayList<String>();
		subCategoryAccountAndIdentity.add("Contact Information and Personal Identifiers");
		subCategoryAccountAndIdentity.add("Government identifiers");
		subCategoryAccountAndIdentity.add("Line Information");
		
		List<String> subCategoryAddOns = new ArrayList<String>();
		subCategoryAddOns.add("Verizon Cloud");

//		jsx.executeScript("window.scrollBy(0,100)");
		//Expand Account and Identity Category
		wait.until(ExpectedConditions.visibilityOfElementLocated(AccountAndIdentity)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ExpandedAccountAndIdentity));
			System.out.println("Test Pass: Account and Identity Category expanded");
		}catch(Exception e) {
			Assert.fail("Test Fail: Account and Identity Category does not expand");
		}
		
		//Assert Sub-category titles of Account and Identity Category
		for (int i=0; i<subCategoryAccountAndIdentity.size();i++) {
			String subCategory = subCategoryAccountAndIdentity.get(i);
			By xpath = SubCategoryXpath(subCategory);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
				System.out.println("Test Pass: Sub Category \"" + subCategory + "\" is available");
			}catch (Exception e) {
				Assert.fail("Test Fail: Sub Category \""+ subCategory + "\" is not available");
			}
		}
		
		//Expand Sub-category of Account and Identity Category
		for (int i=0; i<subCategoryAccountAndIdentity.size();i++) {
			String subCategory = subCategoryAccountAndIdentity.get(i);
			By xpath = SubCategoryXpath(subCategory);
			
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).click();
			String bool_expand = driver.findElement(xpath).getAttribute("aria-expanded");
			Assert.assertEquals("Test Fail: Sub Category \""+ subCategory + "\" does not expand", "true", bool_expand);
			System.out.println("Test Pass: Sub Category \"" + subCategory + "\" expanded");
		}	
		
		//Expand Network and customer activity
		wait.until(ExpectedConditions.visibilityOfElementLocated(NetworkAndCustomerActivity)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ExpandedNetworkAndCustomerActivity));
			System.out.println("Test Pass: Network And Customer Activity Category expanded");
		}catch(Exception e) {
			Assert.fail("Test Fail: Network And Customer Activity Category does not expand");
		}
		
		//Expand Third Party Insights
		wait.until(ExpectedConditions.visibilityOfElementLocated(ThirdPartyInsights)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ExpandedThirdPartyInsights));
			System.out.println("Test Pass: Third Party Insights Category expanded");
		}catch(Exception e) {
			Assert.fail("Test Fail: Third Party Insights Category does not expand");
		}
		
		//Expand Add-Ons
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddOns)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ExpandedAddOns));
			System.out.println("Test Pass: Add-Ons Category expanded");
		}catch(Exception e) {
			Assert.fail("Test Fail: Add-Ons Category does not expand");
		}
		
		//Assert Sub-category titles of Add-Ons
		for (int i=0; i<subCategoryAddOns.size();i++) {
			String subCategory = subCategoryAddOns.get(i);
			By xpath = SubCategoryXpath(subCategory);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
				System.out.println("Test Pass: Sub Category \"" + subCategory + "\" is available");
			}catch (Exception e) {
				Assert.fail("Test Fail: Sub Category \""+ subCategory + "\" is not available");
			}
		}
		
		//Expand Sub-category of Add-Ons
		for (int i=0; i<subCategoryAddOns.size();i++) {
			String subCategory = subCategoryAddOns.get(i);
			By xpath = SubCategoryXpath(subCategory);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).click();
			String bool_expand = driver.findElement(xpath).getAttribute("aria-expanded");
			Assert.assertEquals("Test Fail: Sub Category \""+ subCategory + "\" does not expand", "true", bool_expand);
			System.out.println("Test Pass: Sub Category \"" + subCategory + "\" expanded");
		}
	}

	public By SubCategoryXpath(String subcategory) {
		return By.xpath("//*[text() = \""+subcategory+"\"]//parent::*[@class=\"sub_accordion_button\"]");
	}

	public void terminate() {
		driver.close();
	}

}
