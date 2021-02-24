package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DefaultLandingPage extends BasePage {

    public DefaultLandingPage(WebDriver driver)
    {
        super(driver);
    }

    public By GetSignInButtonLocator()
    {
       return By.id("nav-link-accountList");
    }

    public void open()
    {
        driver.get("https://www.amazon.com/");
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(GetSignInButtonLocator())).isDisplayed();
    }

    public SignInPage SignIn()
    {
        driver.findElement(GetSignInButtonLocator()).click();
        SignInPage signInPage= new SignInPage(driver);
        if(signInPage.isLoaded())
        {
           return signInPage;
        }
        else
        {
            throw new ElementNotVisibleException("UserName text box not found");
        }
    }
}
