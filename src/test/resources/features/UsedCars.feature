Feature: User avails the feature of browsing or buying used cars
  As a customer, I want to browse and buy used cars so that I can choose my favourite car





  @usedcars
  Scenario: User should see at least one product under used cars
    Given I launch Trade me home page
    And I select "Motors" category
    When I choose "UsedCars"
    Then I should see at least one listing available