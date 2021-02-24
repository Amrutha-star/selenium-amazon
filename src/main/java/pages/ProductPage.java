package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver)
    {
        super(driver);
    }

    public By GetAddtoCartButtonLocator()
    {
        return By.id("add-to-cart-button");
    }

    public By GetSuggestionCloseLocator()
    {
        return By.xpath("//*[@aria-label='Close']//i");
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(GetAddtoCartButtonLocator())).isDisplayed();
    }

    public ShoppingCartPage AddToCart()
    {
        WebElement element = driver.findElement(GetAddtoCartButtonLocator());
        element.click();

        if(wait.until(ExpectedConditions.presenceOfElementLocated(GetSuggestionCloseLocator())).isDisplayed())
        {
            new Actions(driver).moveToElement(driver.findElement(GetSuggestionCloseLocator())).moveToElement(driver.findElement(GetSuggestionCloseLocator()));
            driver.findElement(GetSuggestionCloseLocator()).click();
        }
        else
        {
            throw new ElementNotVisibleException("Suggestion close Locator not found");
        }

        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        if (cartPage.isLoaded())
        {
            return cartPage;
        }
        else
        {
            throw new ElementNotVisibleException("Proceed to Checkout Locator not found");
        }

    }
}
