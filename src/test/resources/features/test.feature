@test
Feature: Test that Wiki page requirements are working fine as expected

  Scenario: Test that WikiPage searches for the correct text and navigates to the Articles page
    Given I navigate to the "Wiki" page
    Then I should see the site title "Wikipedia"
    And I search for "furry rabbits" text in the site
    Then I should see the "Did you mean" suggestion text
    When I click on the suggestion
    Then I should see the "20" search results
    Then I click on the first search result
    Then I should see that article has title and references

  Scenario: Test that Slider is working fine as expected
    Given I navigate to the "Travel" page
    When I swipe left on the slider twice in the Travel page
    Then I should see the third item is displayed
