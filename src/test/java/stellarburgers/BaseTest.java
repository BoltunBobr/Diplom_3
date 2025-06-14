package stellarburgers;

import org.junit.After;
import org.junit.Before;
import stellarburgers.config.BaseApi;
import stellarburgers.pages.*;
import stellarburgers.utils.ConfigReader;
import stellarburgers.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;



public abstract class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected ForgotPasswordPage forgotPasswordPage;


    @Before
    public void setUp() {
        String browserType = System.getProperty("browser", ConfigReader.getProperty("browser"));
        driver = WebDriverFactory.createDriver(browserType);
        driver.manage().window().maximize();
        driver.get(BaseApi.BASE_URI);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}