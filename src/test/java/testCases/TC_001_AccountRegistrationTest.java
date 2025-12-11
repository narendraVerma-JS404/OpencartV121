package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("********Starting the Execution**********");
		try {
		HomePage hp = new HomePage(driver);
		

		logger.info("********Clicked on my account link**********");
		hp.clickMyAccount();
		logger.info("********Clicked on my register link**********");
		hp.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// Randomly generated email id String
		regpage.setTelephone(randomeNumber());
		
		//String password = randomAlphanumeric();
		String password = randomeAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("********Validating expected message**********");
		String confmsg = regpage.getConfirmationMsg();
		
		if (confmsg.equals("Your Account Has Been Created!"))
		{
		Assert.assertTrue(true);	
		}
		
		else
		{
			logger.error("Test failed");
			logger.debug("debug provide");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		
	}
		catch(Exception e){
			
			Assert.fail();
		}
	
		logger.info("********Finished test case**********");
}
}
