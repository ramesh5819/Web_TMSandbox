Feature: User avails the feature of browsing or buying used cars
  As a customer, I want to browse and buy used cars so that I can choose my favourite car


  Background: User launches Trade Me Home page
    Given I launch Trade me home page

  @usedcars
  Scenario: User should see at least one product under used cars
    And I select "Motors" category
    When I choose "UsedCars" under Motors
    Then I should see at least one listing available