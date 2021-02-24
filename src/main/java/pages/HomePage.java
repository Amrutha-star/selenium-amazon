package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public By GetDepartmentLocator()
    {
        return By.id("searchDropdownBox");
    }

    public By GetSearchBoxLocator()
    {
        return By.id("twotabsearchtextbox");
    }

    public By GetSearchButton()
    {
        return By.id("nav-search-submit-button");
    }

    public By GetFreeShippingCheckbox()
    {
        return By.xpath("//*[@aria-label='Free Shipping by Amazon']");
    }

    public By GetClearLinkforFreeShipping()
    {
        return By.xpath("//*[@aria-label='Free Shipping by Amazon']/preceding-sibling::li//a");
    }
    public By GetBrand(String brand)
    {
        return By.xpath("//*[text()='"+brand+"']");
    }

    public By GetClearLinkforBrand()
    {
        return By.xpath("//*[text()='Brand']/../following-sibling::ul//*[text()='Clear']");
    }

    public By GetMinPriceLocator()
    {
       return By.id("low-price");
    }

    public By GetMaxPriceLocator()
    {
        return By.id("high-price");
    }

    public By GetSubmitPriceLocator()
    {
        return By.xpath("//*[@id='priceRefinements']//*[@type='submit']");
    }

    public By GetClearLinkforPriceRange()
    {
        return By.xpath("//*[@id='priceRefinements']//a[contains(@class, 'clear-link')]");
    }

    public By GetFirstSearchResultLocator()
    {
        return By.xpath("//*[@data-index=1 and @data-component-type='s-search-result']//img");
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(GetSearchBoxLocator())).isDisplayed();
    }

    public void chooseDepartment(String department) {
        new Select(driver.findElement(GetDepartmentLocator())).selectByVisibleText(department);
    }

    public void enterTextAndSearch(String product) {
        driver.findElement(GetSearchBoxLocator()).sendKeys(product);
        driver.findElement(GetSearchButton()).click();
        if(wait.until(ExpectedConditions.presenceOfElementLocated(GetFreeShippingCheckbox())).isDisplayed())
        {

        }
        else
        {
            throw new ElementNotVisibleException("Free Shipping checkbox not found. Or No search results");
        }
    }

    public void selectFreeShipping()
    {
            driver.findElement(GetFreeShippingCheckbox()).click();
        if(wait.until(ExpectedConditions.presenceOfElementLocated(GetClearLinkforFreeShipping())).isDisplayed())
        {

        }
        else
        {
            throw new ElementNotVisibleException("Clear link not found. Or Checkbox not checked");
        }
    }

    public void selectBrand(String brand)
    {
        WebElement element = driver.findElement(GetBrand(brand));
        new Actions(driver).moveToElement(element).click(element).perform();

        if(wait.until(ExpectedConditions.presenceOfElementLocated(GetClearLinkforBrand())).isDisplayed())
        {

        }
        else
        {
            throw new ElementNotVisibleException("Clear link not found. Or Checkbox not checked");
        }
    }

    public void selectPriceRange(String min, String max)
    {
        driver.findElement(GetMinPriceLocator()).sendKeys(min);
        driver.findElement(GetMaxPriceLocator()).sendKeys(max);
        driver.findElement(GetSubmitPriceLocator()).click();
        if(wait.until(ExpectedConditions.presenceOfElementLocated(GetClearLinkforPriceRange())).isDisplayed())
        {

        }
        else
        {
            throw new ElementNotVisibleException("Price Range was not submitted");
        }
    }

    public ProductPage pickFirstSearchResult()
    {
        driver.findElement(GetFirstSearchResultLocator()).click();
        ProductPage productPage = new ProductPage(driver);
        if (productPage.isLoaded())
        {
            return productPage;
        }
        else
        {
            throw new ElementNotVisibleException("'Add to Cart' button not found");
        }
    }
}
