package pageobjectmodel;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikiPage {

    @FindBy(xpath = "//*[@id=\"www-wikipedia-org\"]/h1/div/div")
    private WebElement wikiPageTitle;
    @FindBy(id = "searchInput")
    private WebElement searchTextField;
    @FindBy(css = "button.pure-button.pure-button-primary-progressive")
    private WebElement searchButton;

    @FindBy(className = "searchdidyoumean")
    private WebElement didYouMeanText;
    @FindBy(id = "mw-search-DYM-suggestion")
    private WebElement suggestionLink;
    @FindBy(css = "#mw-search-DYM-suggestion > em")
    private WebElement suggestionText;
    @FindBy(className = "results-info")
    private WebElement resultsInfoText;

    @FindBy(xpath = "//*[@id=\"mw-content-text\"]/div[4]/ul/li[1]/div[1]/a")
    private WebElement firstSearchResult;
    @FindBy(id = "firstHeading")
    private WebElement articleTitle;
    @FindBy(id = "References")
    private WebElement referencesText;

    // Swipe elements
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/section[2]/article/div[1]/div/div/div[3]/div/h4/span/a")
    private WebElement buyForeignCurrencySlider;

    private Logger log = Logger.getLogger(WikiPage.class.getName());
    private static String articleName = "";

    public String verifyWikiPageTitle() {
        log.info("- Site title :: " + wikiPageTitle.getText());
        return wikiPageTitle.getText();
    }

    public void enterSearchKeyword(String text) {
        searchTextField.click();
        searchTextField.clear();
        searchTextField.sendKeys(text);
    }

    public void clickSearchButton() {
        if (searchButton.isDisplayed()) {
            searchButton.click();
            log.info("- Clicked on Search button -");
        }
    }

    public boolean verifyDidYouMeanText(String text) {
        log.info("- Did you mean text :: " + didYouMeanText.getText());
        return didYouMeanText.getText().contains(text);
    }

    public void clickSuggestionLink() {
        log.info("- Suggestion text :: " + suggestionText.getText());
        if (suggestionText.getText().equalsIgnoreCase("fury rabbits")) {
            suggestionLink.click();
            log.info("- Clicked on Suggestion link -");
        }
    }

    public boolean verifyResultsText(String text) {
        log.info("- Results text :: " + resultsInfoText.getText());
        String[] name = resultsInfoText.getText().split(" ");
        log.info(" - Results text spitted and the text 20 contains in the array position 3 : " + name[3]);
        return name[3].equalsIgnoreCase(text);
    }

    public void clickFirstSearchResultLink() throws InterruptedException {
        log.info("- First search result link text :: " + firstSearchResult.getText());
        if (firstSearchResult.isDisplayed()) {
            Thread.sleep(200);
            articleName = firstSearchResult.getText();
            firstSearchResult.click();
            log.info("- Clicked on 1st Search results Article link - " + articleName);
            Thread.sleep(200);
        }
    }

    public boolean verifyArticleTitleAndReferencesText() {
        boolean status = false;
        log.info("- 1st search result Article Text :: " + articleTitle.getText());
        if (articleTitle.getText().equalsIgnoreCase(articleName)) {
            log.info("- References Text :: " + referencesText.getText());
            if (referencesText.getText().equalsIgnoreCase("References"))
                status = true;
        }
        return status;
    }

    // Swipe Methods

    public boolean verifySliderThirdItemIsDisplayed() {
        log.info("- 3rd slider name :: " + buyForeignCurrencySlider.getText());
        return (buyForeignCurrencySlider.getText().equalsIgnoreCase("Buy foreign currency"));
    }

}
