package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;


@DisplayName("Ozon tests")
public class OzonTests extends TestBase {

    final String basketFeatureName = "Basket";
    final String changeQuantityStoryName = "Change quantity in basket";

    @DisplayName("Check elements on Main Page")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Main Page")
    @Story("Open main page")
    @Test
    @baseAnnotation
    void checkMainElementsTest() {
        mainPage.openPage().checkElements();
    }

    @DisplayName("Search Test:")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Search")
    @Story("Search for goods by name")
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {
            "Twix",
            "Робот пылесос Redmond"})
    @baseAnnotation
    void searchTest(String param){
        mainPage.openPage().search(param).checkSearchResults();
    }

    @DisplayName("Go to catalog section Test:")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Catalog")
    @Story("Go to catalog section")
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {
            "Одежда",
            "Красота и здоровье",
            "For failing test"})
    @baseAnnotation
    void catalogTest(String param){
        mainPage.openPage().goToSection(param);
    }

    @DisplayName("Add item from main page to basket Test")
    @Severity(SeverityLevel.CRITICAL)
    @Feature(basketFeatureName)
    @Story("Add to basket")
    @Test
    @baseAnnotation
    void addToBasketTest(){
        mainPage.openPage().addToBasket().checkQuantityInBasket(1);
    }

    @DisplayName("Increase quantity of item in basket Test")
    @Severity(SeverityLevel.NORMAL)
    @Feature(basketFeatureName)
    @Story(changeQuantityStoryName)
    @Test
    @baseAnnotation
    void increaseQuantityInBasketTest(){
        mainPage.openPage().addToBasket().increaseQuantityInBasket().checkQuantityInBasket(2);
    }

    @DisplayName("Decrease quantity of item in basket Test")
    @Severity(SeverityLevel.NORMAL)
    @Feature(basketFeatureName)
    @Story(changeQuantityStoryName)
    @Test
    @baseAnnotation
    void decreaseQuantityInBasketTest(){
        mainPage.openPage().addToBasket().increaseQuantityInBasket().increaseQuantityInBasket()
                .decreaseQuantityInBasket().checkQuantityInBasket(2);
    }

    @DisplayName("Increase quantity of item in basket several times Test:")
    @Severity(SeverityLevel.NORMAL)
    @Feature(basketFeatureName)
    @Story(changeQuantityStoryName)
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {
            7,
            })
    @baseAnnotation
    void increaseQuantityInBasketSeveralTimesTest(int param) throws Exception {
        mainPage.openPage().addToBasket();
        step("Click + " + param + " times", () -> {
            for (int i = 0; i < param; i++){
                TimeUnit.SECONDS.sleep(1);
                mainPage.increaseQuantityInBasket();
            }
        });
        mainPage.checkQuantityInBasket(param + 1);
    }
}
