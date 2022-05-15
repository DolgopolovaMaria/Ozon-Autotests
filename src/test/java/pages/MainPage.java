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


    private final ElementsCollection
            catalogElements = $$(".c7e div a span"),
            goodsElements = $$(".dk.kd.k2d.al3b");

    private final SelenideElement
        logo = $("[alt=Ozon]"),
        searchInput = $("input[placeholder='Искать на Ozon']"),
        catalogButton = $(byText("Каталог")).closest("button"),
        searchButton = $("[aria-label=Поиск]").parent(),
        ordersLink = $$(".kc1").find(text("Заказы")),
        basketLink = $$(".kc1").find(text("Корзина")),
        favoritesLink = $$(".kc1").find(text("Избранное")),
        loginLink = $$(".kc1").find(text("Войти")),
        sectionHeader = $("[data-widget=catalogResultsHeader]").$("h1"),
        firstItemBasketButton = goodsElements.first().$(byText("В корзину")).closest("button"),
        firstItemBasketQuantity = goodsElements.first().$(".vc8"),
        plusBasketButton = goodsElements.first().$(".vc8").sibling(0),
        minusBasketButton = goodsElements.first().$(".vc8").preceding(0);
        //firstItemName = goodsElements.first().$(".d8e.e8d.de9.fd1.tsBodyM.dk4");

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

    @Step("Go to catalog section: {value}")
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

    @Step("Add item to basket")
    public MainPage addToBasket(){
        firstItemBasketButton.click();

        return this;
    }

    @Step("Check quantity of item in basket: {value}")
    public MainPage checkQuantityInBasket(int value){
        if (value < 1){
            firstItemBasketButton.shouldBe(visible);
            return this;
        }
        String text = value + " шт.";
        firstItemBasketQuantity.shouldHave(text(text));
        return this;
    }

    @Step("Increase quantity of item in basket")
    public MainPage increaseQuantityInBasket(){
        plusBasketButton.click();
        return this;
    }

    @Step("Decrease quantity of item in basket")
    public MainPage decreaseQuantityInBasket(){
        minusBasketButton.click();
        return this;
    }
}
