package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
    private WebDriver driver;

    // Поле ввода почты
    private final By emailField = By.xpath("//input[@name='name']");

    // Поле ввода пароля
    private final By passwordField = By.xpath("//input[@name='Пароль']");

    // Кнопка войти
    private final By loginButton = By.xpath("//button[text()='Войти']");

    // Ссылка на регистрацию
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");

    // Ссылка на восстановление пароля
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие кнопки Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие ссылки Регистрация")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Авторизация")
    public void authorization(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Нажатие ссылки Восстановление пароля")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    @Step("Проверка отображения страницы")
    public boolean isDisplayed() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return element.isDisplayed();
    }
}
