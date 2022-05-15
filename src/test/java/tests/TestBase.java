package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.DriverSettings;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.MainPage;

import java.lang.annotation.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.AllureAttachments.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestBase {

    final static String owner = "Mariya Dolgopolova";

    MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUp() {
        DriverSettings.configure();
        //Configuration.holdBrowserOpen = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    @Step("Check logs, add attachments, close webdriver")
    void afterEach(){
        addAttachments();
        consoleShouldNotHaveErrors();
        closeWebDriver();
    }

    @Step("Add attachments")
    void addAttachments(){
        attachScreenshot();
        attachPageSource();
        attachBrowserConsoleLogs();
    }

    @Step("Check that console log contains no errors")
    public void consoleShouldNotHaveErrors(){
        String consoleLogs = getConsoleLogs();
        String errorText = "SEVERE";

        assertThat(consoleLogs).doesNotContain(errorText);
    }

    @Documented
    @Owner(owner)
    @Target({ ElementType.TYPE, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    @Link(value = "ozon", url = "https://www.ozon.ru")
    public @interface baseAnnotation { }
}
