package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver)
    {
        super(driver);
    }

    public By GetProccedToCheckoutLocator()
    {
        return By.partialLinkText("Proceed to checkout");
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(GetProccedToCheckoutLocator())).isDisplayed();
    }

    public void ProceedToCheckout()
    {
        driver.findElement(GetProccedToCheckoutLocator()).click();
    }
}
