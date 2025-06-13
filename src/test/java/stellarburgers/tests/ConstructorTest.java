package stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.BaseTest;

import static org.junit.Assert.assertTrue;


public class ConstructorTest extends BaseTest {
    @Test
    @Description("Test Buns Section")
    @DisplayName("Проверка перехода к разделу 'Булки'")
    public void testBunsSection() {
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();

        assertTrue("Раздел 'Булки' должен быть активен", mainPage.isBunsSectionActive());
    }

    @Test
    @Description("Test Sauces Section")
    @DisplayName("Проверка перехода к разделу 'Соусы'")
    public void testSaucesSection() {
        mainPage.clickSaucesSection();

        assertTrue("Раздел 'Соусы' должен быть активен", mainPage.isSaucesSectionActive());
    }

    @Test
    @Description("Test Filling Section")
    @DisplayName("Проверка перехода к разделу 'Начинки'")
    public void testFillingsSection() {
        mainPage.clickFillingsSection();

        assertTrue("Раздел 'Начинки' должен быть активен", mainPage.isFillingsSectionActive());
    }
}
