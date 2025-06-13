package stellarburgers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import stellarburgers.config.BaseApi;
import stellarburgers.pages.*;
import stellarburgers.user.User;
import stellarburgers.user.UserClient;
import stellarburgers.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected ForgotPasswordPage forgotPasswordPage;


    @Parameterized.Parameter
    public String browserType;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() {
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