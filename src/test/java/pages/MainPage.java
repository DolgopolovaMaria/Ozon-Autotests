package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class MainPage {

    private final SelenideElement
        logo = $("[alt=Ozon]"),
        searchInput = $("input[placeholder='Искать на Ozon']"),
        catalogButton = $(byText("Каталог")).closest("button"),
        searchButton = $("[aria-label=Поиск]").parent(),
        ordersLink = $$(".kc1").find(text("Заказы")),
        basketLink = $$(".kc1").find(text("Корзина")),
        favoritesLink = $$(".kc1").find(text("Избранное")),
        loginLink = $$(".kc1").find(text("Войти")),
        sectionHeader = $("[data-widget=catalogResultsHeader]").$("h1");

    private final ElementsCollection catalogElements = $$(".c7e div a span");

    @Step("Open main page")
    public MainPage openPage(){
        Selenide.open("");
        logo.shouldBe(visible);

        return this;
    }

    @Step("Check main elements")
    public MainPage checkElements(){
        step("Logo", () -> {
            logo.shouldBe(visible);
        });
        step("Search input and button", () -> {
            searchInput.shouldBe(visible);
            searchButton.shouldBe(visible);
        });
        step("Catalog button", () -> {
            catalogButton.shouldBe(visible);
        });
        step("Order, basket, favourite, login links", () -> {
            ordersLink.shouldBe(visible);
            basketLink.shouldBe(visible);
            favoritesLink.shouldBe(visible);
            loginLink.shouldBe(visible);
        });

        return this;
    }

    @Step("Search for goods")
    public SearchPage search(String value){
        searchInput.setValue(value);
        searchButton.click();

        return new SearchPage(value);
    }

    @Step("Go to catalog section")
    public MainPage goToSection(String value){
        step("Open catalog", () -> {
            catalogButton.click();
        });
        step("Go to section", () -> {
            catalogElements.findBy(text(value)).click();
            sectionHeader.shouldHave(text(value));
        });

        return this;
    }

}
