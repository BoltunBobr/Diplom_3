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

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

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

        userClient.createNewUser(new User(name,email, password))
                .then()
                .log().ifValidationFails()
                .statusCode(SC_OK);

    }

    @Test
    @DisplayName("Test Login From Main Page Button")
    @Description("Проверяем авторизацию с главной страницы")
    public void testLoginFromMainPageButton() {
        mainPage.clickLoginButton();
        loginPage.authorization(email, password);

        boolean isLoggedIn = mainPage.isUserLoggedIn();
        assertTrue("Пользователь должен быть авторизован после входа через главную страницу", isLoggedIn);
    }

    @Test
    @DisplayName("Test Login From Personal Account Button")
    @Description("Проверяем авторизацию с личного кабинета")
    public void testLoginFromPersonalAccountButton() {
        mainPage.clickPersonalAccountButton();
        loginPage.authorization(email, password);

        boolean isLoggedIn = mainPage.isUserLoggedIn();
        assertTrue("Пользователь должен быть авторизован после входа через личный кабинет", isLoggedIn);
    }

    @Test
    @DisplayName("Test Login From Registration Page")
    @Description("Проверяем авторизацию со страницы регистрации")
    public void testLoginFromRegistrationPage() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registrationPage.clickOnLoginLink();
        loginPage.authorization(email, password);

        boolean isLoggedIn = mainPage.isUserLoggedIn();
        assertTrue("Пользователь должен быть авторизован после перехода со страницы регистрации", isLoggedIn);
    }

    @Test
    @DisplayName("Test Login From Forgot Password Page")
    @Description("Проверяем авторизацию со страницы восстановления пароля")
    public void testLoginFromForgotPasswordPage() {
        mainPage.clickLoginButton();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();
        loginPage.authorization(email, password);

        boolean isLoggedIn = mainPage.isUserLoggedIn();
        assertTrue("Пользователь должен быть авторизован после перехода со страницы восстановления пароля", isLoggedIn);
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
