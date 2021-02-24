import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class ProductPurchaseFlowTest
{
    HomePage homePage;

    @BeforeClass
    public static void setUp()
    {
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void login()
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        DefaultLandingPage landingPage = new DefaultLandingPage(driver);
        landingPage.open();
        if(landingPage.isLoaded())
        {
            SignInPage signInPage = landingPage.SignIn();

            homePage = signInPage.enterCredentialsAndSubmit();
        }
        else
        {
            throw new ElementNotVisibleException("SignIn button not found");
        }
    }

    @Test
    public void checkoutProduct()
    {
        homePage.chooseDepartment("Electronics");
        homePage.enterTextAndSearch("Laptop");
        homePage.selectPriceRange("250","500");
        ProductPage productPage = homePage.pickFirstSearchResult();
        ShoppingCartPage cartpage = productPage.AddToCart();
        cartpage.ProceedToCheckout();
    }
}