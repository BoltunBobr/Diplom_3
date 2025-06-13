package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegistrationPage {
    private WebDriver driver;

    // Поле "Имя"
    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    // Поле "Email"
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Поле "Пароль"
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
    // Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // Ссылка "Войти"
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    // Текст "Некорректный пароль"
    public final By errorPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    // Текст заголовка "Регистрация" для проверки перехода на страницу регистрации
    public final By registerText = By.xpath(".//div/h2[text()='Регистрация']");
    // Анимация загрузки
    public final By loadingAnimation = By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Нажатие на ссылку 'Войти'")
    public void clickOnLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Регистрация пользователя")
    public void registration(String name, String email, String password) {
        waitForLoadRegisterPage();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickOnRegisterButton();
    }

    @Step("Выставлено ожидание загрузки страницы регистрации через текст 'Регистрация'.")
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(registerText));
    }

    @Step("Выставлено ожидание загрузки страницы полностью, анимация исчезнет.")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(loadingAnimation));
    }
}
