# Тестирование веб-приложения Stellar Burgers

## О проекте
Автоматизированные тесты для веб-приложения Stellar Burgers (https://stellarburgers.nomoreparties.site/). Тестирование проводится в Google Chrome и Яндекс.Браузере с генерацией Allure-отчетов.

## Технологический стек
- Java 11
- Selenium WebDriver
- JUnit 4
- Allure Framework
- Maven
- ChromeDriver и YandexDriver

## Структура проекта

### Page Objects
src/main/java/stellarburgers/pages/  
├── MainPage.java - Главная страница  
├── LoginPage.java - Страница входа  
├── RegistrationPage.java - Страница регистрации  
└── ForgotPasswordPage.java - Страница восстановления пароля  

### Тесты
src/test/java/stellarburgers/tests/  
├── RegistrationTest.java - Тесты регистрации  
├── LoginTest.java - Тесты авторизации  
└── ConstructorTest.java - Тесты раздела "Конструктор"
