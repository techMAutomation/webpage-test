package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageobjectmodel.WikiPage;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static steps.Hooks.driver;

public class WikiSteps {

    private WikiPage wikiPage = PageFactory.initElements(driver, WikiPage.class);

    @Given("^I navigate to the \"([^\"]*)\" page$")
    public void i_navigate_to_the_page(String name) {
        if (name.equalsIgnoreCase("Wiki")) {
            driver.get("https://www.wikipedia.org/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        } else if (name.equalsIgnoreCase("Travel")) {
            driver.get("https://www.travelex.co.uk/");
            Dimension d = new Dimension(500, 1000);
            driver.manage().window().setSize(d);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    }

    @Then("^I should see the site title \"([^\"]*)\"$")
    public void I_should_see_the_site_title(String siteName) {
        Assert.assertEquals(siteName, wikiPage.verifyWikiPageTitle(), "Check - Wiki page title has been displayed");
    }

    @And("^I search for \"([^\"]*)\" text in the site$")
    public void I_search_for_text_in_the_site(String text) {
        wikiPage.enterSearchKeyword(text);
        wikiPage.clickSearchButton();
    }

    @Then("^I should see the \"([^\"]*)\" suggestion text$")
    public void I_should_see_the_suggestion_text(String text) {
        Assert.assertEquals(wikiPage.verifyDidYouMeanText(text), true, "Check - Did you mean suggestion text is displayed correctly");
    }

    @When("^I click on the suggestion$")
    public void I_click_on_the_suggestion() {
        wikiPage.clickSuggestionLink();
    }

    @Then("^I should see the \"([^\"]*)\" search results$")
    public void I_should_see_the_search_results(String results) {
        Assert.assertEquals(wikiPage.verifyResultsText(results),true, "Check - page has 20 search results");
    }

    @Then("^I click on the first search result$")
    public void I_click_on_the_first_search_result() throws InterruptedException {
        wikiPage.clickFirstSearchResultLink();
    }

    @Then("^I should see that article has title and references$")
    public void I_should_see_that_the_article_has_a_title_and_a_references() {
        Assert.assertEquals(wikiPage.verifyArticleTitleAndReferencesText(), true, "Check - Article has Title and References");
    }

    // Swipe Steps

    @When("^I swipe left on the slider twice in the Travel page$")
    public void I_swipe_left_on_the_slider_twice_in_the_Travel_page() {
        Point slidePosition = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/section[2]/article/div[1]/div/div/div[1]")).getLocation();
        System.out.println("- Slide positions :: " + slidePosition); // 20, 917
        Actions move = new Actions(driver);
        IntStream.rangeClosed(1, 3).forEach(num -> {
            WebElement slider = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/section[2]/article/div[1]/div/div/div[" + num + "]"));
            move.moveToElement(slider).clickAndHold(slider).dragAndDropBy(slider, slidePosition.x, (slidePosition.y - 100)).release().build().perform();
            slider.click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Then("^I should see the third item is displayed$")
    public void I_should_see_the_third_item_is_displayed() {
        Assert.assertEquals(wikiPage.verifySliderThirdItemIsDisplayed(), true,"Check - 3rd slider 'Buy foreign currency' is displayed");
    }
}