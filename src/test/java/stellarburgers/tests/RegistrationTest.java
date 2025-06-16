package stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.BaseTest;
import stellarburgers.user.User;
import stellarburgers.user.UserClient;

import static org.junit.Assert.*;


public class RegistrationTest extends BaseTest {

    private String name;
    private String email;
    private String password;
    private User user;
    private UserClient userClient;

    @Before
    public void setUpTestData() {
        // Генерация тестовых данных
        name = RandomStringUtils.randomAlphanumeric(5, 20);
        password = RandomStringUtils.randomAlphanumeric(8, 20);
        email = RandomStringUtils.randomAlphanumeric(4, 28).toLowerCase() + "@yandex.ru";
        user = new User(email, password);
        userClient = new UserClient();
    }


    @Test
    @DisplayName("Test Successful Registration")
    @Description("Успешная регистрация")
    public void testSuccessfulRegistration() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        registrationPage.registration(name, email, password);


        assertTrue("Пользователь должен быть перенаправлен на страницу входа",
                loginPage.isDisplayed());
    }

    @Test
    @DisplayName("Test Invalid Password Registration")
    @Description("Тест регистрации с некорректным паролем")
    public void testInvalidPasswordRegistration() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        password = "short";

        registrationPage.registration(name, email, password);

        assertEquals("Некорректный пароль", driver.findElement(registrationPage.errorPasswordText).getText());
    }

    @After
    public void cleanUp() {
        // Удаление пользователя после теста
        String accessToken = userClient.checkLoginExistingUser(user)
                .then()
                .extract()
                .path("accessToken");

        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }
}