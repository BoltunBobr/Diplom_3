package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    // Кнопка "Войти в аккаунт"
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    // Кнопка "Личный кабинет"
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");

    // Разделы конструктора
    private final By bunsSection = By.xpath("//span[text()='Булки']/..");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/..");
    private final By fillingsSection = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Начинки']");

    // Индикатор активного раздела
    private final By activeBunsSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[text()='Булки']");
    private final By activeSaucesSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[text()='Соусы']");
    private final By activeFillingsSection = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Начинки']");

    // Индикатор загрузки
    private final By loadingIndicator = By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
       driver.findElement(loginButton).click();
        waitForLoadingIndicatorToDisappear();
    }

    @Step("Кликнуть кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
        waitForLoadingIndicatorToDisappear();
    }

    @Step("Кликнуть раздел 'Булки'")
    public void clickBunsSection() {
        driver.findElement(bunsSection).click();
    }

    @Step("Кликнуть раздел 'Соусы'")
    public void clickSaucesSection() {
        driver.findElement(saucesSection).click();
    }

    @Step("Кликнуть раздел 'Начинки'")
    public void clickFillingsSection() {
        driver.findElement(fillingsSection).click();
    }

    @Step("Проверить активность раздела 'Булки'")
    public boolean isBunsSectionActive() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeBunsSection));
        return element.isDisplayed();
    }

    @Step("Проверить активность раздела 'Соусы'")
    public boolean isSaucesSectionActive() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeSaucesSection));
        return element.isDisplayed();
    }

    @Step("Проверить активность раздела 'Начинки'")
    public boolean isFillingsSectionActive() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(activeFillingsSection));
        return element.isDisplayed();
    }

    @Step("Проверить, что пользователь авторизован")
    public boolean isUserLoggedIn() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        return element.isDisplayed();
    }

    @Step("Ожидание исчезновения индикатора загрузки")
    public void waitForLoadingIndicatorToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(loadingIndicator)));
    }
}