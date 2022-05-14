package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    private String searchText;

    public SearchPage(String searchText){
        this.searchText = searchText;
    }

    public SearchPage(){}

    private final ElementsCollection searchResultsTexts = $$(".tile-hover-target .tsBodyL");
    private final SelenideElement firstSearchResult = searchResultsTexts.first();

    @Step("Check that the first search result contains search text")
    public SearchPage checkSearchResults() {
        String[] splited = searchText.split(" ");
        for(int i = 0; i < splited.length; i++){
            firstSearchResult.shouldHave(text(splited[i]));
        }

        return this;
    }

    @Step("Check that the first search result contains value")
    public SearchPage checkSearchResults(String value) {
        String[] splited = value.split(" ");
        for(int i = 0; i < splited.length; i++){
            firstSearchResult.shouldHave(text(splited[i]));
        }

        return this;
    }

}
