package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayName("Ozon tests")
public class OzonTests extends TestBase {

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

}
