package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends BasePage {

    String userName = "veeva.user01@gmail.com";
    String password = "veevapassword";

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public By GetUserNameLocator()
    {
        return By.id("ap_email");
    }

    public By GetContinueLocator()
    {
        return By.id("continue");
    }

    public By GetPasswordLocator()
    {
        return By.id("ap_password");
    }

    public By GetSubmitButtonLocator()
    {
        return By.id("signInSubmit");
    }

    public boolean isLoaded() {
         if(wait.until(ExpectedConditions.presenceOfElementLocated(GetUserNameLocator())).isDisplayed())
         {
             return true;
         }
         else
         {
             throw new ElementNotVisibleException("UserName text box not found");
         }
    }

    public HomePage enterCredentialsAndSubmit()
    {
        driver.findElement(GetUserNameLocator()).sendKeys(userName);
        driver.findElement(GetContinueLocator()).click();
        if(wait.until(ExpectedConditions.presenceOfElementLocated(GetPasswordLocator())).isDisplayed())
        {
            driver.findElement(GetPasswordLocator()).sendKeys(password);
            driver.findElement(GetSubmitButtonLocator()).click();
            HomePage homePage = new HomePage(driver);
            if (homePage.isLoaded())
            {
                return homePage;
            }
            else
            {
                throw new ElementNotVisibleException("Search box not found");
            }
        }
        else
        {
            throw new ElementNotVisibleException("Password text box not found");
        }
    }
}
